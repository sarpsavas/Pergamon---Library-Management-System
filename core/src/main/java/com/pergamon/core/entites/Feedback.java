package com.pergamon.core.entites;

import java.time.LocalTime;
import java.util.UUID;

public class Feedback {
	
	UUID feedbackId;
	String feedbackText;
	LocalTime feedbackTime;
	
	public Feedback(String feedbackText)
	{
		this.feedbackId = UUID.randomUUID();
		this.feedbackText = feedbackText;
		this.feedbackTime = LocalTime.now();
	}
}
