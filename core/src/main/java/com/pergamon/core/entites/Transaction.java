package com.pergamon.core.entites;


import java.time.LocalDateTime;
import java.util.UUID;

import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;




public class Transaction {
	public UUID transactionId;
	public String organizationPerId;
	public UUID userId;
	public TransactionType type;
	public LocalDateTime transactionTime;
	public Succes succes;
	private String description;
	
	public Transaction()
	{
		transactionId = UUID.randomUUID();
		transactionTime = LocalDateTime.now();
	}
	
	public void setDescription(String descriptionText)
	{
		if (description.length() > 500 )
		{
			throw new IllegalArgumentException("description string length error");
		}
		this.description = descriptionText;
	}
	
	public String getDescription()
	{
		return description;
	}
}
