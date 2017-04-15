package commandlineUI;


import database.DAO;
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
import reference.BookRef;

public class MainMenuTest {
    
    ArrayList<String> lines;
    DAO<BookRef> bookDAO;
    DAO<ArticleRef> articleDAO;
    StubIO io;
    MainMenu mainMenu;
    
    public MainMenuTest() {
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
        bookDAO = new InMemoryDAO<>();
        articleDAO = new InMemoryDAO<>();
        io = new StubIO(lines);
        mainMenu = new MainMenu(articleDAO, bookDAO, io, null, null);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void startingProgramPrintsWelcomeMessage() {
        lines.add("q");
        
        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("\nTERVETULOA!"));
    }
    
    @Test
    public void openMainMenuCallsLoop() {
        lines.add("q");
        
        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("Päävalikko:\n"));
    }
    
    @Test
    public void optionOneOpensBookAdder() {
        lines.add("1");
        lines.add("9");
        lines.add("q");
        
        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("Kirjaviitteen lisääminen:\n"));
    }

    @Test
    public void optionTwoOpensArticleAdder() {
        lines.add("2");
        lines.add("13");
        lines.add("q");

        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("Artikkeliviitteen lisääminen:\n"));
    }
/*
    @Test
    public void optionTreeOpensReferenceLister() {
        lines.add("3");
        lines.add("13");
        lines.add("q");

        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("Artikkeliviitteen lisääminen:\n"));
    }
*/
    @Test
    public void optionFourOpensBibTexPrinter() {
        lines.add("4");
        lines.add("q");
        lines.add("Q");

        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("\nBibText-tiedoston tulostus:"));
    }

    @Test
    public void invalidOptionGivesErrorMessage() {
        lines.add("0");
        lines.add("q");

        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("\nVirheellinen komento!"));
    }
}
