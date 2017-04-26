package service;

import database.DAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import reference.Reference;

public class DaoService {

    DAO[] daos;
    ArrayList<Reference> references;
    BibTexIdService btids;
    
    public DaoService(DAO... daos) {
        this.daos = daos;
        this.references = new ArrayList<>();
        buildRefSet();
        this.btids = new BibTexIdService(references);
        
    }
    
    public BibTexIdService getBibTexIdService() {
        return this.btids;
    }
    
    public ArrayList<Reference> getAllReferences() {
        return references;
    }
    
    public boolean addReference(Reference ref) {
        return references.add(ref);
    }

    private void buildRefSet() {
        for (DAO dao : daos) {
            List<Reference> refs = dao.findAll();
            for (Reference ref : refs) {
                references.add(ref);
            }
        }

    }
}
