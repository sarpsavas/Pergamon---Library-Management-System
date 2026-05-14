package com.pergamon.core.entites;

import java.time.LocalTime;
import java.util.UUID;

public class Feedback {
	
	public UUID feedbackId;
	public String organizationPerId;
	public String feedbackText;
	public LocalTime feedbackTime;
	
	public Feedback(String feedbackText, String organizationPerId)
	{
		this.feedbackId = UUID.randomUUID();
		this.feedbackText = feedbackText;
		this.feedbackTime = LocalTime.now();
	}
}
