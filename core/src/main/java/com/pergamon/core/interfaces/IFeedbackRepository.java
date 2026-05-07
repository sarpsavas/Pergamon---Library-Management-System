package com.pergamon.core.interfaces;

import java.util.List;
import java.util.UUID;

import com.pergamon.core.entites.Feedback;

public interface IFeedbackRepository {
	
	List<Feedback> GetAllFeedbacks(String organizationPerId);
	
	Feedback GetFeedbackByFeedbackId(UUID feedbackId, String organizationPerId);
}
