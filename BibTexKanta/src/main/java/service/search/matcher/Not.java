package service.search.matcher;

import reference.Reference;

public class Not implements Matcher{

    private Matcher matcher;
    
    public Not(Matcher matcher){
        this.matcher = matcher;
    }
    
    @Override
    public boolean matches(Reference ref) {
        return !matcher.matches(ref);
    }
    
}
