package entities;

import java.time.LocalDate;
import java.util.UUID;
import enums.*;



public class Transaction {
	public UUID TransactionId;
	public UUID UserId;
	public TransactionType Type;
	public LocalDate TransactionType;
	public Succes Succes;
	public String Description;
}
