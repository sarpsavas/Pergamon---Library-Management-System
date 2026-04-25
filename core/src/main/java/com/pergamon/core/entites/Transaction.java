package com.pergamon.core.entites;

import java.time.LocalDate;
import java.util.UUID;

import com.pergamon.core.enums.Succes;
import com.pergamon.core.enums.TransactionType;




public class Transaction {
	public UUID TransactionId;
	public UUID UserId;
	public TransactionType Type;
	public LocalDate TransactionType;
	public Succes Succes;
	public String Description;
}
