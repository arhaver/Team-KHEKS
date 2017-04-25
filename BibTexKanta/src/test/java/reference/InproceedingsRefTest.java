/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arto
 */
public class InproceedingsRefTest {
    
    InproceedingsRef noParam;
    InproceedingsRef withPram;
    
    public InproceedingsRefTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        noParam = new InproceedingsRef();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void constructorWithoutParametersSetsTitleNull() {
        assertEquals(null, noParam.getField("title"));
    }
    
    @Test
    public void constructorWithoutParametersSetsAuthorsNull() {
        assertEquals(null, noParam.getField("authors"));
    }
    
    @Test
    public void constructorWithoutParametersSetsBibTexIdNull() {
        assertEquals(null, noParam.getField("bibTexId"));
    }
    
    @Test
    public void constructorWithoutParametersSetsBooktitleNull() {
        assertEquals(null, noParam.getField("booktitle"));
    }
    
    @Test
    public void constructorWithoutParametersSetsPagesNull() {
        assertEquals(null, noParam.getField("pages"));
    }
    
    @Test
    public void constructorWithoutParametersSetsPublisherNull() {
        assertEquals(null, noParam.getField("publisher"));
    }
    
    @Test
    public void constructorWithoutParametersSetsAddressNull() {
        assertEquals(null, noParam.getField("address"));
    }
    
    @Test
    public void constructorWithoutParametersSetsYearMinusOne() {
        assertEquals(-1, noParam.getYear());
    }
    
    @Test
    public void constructorWithoutParametersWithOutSettingIsNotDbReady() {
        assertEquals(false, noParam.readyForDb());
    }
/*
    @Test
    public void testReadyForDb() {
        System.out.println("readyForDb");
        InproceedingsRef instance = new InproceedingsRef();
        boolean expResult = false;
        boolean result = instance.readyForDb();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
}
