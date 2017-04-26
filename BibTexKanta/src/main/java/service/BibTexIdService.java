package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import reference.Reference;

public class BibTexIdService {

    private Set<String> idset;

    public BibTexIdService(){
        this(new ArrayList<>());
    }
    
    public BibTexIdService(List<Reference> references) {
        idset = new HashSet<>();
        buildIdSet(references);
    }

    public boolean validid(String candidate) {
        return !idset.contains(candidate);
    }

    public boolean saveId(String candidate, Reference ref) {
        if (validid(candidate)) {
            ref.setField("bibTexId", candidate);
            return true;
        }
        return false;
    }

    public void generateId(Reference ref) {
        String autohrs = ref.getField("authors");
        String candidate = autohrs.substring(0, 2) + ref.getYear();
        if (!validid(candidate)) {
            int i = 2;
            while (!validid(candidate + "_" + i)) {
                i++;
            }
            candidate = candidate + "_" + i;
        }
        ref.setField("bibTexId", candidate);
    }

    private void buildIdSet(List<Reference> references) {
        if (references != null) {
            for (Reference ref : references) {
                idset.add(ref.getField("bibTexId"));
            }
        }

    }
}
