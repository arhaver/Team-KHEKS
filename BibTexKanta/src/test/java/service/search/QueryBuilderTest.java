package service.search;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;
import service.search.matcher.*;

public class QueryBuilderTest {
    
    private QueryBuilder builder;
    
    private BookRef help1;
    private BookRef help2;
    private BookRef help3;
    
    @Before
    public void setUp() {
        builder = new QueryBuilder();
        
        help1 = new BookRef("title", "authors", "publisher", 1900);
        help2 = new BookRef("title2", "authors2", "publisher2", 1990);
        help3 = new BookRef("title3", "authors3", "publisher3", 1999);
        help1.addTag("test");
    }
    
    @Test
    public void simpleFieldQueryWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("f:title:sad");
        Matcher matcher2 = builder.buildQuery("f:year:200");
        
        assertTrue(matcher1.getClass() == FieldMatcher.class);
        assertTrue(matcher2.getClass() == YearMatcher.class);
        
        FieldMatcher m1 = (FieldMatcher) matcher1;
        YearMatcher m2 = (YearMatcher) matcher2;
        
        assertEquals(m1.getField(), "title");
        assertEquals(m1.getValue(), "sad");
        
        assertEquals(m2.getYear(), 200);
    }
    
    @Test
    public void simpleTagQueryWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("t:test");
        
        assertTrue(matcher1.getClass() == TagMatcher.class); 
        TagMatcher m1 = (TagMatcher) matcher1;

        assertTrue(m1.matches(help1));
    }
    
    @Test
    public void simpleQueryWithBracletsWork() throws Exception{
        Matcher matcher = builder.buildQuery("(f:title:asd)");
        
        assertTrue(matcher.getClass() == FieldMatcher.class);
        
        FieldMatcher m = (FieldMatcher) matcher;
        
        assertEquals(m.getField(), "title");
        assertEquals(m.getValue(), "asd");
    }
    
    @Test
    public void simpleTagQueryWithBracesWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("(t:test)");
        
        assertTrue(matcher1.getClass() == TagMatcher.class); 
        TagMatcher m1 = (TagMatcher) matcher1;

        assertTrue(m1.matches(help1));
    }
    
    @Test
    public void notQueryWorks() throws Exception{
        Matcher matcher = builder.buildQuery("-(f:title:title)");
        
        assertTrue(matcher.getClass() == Not.class);
        
        assertTrue(!matcher.matches(help1));
        assertTrue(matcher.matches(help2));
    }
    
    @Test
    public void simpleTagQueryWithNotWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("-(t:test)");
        
        assertTrue(matcher1.getClass() == Not.class); 

        assertFalse(matcher1.matches(help1));
        assertTrue(matcher1.matches(help2));
    }
    
    
    
    @Test
    public void andQueryWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("(f:title:title)^(f:authors:authors)");
        Matcher matcher2 = builder.buildQuery("(f:title:title2)^(f:authors:authors2)^(f:publisher:publisher2)");
        
        assertTrue(matcher1.getClass() == And.class);
        assertTrue(matcher2.getClass() == And.class);
        
        assertTrue(matcher1.matches(help1));
        assertTrue(!matcher1.matches(help2));
        
        assertTrue(!matcher2.matches(help1));
        assertTrue(matcher2.matches(help2));
    }
    
    @Test
    public void orQueryWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("(f:title:title)v(f:authors:authors2)");
        Matcher matcher2 = builder.buildQuery("(f:title:title2)v(f:authors:authors4)v(f:publisher:publisher)");
        
        assertTrue(matcher1.getClass() == Or.class);
        assertTrue(matcher2.getClass() == Or.class);
        
        assertTrue(matcher1.matches(help1));
        assertTrue(matcher1.matches(help2));
        assertTrue(!matcher1.matches(help3));
        
        assertTrue(matcher2.matches(help1));
        assertTrue(matcher2.matches(help2));
        assertTrue(!matcher2.matches(help3));
    }
    
    @Test
    public void andAndOrWithoutBrackletsWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("(f:year:1900)v(f:title:title2)^(f:authors:authors2)");
        Matcher matcher2 = builder.buildQuery("(f:year:1900)^(f:title:title2)v(f:authors:authors2)");
        
        assertTrue(matcher1.getClass() == And.class);
        assertTrue(matcher2.getClass() == Or.class);
        
        assertTrue(matcher1.matches(help2));
        assertTrue(!matcher1.matches(help1));
        assertTrue(!matcher1.matches(help3));
        
        assertTrue(!matcher2.matches(help1));
        assertTrue(matcher2.matches(help2));
        assertTrue(!matcher2.matches(help3));
    }
    
    @Test
    public void queryOfMultipleLevelsWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery(
                "((f:year:1900)^(f:title:title))v((f:year:1990)^(f:title:title2))");
        Matcher matcher2 = builder.buildQuery(
                "((f:year:1999)v(f:title:title))^((f:year:1990)v(f:title:title3))");
        Matcher matcher3 = builder.buildQuery(
                "-(f:title:title)^((f:year:1900)v(f:authors:authors3))");
        
        assertTrue(matcher1.getClass() == Or.class);
        assertTrue(matcher2.getClass() == And.class);
        assertTrue(matcher3.getClass() == And.class);
        
        assertTrue(matcher1.matches(help1));
        assertTrue(matcher1.matches(help2));
        assertTrue(!matcher1.matches(help3));
        
        assertTrue(!matcher2.matches(help1));
        assertTrue(!matcher2.matches(help2));
        assertTrue(matcher2.matches(help3));
        
        assertTrue(!matcher3.matches(help1));
        assertTrue(!matcher3.matches(help2));
        assertTrue(matcher3.matches(help3));
    }
    
    @Test
    public void yearErrorShownCorrectly(){
        try{
            Matcher wrong = builder.buildQuery("f:year:asd");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Vuosiluku \"asd\" ei ollut luku");
        }
    }
    
    @Test
    public void wrongCharacterErrorShownCorrectly(){
        try{
            Matcher wrong = builder.buildQuery("S:year:100");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Merkki \"S\" epävalidissa kohtaa");
        }
        
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)4");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Merkki \"4\" epävalidissa kohtaa");
        }
        
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)^A(f:title:title)");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Merkki \"A\" epävalidissa kohtaa");
        }
    }
    
    @Test
    public void emptyBrackletsErrorShows(){
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)^()");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Tyhjät sulut");
        }
    }
    
    @Test
    public void twoFieldsWithoutUnifierShowsRightError(){
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)(f:title:title)");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Kahden ehdon välillä ei yhdistäjämerkkiä");
        }
    }
    
    @Test
    public void oneFieldAndUnifierShowsRightError(){
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)^");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Yhdistehaku ilman toista hakuehtoa");
        }
        
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)v");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Yhdistehaku ilman toista hakuehtoa");
        }
    }
    
    @Test
    public void multipleUnifiersInRowCausesError(){
        try{
            Matcher wrong = builder.buildQuery("(f:year:100)vv(f:title:title)");
            assertTrue(false);
        }catch(Exception e){
            assertEquals(e.getMessage(), "Monta yhdistemerkkiä peräkkäin");
        }
    }
}
