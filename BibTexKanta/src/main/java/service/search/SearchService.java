package service.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import reference.Reference;
import service.Utilities;
import service.search.matcher.Matcher;

public class SearchService {
    
    private QueryBuilder qbuilder;
    
    public SearchService(){
        qbuilder = new QueryBuilder();
    }
    
    public Map<Integer, Reference> searchReferenceMap(List<Reference> references, String query) throws Exception{
        return Utilities.mapReferences(search(references, query));
    }
    
    public List<Reference> search(List<Reference> references, String query) throws Exception{
        Matcher matcher = qbuilder.buildQuery(query);
        
        List<Reference> matches = new ArrayList<>();
        for(Reference ref : references){
            if(matcher.matches(ref)) matches.add(ref);
        }
        
        return matches;
    }
    
}
