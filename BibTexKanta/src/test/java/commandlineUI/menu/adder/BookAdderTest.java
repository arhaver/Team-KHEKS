package commandlineUI.menu.adder;

import commandlineUI.menu.adder.BookAdder;
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

    @Before
    public void setUp() {
        lines = new ArrayList<>();
        bookDAO = new InMemoryDAO<>();
        io = new StubIO(lines);
        bookAdder = new BookAdder(bookDAO, io);
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

        bookAdder.execute(null);
        assertEquals("Viite lisätty onnistuneesti\n", io.getLastPrint());
    }

    @Test
    public void tooShortTitleCannotBeAdded() {
        lines.add("1");
        lines.add("K");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'K' virheellinen\n"));
    }

    @Test
    public void negativeYearCannotBeAdded() {
        lines.add("3");
        lines.add("-1");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '-1' virheellinen\n"));
    }

    @Test
    public void tooShortAuthorNameCannotBeAdded() {
        lines.add("2");
        lines.add("A");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'A' virheellinen\n"));
    }

    @Test
    public void tooShortPublisherCannotBeAdded() {
        lines.add("4");
        lines.add("P");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'P' virheellinen\n"));
    }

    @Test
    public void tooShortPublisherAddressCannotBeAdded() {
        lines.add("5");
        lines.add("A");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'A' virheellinen\n"));
    }

    @Test
    public void emptyBookReferenceCannotBeAdded() {
        lines.add("7");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Tallennus epäonnistui\n"));
    }

    @Test
    public void userCanCheckTitleHasBeenAdded() {
        lines.add("1");
        lines.add("EkaNimi");
        lines.add("8");
        lines.add("9");
        
        bookAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("title: EkaNimi\n"));
    }
}
