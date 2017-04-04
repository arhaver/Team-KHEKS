package commandlineUI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import io.StubIO;
import database.Database;
import java.util.ArrayList;

/**
 *
 * @author mikko
 */
public class BookAdderTest {

    ArrayList<String> lines;
    Database db;

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
        db = new Database("SD");
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

        StubIO io = new StubIO(lines);
        BookAdder ba = new BookAdder(db, io);
        ba.addBookToDB();
        assertEquals("Viite lisätty onnistuneesti\n", io.getLastPrint());
    }

    @Test
    public void tooShortTitleCannotBeAdded() {
        lines.add("1");
        lines.add("K");
        lines.add("7");
        StubIO io = new StubIO(lines);
        io.setTestString("Lisäys 'K' virheellinen\n");
        BookAdder ba = new BookAdder(db, io);
        ba.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void negativeYearCannotBeAdded() {
        lines.add("3");
        lines.add("-1");
        lines.add("7");
        StubIO io = new StubIO(lines);
        io.setTestString("Lisäys '-1' virheellinen\n");
        BookAdder ba = new BookAdder(db, io);
        ba.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void tooShortAuthorNameCannotBeAdded() {
        lines.add("2");
        lines.add("A");
        lines.add("7");
        StubIO io = new StubIO(lines);
        io.setTestString("Lisäys 'A' virheellinen\n");
        BookAdder ba = new BookAdder(db, io);
        ba.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void tooShortPublisherCannotBeAdded() {
        lines.add("4");
        lines.add("P");
        lines.add("7");
        StubIO io = new StubIO(lines);
        io.setTestString("Lisäys 'P' virheellinen\n");
        BookAdder ba = new BookAdder(db, io);
        ba.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }

    @Test
    public void tooShortPublisherAddressCannotBeAdded() {
        lines.add("5");
        lines.add("A");
        lines.add("7");
        StubIO io = new StubIO(lines);
        io.setTestString("Lisäys 'A' virheellinen\n");
        BookAdder ba = new BookAdder(db, io);
        ba.addBookToDB();
        assertEquals(true, io.testHasBeenPrinted());
    }
}
