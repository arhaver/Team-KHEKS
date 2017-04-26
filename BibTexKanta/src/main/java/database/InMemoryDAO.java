package database;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDAO<T> extends AbstractDAO<T> implements DAO<T>{

    private List<T> ts;
    
    public InMemoryDAO(){
        ts = new ArrayList<>();
    }
    
    @Override
    public List<T> findAll(){
        return ts;
    }

    @Override
    public boolean add(T t){
        return ts.add(t);
    }
    
}
