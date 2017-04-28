package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import reference.Reference;

public class Utilities {
    public static Map<Integer, Reference> mapReferences(List<Reference> references){
        Map<Integer, Reference> refMap = new HashMap<>();
        
        int i = 1;
        for(Reference ref : references){
            refMap.put(i++, ref);
        }
        
        return refMap;
    } 
}
