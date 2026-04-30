package com.pergamon.core.interfaces;

import java.util.UUID;

public interface IRepository<T>  {
	
	void Add(T object);
	
	void Update(T object);
	
	void Delete(UUID object);
	
}
