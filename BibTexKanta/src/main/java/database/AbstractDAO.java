package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T>{

    private final String tableName;
    private final String columns;
    
    private final Database db;
    
    public AbstractDAO(Database database, String tableName, String columns){
        db = database;
        
        this.tableName = tableName;
        this.columns = columns;
    }
    
    @Override
    public List<T> findAll() throws Exception{
        Connection c = db.getConnection();
        
        PreparedStatement query = c.prepareStatement("SELECT * FROM "+tableName);
        
        ResultSet rs = query.executeQuery();
        
        List<T> ts = new ArrayList<>();
        
        while(rs.next()){
            ts.add(createFromResultSet(rs));
        }
        
        rs.close();
        query.close();
        c.close();
        
        return ts;
    }

    @Override
    public boolean add(T t) throws Exception{
        Connection c = db.getConnection();
        
        List<Object> decomposed = decompose(t);
        
        String query = "INSERT INTO "+tableName+" ("+columns+") VALUES ("+
                questionMarks(decomposed.size())+")";
        
        PreparedStatement ps = c.prepareStatement(query);
        setPreparedStatementValues(ps, decomposed);
        
        int createdAmount = ps.executeUpdate();
        
        ps.close();
        c.close();
        
        return createdAmount > 0;
    }
    
    protected abstract T createFromResultSet(ResultSet rs) throws Exception;
    protected abstract List<Object> decompose(T t);
    
    private void setPreparedStatementValues(PreparedStatement ps, List<Object> values) throws Exception{
        for(int i = 0 ; i < values.size() ; i++){
            int j = i+1;
            Object o = values.get(i);
            
            if(o == null){
                ps.setObject(j, "");
                continue;
            }
            
            Class clas = o.getClass();
            
            if(clas == Timestamp.class){
                ps.setTimestamp(j, (Timestamp) o);
            }else if(clas == Integer.class){
                ps.setInt(j, (Integer) o);
            }else if(clas == String.class){
                ps.setString(j, (String) o);
            }else if(clas == Boolean.class){
                ps.setBoolean(j, (Boolean) o);
            }
        }
    }
    
    private String questionMarks(int amount){
        if(amount <= 0){
            return "";
        }
        
        StringBuilder b = new StringBuilder();
        
        for(int i = 0 ; i < amount-1 ; i++){
            b.append("?, ");
        }
        
        return b.toString()+"?";
    }
    
}
