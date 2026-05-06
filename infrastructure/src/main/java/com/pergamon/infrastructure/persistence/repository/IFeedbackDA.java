package com.pergamon.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.pergamon.core.entites.Feedback;
import com.pergamon.core.entites.Visitor;

@RegisterBeanMapper(Feedback.class)
public interface IFeedbackDA {
	
	@SqlQuery("")
	List<Feedback> GetAllFeedbacks();
	
	@SqlQuery("")
	Feedback GetFeedbackByFeedbackId(UUID feedbackId);
	
	@SqlUpdate("")
	void add(@BindBean Feedback feedback);
	
	@SqlUpdate("")
	void update(@BindBean Feedback feedback);
	
	@SqlUpdate("")
	void delete(@Bind("id") UUID id);
}
