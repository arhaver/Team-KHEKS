package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    private String address;
    
    public Database(String address){
        this.address = address;
    }
    
    public Connection getConnection() throws Exception{
        return DriverManager.getConnection(address);
    }
}
