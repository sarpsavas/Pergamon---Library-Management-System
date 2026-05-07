package com.pergamon.core.entites;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Barrowed {
	public UUID barrowedId;
	public String bookId;
	public UUID visitorId;
	public String organizationPerId;
	public LocalDateTime barrStartTime;
	public LocalDateTime barrEndTime;
	
	public Barrowed()
	{
		barrowedId = UUID.randomUUID();
		barrStartTime = LocalDateTime.now();
		barrEndTime = barrStartTime.plusDays(14);
	}
}
