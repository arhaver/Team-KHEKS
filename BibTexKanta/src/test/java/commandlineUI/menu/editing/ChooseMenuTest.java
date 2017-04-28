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
public class ChooseMenuTest {
    List<String> lines;
    StubIO io;
    InMemoryDAO<BookRef> bdao;
    InMemoryDAO<ArticleRef> adao;
    InMemoryDAO<InproceedingsRef> idao;
    PrintRef pr;
    BookRef bref;
    ArticleRef aref;
    InproceedingsRef iref;
    ChooseMenu cem;
    BibTexIdService service;
    
    private BookRef book1;
    
    @Before
    public void setUp() {
        bdao = new InMemoryDAO<>();
        adao = new InMemoryDAO<>();
        idao = new InMemoryDAO<>();
        lines = new ArrayList<>();
        
        io = new StubIO(lines);
        service =  new BibTexIdService();
        cem = new ChooseMenu(io, service, adao, bdao, idao);
        
        book1 = new BookRef("kirja1", "jätkät", "otava", 1999);
    }
    
    
    
}
