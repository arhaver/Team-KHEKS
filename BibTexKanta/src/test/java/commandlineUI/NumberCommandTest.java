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
import reference.ArticleRef;

/**
 *
 * @author Arto
 */
public class NumberCommandTest {

    private ArrayList<String> lines;
    private StubIO io;
    private ArticleRef ref;
    private NumberCommand command;
    private String number;

    public NumberCommandTest() {
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
        command = new NumberCommand(io);
        ref = new ArticleRef();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validNumbertitleIsAdded() {
        number = "5";
        lines.add(number);

        command.execute(ref);
        assertEquals(number, ref.getField("number"));
    }

    @Test
    public void invalidNumbertitleIsNotAdded() {
        number = "";
        lines.add(number);

        command.execute(ref);
        assertEquals(null, ref.getField("number"));
    }

    @Test
    public void ifInvalidBooktitleIsGivenErrorMessageIsPrinted() {
        number = "";
        lines.add(number);

        command.execute(ref);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '" + number + "' virheellinen\n"));
    }

}
