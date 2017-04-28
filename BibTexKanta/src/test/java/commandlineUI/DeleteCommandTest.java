package commandlineUI;

import database.DAO;
import io.StubIO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import reference.BookRef;
import reference.Reference;

/**
 *
 * @author klint
 */
public class DeleteCommandTest {
    
    private StubIO io;
    private List<String> commands;
    
    private DeleteCommand dc;
    private DAO dao;
    
    private Reference ref;
    
    @Before
    public void setUp() {
        commands = new ArrayList<>();
        io = new StubIO(commands);
        
        dao = mock(DAO.class);
        
        dc = new DeleteCommand(io, dao);
        
        ref = new BookRef();
        ref.setField("title", "Title");
    }
    
    @Test
    public void cancelCancels(){
        commands.add("e");
        
        assertTrue(dc.execute(ref));
        
        assertTrue(io.outputsContainsLine("Haluatko varmasti poistaa viitteen " + ref.getField("title") + " (k/e)?"));
        assertTrue(io.outputsContainsLine("Peruutetaan"));
    }
    
    @Test
    public void acceptDeletes(){
        commands.add("k");
        
        assertTrue(!dc.execute(ref));
        
        assertTrue(io.outputsContainsLine("\nViite poistettu onnistuneesti!\n"));
        verify(dao, times(1)).remove(eq(ref));
    }
}
