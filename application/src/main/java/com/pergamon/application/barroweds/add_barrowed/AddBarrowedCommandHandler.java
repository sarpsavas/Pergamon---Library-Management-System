package com.pergamon.application.barroweds.add_barrowed;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Barrowed;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.entites.Visitor;
import com.pergamon.core.enums.AccountProfile;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IBarrowedRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.core.interfaces.IVisitorRepository;

@Component
public class AddBarrowedCommandHandler 
{
	private IRepository<Barrowed> _repositoryBa;
	private IBarrowedRepository _baRepository;
	private IRepository<Transaction> _repositoryTr;
	private IVisitorRepository _viRepository;
	
	public AddBarrowedCommandHandler(IRepository<Barrowed> repositoryBa, 
			IRepository<Transaction> repositoryTr,
			IVisitorRepository viRepository)
	{
		_repositoryBa = repositoryBa;
		_repositoryTr = repositoryTr;
	}
	
	@CommandHandler
	public void handle(AddBarrowedCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.type = TransactionType.ADD_BARROWED;
		transaction.userId = request.defaultAdminId();
		transaction.organizationPerId = request.organizationPerId();
		transaction.setDescription(request.visitorId().toString()); 
		try {
			Barrowed barrowed = new Barrowed();
			
			var response = _baRepository.GetVisitorBarrowedsByVisitorId(request.visitorId(), request.organizationPerId());
			int totalBarrowed = (response != null) ? response.size() : 0;
			
			Visitor visitor = _viRepository.GetVisitorById(request.visitorId(), request.organizationPerId());
			
			if (visitor.profil == AccountProfile.TEACHER)
			{
				if (totalBarrowed == 5) {throw new IllegalArgumentException("maximum barrowed amount exception");}
				barrowed.barrEndTime = barrowed.barrStartTime.plusDays(28);
			}
			else if (visitor.profil == AccountProfile.STUDENT)
			{
				if (totalBarrowed == 2) {throw new IllegalArgumentException("maximum barrowed amount exception");}
				barrowed.barrEndTime = barrowed.barrStartTime.plusDays(14);
			}
			
			barrowed.bookId = request.bookId();
			barrowed.visitorId = request.visitorId();
			barrowed.organizationPerId = request.organizationPerId();
			
			_repositoryBa.add(barrowed);
			transaction.succes = Succes.SUCCESSFUL;
			_repositoryTr.add(transaction);
		} 
		catch (Exception e) 
		{
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
