package service.search.matcher;

import reference.Reference;

public class TagMatcher implements Matcher{
    
    private String tag;

    public TagMatcher(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean matches(Reference ref) {
        return ref.hasTag(tag);
    } 
}
