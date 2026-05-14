package com.pergamon.application.feedbacks.send_feedback;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Feedback;
import com.pergamon.core.interfaces.IRepository;

@Component
public class SendFeedbackCommandHandler {

	private IRepository<Feedback> _repositoryFe;
	
	public SendFeedbackCommandHandler(IRepository<Feedback> repositoryFe)
	{
		_repositoryFe = repositoryFe;
	}
	
	@CommandHandler
	public void handle(SendFeedbackCommand request)
	{
		Feedback feedback = new Feedback(request.feedbackText(),request.organizationPerId());
		_repositoryFe.add(feedback);
	}
}
