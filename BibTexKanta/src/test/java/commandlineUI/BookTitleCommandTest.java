package commandlineUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import commandlineUI.BookTitleCommand;
import io.StubIO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.InproceedingsRef;
import reference.Reference;

/**
 *
 * @author Arto
 */
public class BookTitleCommandTest {

    private ArrayList<String> lines;
    private StubIO io;
    private InproceedingsRef ref;
    private BookTitleCommand command;
    private String booktitle;

    public BookTitleCommandTest() {
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
        io = new StubIO(lines);
        command = new BookTitleCommand(io);
        ref = new InproceedingsRef();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validBooktitleIsAdded() {
        booktitle = "Konferenssijulkaisu: testi";
        lines.add(booktitle);

        command.execute(ref);
        assertEquals(booktitle, ref.getField("booktitle"));
    }

    @Test
    public void invalidBooktitleIsNotAdded() {
        booktitle = "te";
        lines.add(booktitle);

        command.execute(ref);
        assertEquals(null, ref.getField("booktitle"));
    }

    @Test
    public void ifInvalidBooktitleIsGivenErrorMessageIsPrinted() {
        booktitle = "te";
        lines.add(booktitle);

        command.execute(ref);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '" + booktitle + "' virheellinen\n"));
    }

}
