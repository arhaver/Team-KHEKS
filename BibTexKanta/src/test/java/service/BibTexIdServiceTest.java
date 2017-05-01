package service;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;


public class BibTexIdServiceTest {
    
    BookRef book1, book2;
    BibTexIdService service;  
    
    public BibTexIdServiceTest() {
    }
    
    @Before
    public void setUp() {
        book1 = new BookRef("title", "author", "publisher", 2006);
        book1.setField("bibTexId", "au2006");
        book2 = new BookRef("title2", "author2", "publisher2", 2006);
    }
    
    @Test
    public void testValidid() {
        service = new BibTexIdService();
        assertTrue(service.validid("au2006"));
        service.generateId(book1);
        assertFalse(service.validid("au2006"));
    }
    
    @Test
    public void testSaveId() {
        service = new BibTexIdService();
        service.saveId("testid",book2);
        assertTrue(book2.getField("bibTexId").equals("testid"));
    }
    
    @Test
    public void testGenerateId() {
        service = new BibTexIdService();
        service.generateId(book2);
        assertTrue(book2.getField("bibTexId").equals("au2006"));
    } 
    
    @Test
    public void testGenerateIdGeneratesUniqueId() {
        service = new BibTexIdService();
        service.generateId(book1);
        service.generateId(book2);
        assertTrue(book2.getField("bibTexId").equals("au2006_2"));
    } 

}
