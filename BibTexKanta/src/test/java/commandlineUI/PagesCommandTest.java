package commandlineUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import io.StubIO;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.InproceedingsRef;

/**
 *
 * @author Arto
 */
public class PagesCommandTest {

    private ArrayList<String> lines;
    private StubIO io;
    private InproceedingsRef ref;
    private PagesCommand command;
    private String pages;

    public PagesCommandTest() {
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
        command = new PagesCommand(io);
        ref = new InproceedingsRef();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validBooktitleIsAdded() {
        pages = "15-20";
        lines.add(pages);

        command.execute(ref);
        assertEquals(pages, ref.getField("pages"));
    }

    @Test
    public void invalidBooktitleIsNotAdded() {
        pages = "15";
        lines.add(pages);

        command.execute(ref);
        assertEquals(null, ref.getField("pages"));
    }

    @Test
    public void ifInvalidBooktitleIsGivenErrorMessageIsPrinted() {
        pages = "15";
        lines.add(pages);

        command.execute(ref);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '" + pages + "' virheellinen\n"));
    }

}
