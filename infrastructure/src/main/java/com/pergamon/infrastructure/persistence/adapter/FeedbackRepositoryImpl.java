package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import com.pergamon.core.entites.Feedback;
import com.pergamon.core.interfaces.IFeedbackRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IFeedbackDA;

@Repository
public class FeedbackRepositoryImpl implements IFeedbackRepository,IRepository<Feedback>{
	 
	private final Jdbi _jdbi;
	
	public FeedbackRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Feedback> GetAllFeedbacks( String organizationPerId)
	{
		return _jdbi.withExtension(IFeedbackDA.class, da -> da.GetAllFeedbacks(organizationPerId));
	}
	
	public Feedback GetFeedbackByFeedbackId(UUID feedbackId, String organizationPerId)
	{
		return _jdbi.withExtension(IFeedbackDA.class, da -> da.GetFeedbackByFeedbackId(feedbackId, organizationPerId));
	}
	
	public void add(Feedback feedback)
	{
		_jdbi.useExtension(IFeedbackDA.class, da -> da.add(feedback,feedback.organizationPerId));
	}
	
	public void update(Feedback feedback)
	{
		_jdbi.useExtension(IFeedbackDA.class, da -> da.update(feedback,feedback.organizationPerId));
	}
	
	public void delete(UUID FeedbackId, String organizationPerId)
	{
		_jdbi.useExtension(IFeedbackDA.class, da -> da.delete(FeedbackId,organizationPerId));
	}
}
