package service.search.matcher;

import java.util.Arrays;
import java.util.List;
import reference.Reference;

public class Or implements Matcher{

    private List<Matcher> matchers;
    
    public Or(Matcher... matchers){
        this(Arrays.asList(matchers));
    }
    
    public Or(List<Matcher> matchers){
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
