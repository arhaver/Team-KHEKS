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
    public void tooShortTitleCannotBeAdded() {
        lines.add("1");
        lines.add("K");
        lines.add("13");
        
        articleAdder.addArticleToDB();
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'K' virheellinen\n"));
    }

    @Test
    public void negativeYearCannotBeAdded() {
        lines.add("3");
        lines.add("-1");
        lines.add("13");
        
        articleAdder.addArticleToDB();
        assertEquals(true, io.getPrintedLines().contains("Lisäys '-1' virheellinen\n"));
    }
}