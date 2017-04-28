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
public class YearMatcherTest {
    
    private Reference ref1;
    private Reference ref2;
    private Reference ref3;
    
    @Before
    public void setUp() {
        ref1 = new BookRef("Asd", "Asd", "Asd", 1000);
        ref2 = new BookRef("Asda", "Asda", "Asda", 1500);
        ref3 = new InproceedingsRef("Asd", "Asd", "Asd", 1000);
    }
    
    @Test
    public void matcherAcceptsRightRefs(){
        Matcher matcher = new YearMatcher(1000);
        Matcher matcher2 = new YearMatcher(1500);
        
        assertTrue(matcher.matches(ref1));
        assertTrue(matcher.matches(ref3));
        assertTrue(matcher2.matches(ref2));
        
        assertTrue(!matcher.matches(ref2));
        assertTrue(!matcher2.matches(ref3));
    }
    
    
}
