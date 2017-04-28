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
public class BookRefTest {

    BookRef ref;

    String title;
    String authors;
    String publisher;
    String address;
    String bibTexId;
    int year;

    public BookRefTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ref = new BookRef();
        title = "Artikkelin nimi";
        authors = "Kirjoittajat";
        year = 1999;
        publisher = "Kustantaja";
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorWithoutParametersSetsTitleNull() {
        assertEquals(null, ref.getField("title"));
    }

    @Test
    public void constructorWithoutParametersSetsAuthorsNull() {
        assertEquals(null, ref.getField("authors"));
    }

    @Test
    public void constructorWithoutParametersSetsBibTexIdNull() {
        assertEquals(null, ref.getField("bibTexId"));
    }

    @Test
    public void constructorWithoutParametersSetsPublisherNull() {
        assertEquals(null, ref.getField("publisher"));
    }

    @Test
    public void constructorWithoutParametersSetsAddressNull() {
        assertEquals(null, ref.getField("address"));
    }

    @Test
    public void constructorWithoutParametersSetsYearMinusOne() {
        assertEquals(-1, ref.getYear());
    }

    @Test
    public void constructorWithoutParametersWithOutSettingIsNotDbReady() {
        assertTrue(!ref.readyForDb());
    }

    @Test
    public void setterSetsFieldCorrect() {
        ref.setField("title", title);
        assertEquals(title, ref.getField("title"));
    }

    @Test
    public void getterGivesNullIfFieldNameDoesNotExsist() {
        ref.setField("title", title);
        assertEquals(null, ref.getField("otsikko"));
    }
    
    @Test
    public void isNotReadyForDbWithoutTitle() {
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("publisher", publisher);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutAuthors() {
        ref.setField("title", title);
        ref.setYear(year);
        ref.setField("publisher", publisher);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutPublisher() {
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("title", title);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutYear() {
        ref.setField("authors", authors);
        ref.setField("title", title);
        ref.setField("publisher", publisher);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isReadyForDbWithTitleAuthorsPublisherYear() {
        ref.setField("title", title);
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("publisher", publisher);
        assertTrue(ref.readyForDb());
    }
    
    @Test
    public void constructorWithParametersConstructsRight(){
        ref = new BookRef(title, authors, publisher, year);
        assertTrue(ref.getField("title").equals(title));
        assertTrue(ref.getField("authors").equals(authors));
        assertTrue(ref.getField("publisher").equals(publisher));
        assertEquals(year, ref.getYear());
    }
}
