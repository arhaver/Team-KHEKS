package service.search.matcher;

import java.util.Arrays;
import java.util.List;
import reference.Reference;

public class And implements Matcher{

    private List<Matcher> matchers;
    
    public And(Matcher... matchers){
        this(Arrays.asList(matchers));
    }
    
    public And(List<Matcher> matchers){
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
