package commandlineUI.menu.adder;

import commandlineUI.menu.adder.ArticleAdder;
import database.DAO;
import service.BibTexIdService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.StubIO;
import database.InMemoryDAO;
import java.util.ArrayList;
import reference.ArticleRef;
import service.DaoService;

/**
 *
 * @author mikko
 */
public class ArticleAdderTest {

    ArrayList<String> lines;
    DAO<ArticleRef> articleDAO;
    StubIO io;
    ArticleAdder articleAdder;

    @Before
    public void setUp() {
        lines = new ArrayList<>();
        articleDAO = new InMemoryDAO<>();
        io = new StubIO(lines);
        DaoService ds = new DaoService(articleDAO);
        articleDAO.setDaoService(ds);
        articleAdder = new ArticleAdder(articleDAO, io);
    }

    @Test
    public void articleCanBeAdded() {
        lines.add("1");
        lines.add("Artikkeli1");
        lines.add("2");
        lines.add("Kirjoittaja Yksi");
        lines.add("3");
        lines.add("1970");
        lines.add("9");
        lines.add("J. of Big Time Science");
        lines.add("7");
        lines.add("123");
        lines.add("8");
        lines.add("12b");
        lines.add("11");

        articleAdder.execute(null);
        assertEquals("Viite lisätty onnistuneesti\n", io.getLastPrint());
    }

    @Test
    public void volumeCanBeAdded() {
        lines.add("7");
        lines.add("25b");
        lines.add("12");
        lines.add("13");

        articleAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("volume: 25b\n"));
    }

    @Test
    public void numberCanBeAdded() {
        lines.add("8");
        lines.add("11b");
        lines.add("12");
        lines.add("13");

        articleAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("number: 11b\n"));
    }

    @Test
    public void pagesCanBeAdded() {
        lines.add("10");
        lines.add("112-145");
        lines.add("12");
        lines.add("13");

        articleAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("pages: 112-145\n"));
    }

    @Test
    public void bibTexIdCanBeAdded() {
        lines.add("6");
        lines.add("guys2016");
        lines.add("12");
        lines.add("13");

        articleAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("BibTexId: guys2016\n"));
    }
}
