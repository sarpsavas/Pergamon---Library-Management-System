package com.pergamon.application.feedbacks.view_feedbacks;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.pergamon.application.responses.ViewFeedbacksResponse;
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
	public ViewFeedbacksResponse handle(ViewFeedbacksQuery request)
	{
		return new ViewFeedbacksResponse(_feRepository.GetAllFeedbacks(request.organizationPerId()));
	}
}
