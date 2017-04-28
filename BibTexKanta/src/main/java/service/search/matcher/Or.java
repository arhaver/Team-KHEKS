package service.search.matcher;

import reference.Reference;

public class Or implements Matcher{

    private Matcher[] matchers;
    
    public Or(Matcher... matchers){
        this.matchers = matchers;
    }
    
    @Override
    public boolean matches(Reference ref) {
        for(Matcher matcher : matchers){
            if(matcher.matches(ref)) return true;
        }
        
        return false;
    }
    
}
