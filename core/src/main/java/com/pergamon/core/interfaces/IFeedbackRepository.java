package com.pergamon.core.interfaces;

import java.util.List;

import com.pergamon.core.entites.Feedback;

public interface IFeedbackRepository {
	
	List<Feedback> GetAllFeedbacks();
}
