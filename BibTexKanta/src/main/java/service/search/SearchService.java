package service.search;

import java.util.List;
import java.util.Map;
import reference.Reference;
import service.Utilities;

public class SearchService {
    
    public Map<Integer, Reference> searchReferenceMap(List<Reference> references, String query) throws Exception{
        return Utilities.mapReferences(search(references, query));
    }
    
    public List<Reference> search(List<Reference> references, String query) throws Exception{
        return null;
    }
    
}
