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
import reference.BookRef;

/**
 *
 * @author mikko
 */
public class BookAdderTest {

    ArrayList<String> lines;
    DAO<BookRef> bookDAO;
    StubIO io;
    BookAdder bookAdder;

    public BookAdderTest() {
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
        bookAdder = new BookAdder(bookDAO, io);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bookCanBeAdded() {
        lines.add("1");
        lines.add("Kirja1");
        lines.add("2");
        lines.add("Kirjailija Yksi");
        lines.add("3");
        lines.add("1970");
        lines.add("4");
        lines.add("Otava");
        lines.add("7");

        bookAdder.addBookToDB();
        assertEquals("Viite lisätty onnistuneesti\n", io.getLastPrint());
    }

    @Test
    public void tooShortTitleCannotBeAdded() {
        lines.add("1");
        lines.add("K");
        lines.add("9");
        
        io.setTestString("Lisäys 'K' virheellinen\n");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void negativeYearCannotBeAdded() {
        lines.add("3");
        lines.add("-1");
        lines.add("9");
        
        io.setTestString("Lisäys '-1' virheellinen\n");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void tooShortAuthorNameCannotBeAdded() {
        lines.add("2");
        lines.add("A");
        lines.add("9");
        
        io.setTestString("Lisäys 'A' virheellinen\n");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void tooShortPublisherCannotBeAdded() {
        lines.add("4");
        lines.add("P");
        lines.add("9");
        
        io.setTestString("Lisäys 'P' virheellinen\n");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void tooShortPublisherAddressCannotBeAdded() {
        lines.add("5");
        lines.add("A");
        lines.add("9");
        
        io.setTestString("Lisäys 'A' virheellinen\n");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void emptyBookReferenceCannotBeAdded() {
        lines.add("7");
        lines.add("9");
        
        io.setTestString("Tallennus epäonnistui\n");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void userCanCheckTitleHasBeenAdded() {
        lines.add("1");
        lines.add("EkaNimi");
        lines.add("6");
        lines.add("9");
        
        io.setTestString("Nimi: EkaNimi");
        
        bookAdder.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }
}
