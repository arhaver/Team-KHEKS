package database;

import java.util.ArrayList;
import java.util.List;
import reference.Reference;
import service.DaoService;

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
        boolean success = ts.add(t);
        if (success)
            ds.addReference((Reference) t);
        return success;
    }
    
}
