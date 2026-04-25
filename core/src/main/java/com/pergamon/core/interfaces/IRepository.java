package com.pergamon.core.interfaces;

public interface IRepository<T>  {
	
	void Add(T object);
	
	void Update(T object);
	
	void Delete(T object);
	
}
