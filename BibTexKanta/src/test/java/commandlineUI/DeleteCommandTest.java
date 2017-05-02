package commandlineUI;

import commandlineUI.menu.editing.ChooseMenu;
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
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;

/**
 *
 * @author klint
 */
public class DeleteCommandTest {
    
    private StubIO io;
    private List<String> commands;
    
    private DeleteCommand dc;
    private DeleteCommand dc2;
    private DeleteCommand dc3;
    private DAO dao;
    private ChooseMenu cm;
    
    private Reference ref;
    private Reference ref2;
    private Reference ref3;
    
    @Before
    public void setUp() {
        commands = new ArrayList<>();
        io = new StubIO(commands);
        
        dao = mock(DAO.class);
        cm = mock(ChooseMenu.class);
        
        dc = new DeleteCommand(io, cm, dao, null, null);
        dc2 = new DeleteCommand(io, cm, null, dao, null);
        dc3 = new DeleteCommand(io, cm, null, null, dao);
        
        ref = new BookRef();
        ref.setField("title", "Title");
        
        ref2 = new ArticleRef();
        ref2.setField("title", "Title");
        
        ref3 = new InproceedingsRef();
        ref3.setField("title", "Title");
    }
    
    @Test
    public void cancelCancels(){
        commands.add("e");
        
        assertTrue(dc.execute(ref));
        
        assertTrue(io.outputsContainsLine("Haluatko varmasti poistaa viitteen " + ref.getField("title") + " (k/e)?"));
        assertTrue(io.outputsContainsLine("Peruutetaan"));
        
        verify(dao, times(0)).remove(any());
        verify(cm, times(0)).removeReference(any());
    }
    
    @Test
    public void acceptDeletesOfBooks(){
        commands.add("k");
        
        assertTrue(!dc.execute(ref));
        
        assertTrue(io.outputsContainsLine("\nViite poistettu onnistuneesti!\n"));
        verify(dao, times(1)).remove(eq(ref));
        verify(cm, times(1)).removeReference(eq(ref));
    }
    
    @Test
    public void acceptDeletesOfArticles(){
        commands.add("k");
        
        assertTrue(!dc2.execute(ref2));
        
        assertTrue(io.outputsContainsLine("\nViite poistettu onnistuneesti!\n"));
        verify(dao, times(1)).remove(eq(ref2));
        verify(cm, times(1)).removeReference(eq(ref2));
    }
    
        @Test
    public void acceptDeletesOfInproceedings(){
        commands.add("k");
        
        assertTrue(!dc3.execute(ref3));
        
        assertTrue(io.outputsContainsLine("\nViite poistettu onnistuneesti!\n"));
        verify(dao, times(1)).remove(eq(ref3));
        verify(cm, times(1)).removeReference(eq(ref3));
    }
}
