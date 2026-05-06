package com.pergamon.core.interfaces;

import java.util.UUID;

public interface IRepository<T>  {
	
	void add(T object);
	
	void update(T object);
	
	void delete(UUID object);
	
}
