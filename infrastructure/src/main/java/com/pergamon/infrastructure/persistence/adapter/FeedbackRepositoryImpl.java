package com.pergamon.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.jdbi.v3.core.Jdbi;

import com.pergamon.core.entites.Feedback;
import com.pergamon.core.interfaces.IFeedbackRepository;
import com.pergamon.core.interfaces.IRepository;
import com.pergamon.infrastructure.persistence.repository.IFeedbackDA;

public class FeedbackRepositoryImpl implements IFeedbackRepository,IRepository<Feedback>{
	 
	private final Jdbi _jdbi;
	
	public FeedbackRepositoryImpl(Jdbi jdbi)
	{
		_jdbi = jdbi;
	}
	
	public List<Feedback> GetAllFeedbacks()
	{
		return _jdbi.withExtension(IFeedbackDA.class, da -> da.GetAllFeedbacks());
	}
	
	public Feedback GetFeedbackByFeedbackId(UUID feedbackId)
	{
		return _jdbi.withExtension(IFeedbackDA.class, da -> da.GetFeedbackByFeedbackId(feedbackId));
	}
	
	public void add(Feedback feedback)
	{
		_jdbi.useExtension(IFeedbackDA.class, da -> da.add(feedback));
	}
	
	public void update(Feedback feedback)
	{
		_jdbi.useExtension(IFeedbackDA.class, da -> da.update(feedback));
	}
	
	public void delete(UUID FeedbackId)
	{
		_jdbi.useExtension(IFeedbackDA.class, da -> da.delete(FeedbackId));
	}
}
