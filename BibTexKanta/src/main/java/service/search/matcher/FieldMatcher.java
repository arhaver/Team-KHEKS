package service.search.matcher;

import reference.Reference;

public class FieldMatcher implements Matcher{
    
    private String field;
    private String value;

    public FieldMatcher(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public boolean matches(Reference ref) {
        String gottenValue = ref.getField(field);
        
        if(gottenValue != null){
            return value.equals(gottenValue);
        }
        
        return false;
    }
    
}
