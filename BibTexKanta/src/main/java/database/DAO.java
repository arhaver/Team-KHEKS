package database;

import java.util.List;

public interface DAO<T> {
    
    //Hakee tietokannasta kaikki tyyppiä T olevat otukset
    public List<T> findAll();
    
    //Lisää tietokantaan annetun T:n
    public boolean add(T t); 
}
