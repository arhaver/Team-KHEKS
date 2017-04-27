/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI.menu.editing;

import commandlineUI.PrintRef;
import database.InMemoryDAO;
import io.StubIO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;
import service.BibTexIdService;

/**
 *
 * @author mikko
 */
public class ChooseEditedMenuTest {
    List<String> lines;
    StubIO io;
    InMemoryDAO<BookRef> bdao;
    InMemoryDAO<ArticleRef> adao;
    InMemoryDAO<InproceedingsRef> idao;
    PrintRef pr;
    BookRef bref;
    ArticleRef aref;
    InproceedingsRef iref;
    ChooseEditedMenu cem;
    BibTexIdService service;
    
    public ChooseEditedMenuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        bdao = new InMemoryDAO<>();
        adao = new InMemoryDAO<>();
        idao = new InMemoryDAO<>();
        lines = new ArrayList<String>();
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSetSelectionSetsCorrectSelection() {
        io = new StubIO(lines);
        service =  new BibTexIdService();
        cem = new ChooseEditedMenu(io, service, adao, bdao, idao);
        HashMap<String, Reference> refMap = new HashMap<>();
                   
        BookRef myBook = new BookRef();
        myBook.setField("title", "kirja1");
        myBook.setField("authors", "jätkät");
        myBook.setField("publisher", "otava");
        myBook.setYear(1999);
        refMap.put("1", myBook);       
        cem.setReferences(refMap);
        assertEquals(true, cem.setSelection("1"));    
    }
    
        @Test
    public void testGetSelectionGetsSelection() {
        io = new StubIO(lines);
        service =  new BibTexIdService();
        cem = new ChooseEditedMenu(io, service, adao, bdao, idao);
        HashMap<String, Reference> refMap = new HashMap<>();
                   
        BookRef myBook = new BookRef();
        myBook.setField("title", "kirja1");
        myBook.setField("authors", "jätkät");
        myBook.setField("publisher", "otava");
        myBook.setYear(1999);
        refMap.put("1", myBook);       
        cem.setReferences(refMap);
        cem.setSelection("1");
        assertEquals("1", cem.getSelection());    
    }
    
    @Test
    public void testSetSelectionDoesNotSetIncorrectSelection() {
        io = new StubIO(lines);
        service =  new BibTexIdService();
        cem = new ChooseEditedMenu(io, service, adao, bdao, idao);
        HashMap<String, Reference> refMap = new HashMap<>();
                   
        BookRef myBook = new BookRef();
        myBook.setField("title", "kirja1");
        myBook.setField("authors", "jätkät");
        myBook.setField("publisher", "otava");
        myBook.setYear(1999);
        refMap.put("1", myBook);       
        cem.setReferences(refMap);
        assertEquals(false, cem.setSelection("2"));    
    }    

    @Test
    public void testRemoveReferenceRemovesReference() {
        io = new StubIO(lines);
        service =  new BibTexIdService();
        cem = new ChooseEditedMenu(io, service, adao, bdao, idao);
        HashMap<String, Reference> refMap = new HashMap<>();
                   
        BookRef myBook = new BookRef();
        myBook.setField("title", "kirja1");
        myBook.setField("authors", "jätkät");
        myBook.setField("publisher", "otava");
        myBook.setYear(1999);
        refMap.put("1", myBook);       
        cem.setReferences(refMap);
        assertEquals(true, cem.RemoveReference("1"));    
    } 
    
    @Test
    public void testReferenceCanBeDeleted() {
        lines.add("l");
        lines.add("1");
        lines.add("d");
        lines.add("k");
        lines.add("q");
        io = new StubIO(lines);
        service =  new BibTexIdService();
        HashMap<String, Reference> refMap = new HashMap<>();
                   
        BookRef myBook = new BookRef();
        myBook.setField("title", "kirja1");
        myBook.setField("authors", "jätkät");
        myBook.setField("publisher", "otava");
        myBook.setYear(1999);
        refMap.put("1", myBook);  
        cem = new ChooseEditedMenu(io, service, adao, bdao, idao);
        cem.setReferences(refMap);
        cem.execute(null);
        assertEquals(true, io.outputsContainsLine("\nViite poistettu onnistuneesti!\n"));    
    }
}
