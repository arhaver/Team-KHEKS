package service.search.matcher;

import reference.Reference;

public class And implements Matcher{

    private Matcher[] matchers;
    
    public And(Matcher... matchers){
        this.matchers = matchers;
    }
    
    @Override
    public boolean matches(Reference ref) {
        for(Matcher matcher : matchers){
            if(!matcher.matches(ref)) return false;
        }
        
        return true;
    }
    
}
