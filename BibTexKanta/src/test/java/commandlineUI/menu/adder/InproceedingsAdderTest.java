package commandlineUI.menu.adder;

import database.DAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import io.StubIO;
import database.InMemoryDAO;
import java.util.ArrayList;
import reference.InproceedingsRef;
import service.BibTexIdService;


public class InproceedingsAdderTest {

    ArrayList<String> lines;
    DAO<InproceedingsRef> inproceedingsDAO;
    StubIO io;
    InproceedingsAdder inproceedingsAdder;
    BibTexIdService service;

    @Before
    public void setUp() {
        lines = new ArrayList<>();
        inproceedingsDAO = new InMemoryDAO<>();
        io = new StubIO(lines);
        service = new BibTexIdService();
        inproceedingsAdder = new InproceedingsAdder(inproceedingsDAO, io, service);
    }

    @Test
    public void inproceedingsCanBeAdded() {
        lines.add("1");
        lines.add("Artikkeli1");
        lines.add("2");
        lines.add("Kirjoittaja Yksi");
        lines.add("3");
        lines.add("1970");
        lines.add("7");
        lines.add("Julkaisun nimi1");
        lines.add("9");

        inproceedingsAdder.execute(null);
        assertEquals("Viite lisätty onnistuneesti\n", io.getLastPrint());
    }

    @Test
    public void tooShortTitleCannotBeAdded() {
        lines.add("1");
        lines.add("K");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'K' virheellinen\n"));
    }

    @Test
    public void negativeYearCannotBeAdded() {
        lines.add("3");
        lines.add("-1");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '-1' virheellinen\n"));
    }

    @Test
    public void tooShortAuthorNameCannotBeAdded() {
        lines.add("2");
        lines.add("A");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'A' virheellinen\n"));
    }

    @Test
    public void tooShortPublisherCannotBeAdded() {
        lines.add("4");
        lines.add("P");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'P' virheellinen\n"));
    }

    @Test
    public void tooShortPublisherAddressCannotBeAdded() {
        lines.add("5");
        lines.add("A");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'A' virheellinen\n"));
    }
    
    @Test
    public void tooShortBooktitleCannotBeAdded() {
        lines.add("7");
        lines.add("A");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys 'A' virheellinen\n"));
    }
    
    @Test
    public void tooShortPagesCannotBeAdded() {
        lines.add("8");
        lines.add("55");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '55' virheellinen\n"));
    }

    @Test
    public void emptyInproceedingsReferenceCannotBeAdded() {
        lines.add("9");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("Tallennus epäonnistui\n"));
    }

    @Test
    public void userCanCheckTitleHasBeenAdded() {
        lines.add("1");
        lines.add("EkaNimi");
        lines.add("10");
        lines.add("11");

        inproceedingsAdder.execute(null);
        assertEquals(true, io.getPrintedLines().contains("title: EkaNimi\n"));
    }
}
