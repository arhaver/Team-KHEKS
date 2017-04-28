package service.search;

import java.util.ArrayList;
import java.util.List;
import service.search.matcher.*;

public class QueryBuilder {
    
    private String query;
    private int index;
    
    public Matcher buildQuery(String query) throws Exception{
        this.query = query;
        this.index = 0;
        
        RecursiveBuilder builder = new RecursiveBuilder(this);
        return builder.bqrec();
    }
    
    private Matcher finishMatcher(List<Matcher> matchers, char matchingType) throws Exception{
        if(matchingType == 'o' && matchers.size() == 1){
            return matchers.get(0);
        }
        
        if(matchingType == '^' && matchers.size() > 1){
            return new And(matchers);
        }
        
        if(matchingType == 'v' && matchers.size() > 1){
            return new Or(matchers);
        }
        
        if( matchers.isEmpty()){
            exc("Tyhjät sulut");
        }
        
        if( matchers.size() == 1){
            exc("Yhdistehaku ilman toista hakuehtoa");
        }
        
        exc("Tuntematon virhe");
        return null;
    }
    
    private Matcher createFieldMatcher() throws Exception{
        pop();
        String field = searchUntil(':');
        String value = searchUntil(')');
        pop();
        
        if(field.equals("year")){
            try{
                return new YearMatcher(Integer.parseInt(value));
            }catch(Exception e){
                exc("Vuosiluku " + value +" ei ollut luku");
            }
        }else{
            return new FieldMatcher(field, value);
        }
        
        return null;
    }
    
    private String searchUntil(char c){
        int startIndex = index;
        while(peek() != c) pop();
        
        String subString = query.substring(startIndex, index);
        pop();
        return subString;
    }
    
    private char peek(){
        if(index >= query.length()){
            return ')';
        }
        
        return query.charAt(index);
    }
    
    private char pop(){
        char c = peek();
        index++;
        return c;
    }
    
    private void exc(String message) throws Exception{
        throw new Exception(message);
    }
    
    private class RecursiveBuilder{
        
        private QueryBuilder qb;
        
        private List<Matcher> matchers;
        private char matchingType;
        private boolean groupingAllowed;
        private boolean cont;
        
        public RecursiveBuilder(QueryBuilder queryBuilder){
            this.qb = queryBuilder;
            
            matchers = new ArrayList<>();
            matchingType = 'o';
            groupingAllowed = false;
            cont = true;
        }
        
        private Matcher bqrec()throws Exception{
            while(cont){
                char c = pop();

                switch(c){
                    case 'F': 
                        createFieldMatcher();
                        break; 
                    case '(':
                        recurse();
                        break;
                    case 'v':
                        applyUnifier(c);
                        break;
                    case '^':
                        applyUnifier(c);
                        break;
                    case '-':
                        return makeNot();
                    case ')':
                        cont = false;
                        break;
                    default: throw new Exception();
                }
            }

            return qb.finishMatcher(matchers, matchingType);
        }
        
        private void createFieldMatcher() throws Exception{
            matchers.add(qb.createFieldMatcher());
            groupingAllowed = true;
        }
        
        private void recurse() throws Exception{
            RecursiveBuilder recBuilder = new RecursiveBuilder(qb);
            matchers.add(recBuilder.bqrec());
            
            groupingAllowed = true;
        }
        
        private void applyUnifier(char currentCharacter) throws Exception{
            if(groupingAllowed){
                if(matchingType != currentCharacter){
                    List<Matcher> helper = new ArrayList<>();
                    helper.add(finishMatcher(matchers, matchingType));
                    matchers = helper;
                }
                matchingType = currentCharacter;
                groupingAllowed = false;
                return;
            }
            
            qb.exc("Monta yhdistemerkkiä peräkkäin");
        }
        
        private Matcher makeNot() throws Exception{
            RecursiveBuilder rec = new RecursiveBuilder(qb);
            groupingAllowed = true;
            
            return new Not(rec.bqrec());
        }
    }
}
