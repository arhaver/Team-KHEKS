package database;

import java.util.ArrayList;
import java.util.List;

public class MockupDAO<T> implements DAO<T>{

    private List<T> ts;
    
    public MockupDAO(){
        ts = new ArrayList<>();
    }
    
    @Override
    public List<T> findAll() throws Exception {
        return ts;
    }

    @Override
    public boolean add(T t) throws Exception {
        ts.add(t);
        
        return true;
    }
    
}
