package service.search;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.search.matcher.FieldMatcher;
import service.search.matcher.Matcher;
import service.search.matcher.YearMatcher;

public class QueryBuilderTest {
    
    private QueryBuilder builder;
    
    @Before
    public void setUp() {
        builder = new QueryBuilder();
    }
    
    @Test
    public void simpleFieldQueryWorks() throws Exception{
        Matcher matcher1 = builder.buildQuery("F:title:sad");
        Matcher matcher2 = builder.buildQuery("F:year:200");
        
        assertTrue(matcher1.getClass() == FieldMatcher.class);
        assertTrue(matcher2.getClass() == YearMatcher.class);
        
        FieldMatcher m1 = (FieldMatcher) matcher1;
        YearMatcher m2 = (YearMatcher) matcher2;
        
        assertEquals(m1.getField(), "title");
        assertEquals(m1.getValue(), "sad");
        
        assertEquals(m2.getYear(), 200);
    }
}
