/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import database.DAO;
import database.InMemoryDAO;
import io.ConsoleIO;
import io.IO;
import io.StubIO;
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
import static org.mockito.Mockito.*;

public class PrintRefTest {

    List<String> lines;
    StubIO io;
    InMemoryDAO<BookRef> bdao;
    InMemoryDAO<ArticleRef> adao;
    InMemoryDAO<InproceedingsRef> idao;
    PrintRef pr;
    BookRef bref;
    ArticleRef aref;
    InproceedingsRef iref;

    public PrintRefTest() {
    }

    @Before
    public void setUp() {
        bdao = new InMemoryDAO<>();
        adao = new InMemoryDAO<>();
        idao = new InMemoryDAO<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPrintAnyRefPrintsBookRef() {
        io = new StubIO(lines);
        pr = new PrintRef(bdao, adao, idao, io);
        bref = new BookRef();
        bref.setField("title", "title");
        bref.setField("authors", "title");
        bref.setField("publisher", "title");
        bref.setYear(1999);
        bdao.add(bref);
        List<BookRef> books = bdao.findAll();
        pr.printAnyRef(books.get(0));
        assertEquals(true, io.outputsContainsLine("Title: title"));

    }

    @Test
    public void testPrintAnyRefPrintsArticleRef() {
        io = new StubIO(lines);
        pr = new PrintRef(bdao, adao, idao, io);
        aref = new ArticleRef();
        aref.setField("title", "title");
        aref.setField("authors", "title");
        aref.setField("publisher", "title");
        aref.setField("journal", "somejournal");
        aref.setField("number", "1");
        aref.setField("volume", "12");
        aref.setField("pages", "12-14");
        aref.setYear(1999);
        adao.add(aref);
        List<ArticleRef> articles = adao.findAll();
        pr.printAnyRef(articles.get(0));
        assertEquals(true, io.outputsContainsLine("Journal: somejournal"));

    }

    @Test
    public void testPrintAnyRefPrintsInproceedingsRef() {
        io = new StubIO(lines);
        pr = new PrintRef(bdao, adao, idao, io);
        iref = new InproceedingsRef();
        iref.setField("title", "title");
        iref.setField("authors", "title");
        iref.setField("publisher", "title");
        iref.setField("booktitle", "some book");
        iref.setField("pages", "12-14");
        iref.setYear(1999);
        idao.add(iref);
        List<InproceedingsRef> ips = idao.findAll();
        pr.printAnyRef(ips.get(0));
        assertEquals(true, io.outputsContainsLine("Book Title: some book"));

    }

}
