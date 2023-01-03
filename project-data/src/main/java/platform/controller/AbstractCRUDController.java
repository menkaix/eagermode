package platform.controller;

import java.util.List;

public abstract class AbstractCRUDController<T> {
	
	
	public abstract T create(T entity) ;
	public abstract T update(T entity) ;
	public abstract Boolean delete(T entity) ;
	
	public abstract T getOne(Integer model) ;
	public abstract List<T> getAll() ;

}
