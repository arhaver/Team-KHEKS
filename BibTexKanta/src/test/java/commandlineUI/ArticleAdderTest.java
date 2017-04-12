package commandlineUI;

import database.DAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.StubIO;
import database.Database;
import database.InMemoryDAO;
import java.util.ArrayList;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class ArticleAdderTest {

    ArrayList<String> lines;
    DAO<Reference> articleDAO;
    StubIO io;
    ArticleAdder articleAdder;

    public ArticleAdderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        lines = new ArrayList<>();
        articleDAO = new InMemoryDAO<>();
        io = new StubIO(lines);
        articleAdder = new ArticleAdder(articleDAO, io);
    }

    @After
    public void tearDown() {
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
        lines.add("11");

        articleAdder.addArticleToDB();
        assertEquals("Viite lisätty onnistuneesti\n", io.getLastPrint());
    }

    @Test
    public void volumeCanBeAdded() {
        lines.add("7");
        lines.add("25");
        lines.add("12");
        lines.add("13");

        articleAdder.addArticleToDB();
        assertEquals(true, io.getPrintedLines().contains("volume: 25\n"));
    }

    @Test
    public void numberCanBeAdded() {
        lines.add("8");
        lines.add("1b");
        lines.add("12");
        lines.add("13");

        articleAdder.addArticleToDB();
        assertEquals(true, io.getPrintedLines().contains("number: 1b\n"));
    }

    @Test
    public void pagesCanBeAdded() {
        lines.add("10");
        lines.add("112-145");
        lines.add("12");
        lines.add("13");

        articleAdder.addArticleToDB();
        assertEquals(true, io.getPrintedLines().contains("pages: 112-145\n"));
    }

    @Test
    public void bibTexIdCanBeAdded() {
        lines.add("6");
        lines.add("guys2016");
        lines.add("12");
        lines.add("13");

        articleAdder.addArticleToDB();
        assertEquals(true, io.getPrintedLines().contains("BibTexId: guys2016\n"));
    }
}
