package database;

import java.util.List;

public interface DAO<T> {
    
    public List<T> findAll() throws Exception;
    
    public boolean add(T t) throws Exception; 
}
