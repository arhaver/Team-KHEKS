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
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import service.BibTexIdService;

/**
 *
 * @author mikko
 */
public class ListerCommandTest {

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
    ListerCommand lc;

    @Before
    public void setUp() {
        service = new BibTexIdService();
        bdao = new InMemoryDAO<>();
        adao = new InMemoryDAO<>();
        idao = new InMemoryDAO<>();
        lines = new ArrayList<>();
        io = new StubIO(lines);

    }

    @Test
    public void testExecute() {
        lines.add("q");
        lc = new ListerCommand(io, bdao, adao, idao, service);
        BookRef ref = new BookRef();
        ref.setField("title", "title");
        ref.setField("authors", "title");
        ref.setField("publisher", "title");
        ref.setYear(1999);
        bdao.add(ref);
        assertEquals(true, lc.execute(ref));
    }

}
