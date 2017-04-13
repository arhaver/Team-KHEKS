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
import reference.Reference;

public class MainMenuTest {
    
    ArrayList<String> lines;
    DAO<Reference> bookDAO;
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
        io = new StubIO(lines);
        mainMenu = new MainMenu(bookDAO, io);
    }
    
    @After
    public void tearDown() {
    }

/**    @Test
    public void testLoop() {
        System.out.println("loop");
        MainMenu instance = null;
        instance.loop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
**/
    @Test
    public void startingProgramPrintsWelcomeMessage() {
        lines.add("q");
        
        mainMenu.openMainMenu();
        assertEquals(true, io.getPrintedLines().contains("\nTERVETULOA!"));
    }
    
}
