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
public class VolumeCommandTest {

    private ArrayList<String> lines;
    private StubIO io;
    private ArticleRef ref;
    private VolumeCommand command;
    private String volume;

    public VolumeCommandTest() {
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
        command = new VolumeCommand(io);
        ref = new ArticleRef();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void validNumbertitleIsAdded() {
        volume = "5";
        lines.add(volume);

        command.execute(ref);
        assertEquals(volume, ref.getField("volume"));
    }

    @Test
    public void invalidNumbertitleIsNotAdded() {
        volume = "";
        lines.add(volume);

        command.execute(ref);
        assertEquals(null, ref.getField("volume"));
    }

    @Test
    public void ifInvalidBooktitleIsGivenErrorMessageIsPrinted() {
        volume = "";
        lines.add(volume);

        command.execute(ref);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '" + volume + "' virheellinen\n"));
    }

}
