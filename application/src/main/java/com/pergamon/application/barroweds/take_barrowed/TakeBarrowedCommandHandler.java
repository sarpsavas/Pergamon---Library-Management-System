package com.pergamon.application.barroweds.take_barrowed;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Barrowed;
import com.pergamon.core.entites.Transaction;
import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;
import com.pergamon.core.interfaces.IBarrowedRepository;
import com.pergamon.core.interfaces.IRepository;

@Component
public class TakeBarrowedCommandHandler 
{

	private IRepository<Barrowed> _repositoryBa;
	private IBarrowedRepository _baRepository;
	private IRepository<Transaction> _repositoryTr;
	
	public TakeBarrowedCommandHandler(
			IRepository<Barrowed> repositoryBa,
			IBarrowedRepository baRepository,
			IRepository<Transaction> repositoryTr)
	{
		_repositoryBa = repositoryBa;
		_baRepository = baRepository;
		_repositoryTr = repositoryTr;
	}
	
	@CommandHandler
	public void handle(TakeBarrowedCommand request)
	{
		Transaction transaction = new Transaction();
		transaction.organizationPerId = request.organizationPerId();
		transaction.type = TransactionType.TAKE_BARROWED;
		transaction.userId = request.defaultAdminId();
		transaction.setDescription("-");
		
		try 
		{
			Barrowed barrowed = _baRepository.GetBarrowedByBarrowedId(request.barrowedId(), request.organizationPerId());
			_repositoryBa.delete(barrowed.barrowedId, request.organizationPerId());
			
			transaction.succes = Succes.SUCCESSFUL;
			_repositoryTr.add(transaction);
		} 
		catch (Exception e) 
		{
			transaction.succes = Succes.UNSUCCESSFUL;
			_repositoryTr.add(transaction);
			transaction.succes = Succes.SUCCESSFUL;
			_repositoryTr.add(transaction);
		}
	}
}
