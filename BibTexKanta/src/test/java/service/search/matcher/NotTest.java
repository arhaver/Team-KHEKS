package service.search.matcher;

import gherkin.lexer.No;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;

public class NotTest {
    
    Reference ref1;
    Reference ref2;
    
    @Before
    public void setUp() {
        ref1 = new BookRef("Asd", "Asd", "Asd", 1000);
        ref2 = new BookRef("sda", "Asd", "Asd", 1000);
    }
    
    @Test
    public void matcherMatchesRight(){
        Matcher matcher1 = new FieldMatcher("title", "Asd");
        Matcher no = new Not(matcher1);
        
        assertTrue(no.matches(ref2));
        assertTrue(!no.matches(ref1));
    }
}
