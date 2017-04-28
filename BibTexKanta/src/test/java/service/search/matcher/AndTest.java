/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.search.matcher;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;

/**
 *
 * @author klint
 */
public class AndTest {
    
    private Reference ref1;
    private Reference ref2;
    private Reference ref3;
    
    private Matcher matcher1;
    private Matcher matcher2;
    private Matcher matcher3;
    
    @Before
    public void setUp() {
        ref1 = new BookRef("Asd", "Asd", "Asd", 1000);
        ref2 = new BookRef("Asda", "Asda", "Asda", 1200);
        ref3 = new InproceedingsRef("Asd", "Asda", "Asd", 1000);
        
        matcher1 = new FieldMatcher("title", "Asd");
        matcher2 = new FieldMatcher("publisher", "Asd");
        matcher3 = new YearMatcher(1000);
    }
    
    @Test
    public void matcherMatchesRight(){
        And and = new And(matcher1, matcher2, matcher3);
        
        assertTrue(and.matches(ref1));
        assertTrue(!and.matches(ref2));
        assertTrue(!and.matches(ref3));
    }
}
