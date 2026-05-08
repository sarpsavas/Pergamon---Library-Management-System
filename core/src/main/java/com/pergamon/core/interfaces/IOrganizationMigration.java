package com.pergamon.core.interfaces;

public interface IOrganizationMigration {
	
	void addShema(String organizationPerId);
	
	void addBooksTable(String organizationPerId);
	void addTransactionsTable(String organizationPerId);
	void addAdminsTable(String organizationPerId);
	void addVisitorsTable(String organizationPerId);
	void addBarrowedsTable(String organizationPerId);
	void addFeedbacksTable(String organizationPerId);
	
	void deleteShema(String organizationPerId);
}
