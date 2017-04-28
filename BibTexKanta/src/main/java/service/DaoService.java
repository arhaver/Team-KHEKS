package service;

import database.DAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import reference.Reference;

public class DaoService {

    List<DAO<Reference>> daos;
    
    public DaoService(DAO... daos) {
        this.daos = Arrays.asList(daos);
    }
    
    public List<Reference> getAllReferences(){
        List<Reference> all = new ArrayList<>();
        
        for(DAO<Reference> dao : daos){
            all.addAll(dao.findAll());
        }
        
        return all;
    }
    
    public Map<Integer, Reference> getReferencesNumbermapped(){
        return Utilities.mapReferences(getAllReferences());
    }
}
