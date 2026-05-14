package com.pergamon.application.responses;

import java.util.List;

import com.pergamon.core.entites.Feedback;

public class ViewFeedbacksResponse {
	public List<Feedback> feedbacks;
	
	public ViewFeedbacksResponse(List<Feedback> feedbacks)
	{
		this.feedbacks = feedbacks;
	}
}
