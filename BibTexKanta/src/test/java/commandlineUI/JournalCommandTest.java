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
public class JournalCommandTest {
    
    private ArrayList<String> lines;
    private StubIO io;
    private ArticleRef ref;
    private JournalCommand command;
    private String journal;
    
    public JournalCommandTest() {
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
        command = new JournalCommand(io);
        ref = new ArticleRef();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void validBooktitleIsAdded() {
        journal = "Artikkelijulkaisu: testilehti";
        lines.add(journal);
        
        command.execute(ref);
        assertEquals(journal, ref.getField("journal"));
    }
    
    @Test
    public void invalidBooktitleIsNotAdded() {
        journal = "te";
        lines.add(journal);
        
        command.execute(ref);
        assertEquals(null, ref.getField("journal"));
    }
    
    @Test
    public void ifInvalidBooktitleIsGivenErrorMessageIsPrinted() {
        journal = "te";
        lines.add(journal);
        
        command.execute(ref);
        assertEquals(true, io.getPrintedLines().contains("Lisäys '" +journal+ "' virheellinen\n"));
    }
    
}
