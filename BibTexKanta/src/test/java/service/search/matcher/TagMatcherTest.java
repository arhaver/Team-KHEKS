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
 * @author mikko
 */
public class TagMatcherTest {
    
    private Reference ref1;
    private Reference ref2;
    private Reference ref3;
    private Reference ref4;
    
    @Before
    public void setUp() {
        ref1 = new BookRef("Asd", "Asd", "Asd", 1000);
        ref2 = new BookRef("Asda", "Asda", "Asda", 1000);
        ref3 = new InproceedingsRef("Asd", "Asda", "Asd", 1000);
        ref4 = new InproceedingsRef("Asd", "Asda", "Asd", 1000);
        
        ref1.addTag("Testi1");
        ref2.addTag("testi2");
        ref3.addTag("testi1");
    }
    
    @Test
    public void matcherMatchesRight(){
        Matcher matcher1 = new TagMatcher("Testi1");
        Matcher matcher2 = new TagMatcher("testi2");
        
        assertTrue(matcher1.matches(ref1));
        assertTrue(matcher1.matches(ref3));
        assertFalse(matcher1.matches(ref2));
        assertFalse(matcher1.matches(ref4));
        
        assertTrue(matcher2.matches(ref2));
        assertFalse(matcher2.matches(ref1));
        assertFalse(matcher2.matches(ref3));
        assertFalse(matcher1.matches(ref4));
    }
    
}
