package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Feedback;
import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Feedback.class)
public interface IFeedbackDA {
	
	@SqlQuery("")
	List<Feedback> GetAllFeedbacks(@Define("organization_per_id") String organizationPerId);
	
	@SqlQuery("")
	Feedback GetFeedbackByFeedbackId(@Bind("id") UUID feedbackId, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void add(@BindBean Feedback feedback, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void update(@BindBean Feedback feedback, @Define("organization_per_id") String organizationPerId);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID id, @Define("organization_per_id") String organizationPerId);
}
