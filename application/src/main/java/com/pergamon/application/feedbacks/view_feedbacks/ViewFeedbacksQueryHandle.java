package com.pergamon.application.feedbacks.view_feedbacks;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.core.entites.Feedback;
import com.pergamon.core.interfaces.IFeedbackRepository;

@Component
public class ViewFeedbacksQueryHandle {
	
	private IFeedbackRepository _feRepository;
	
	public ViewFeedbacksQueryHandle(IFeedbackRepository feRepository)
	{
		_feRepository = feRepository;
	}
	
	@QueryHandler
	public List<Feedback> handle(ViewFeedbacksQuery request)
	{
		return _feRepository.GetAllFeedbacks(request.organizationPerId());
	}
}
