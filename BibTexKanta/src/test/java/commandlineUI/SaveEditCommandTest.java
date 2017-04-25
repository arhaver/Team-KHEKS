
package commandlineUI;

import commandlineUI.menu.adder.AbstractEditor;
import io.StubIO;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import reference.BookRef;
import reference.Reference;

/**
 *
 * @author klint
 */
public class SaveEditCommandTest {
    
    private AbstractEditor editor;
    private StubIO io;
    
    private SaveEditCommand command;
    private BookRef book1;
    private BookRef edited;
    
    @Before
    public void setUp() {
        editor = mock(AbstractEditor.class);
        
        io = new StubIO(new ArrayList<>());
        
        book1 = new BookRef();
        book1.setField("title", "asdasd");
        book1.setField("authors", "asdasdasd");
        book1.setField("publisher", "asdasddsa");
        book1.setYear(1990);
        
        edited = new BookRef();
        edited.setField("title", "satsatsat");
        edited.setField("authors", "satsat");
        edited.setField("publisher", "satsatsatsa");
        edited.setYear(1900);
        
        command = new SaveEditCommand(editor, io);
    }
    
    @Test
    public void unchangedRefCanBeSaved(){
        when(editor.getTrueRef()).thenReturn(book1);
        when(editor.getEditRef()).thenReturn(book1);
        command.execute(null);
        
        assertTrue(!io.outputsContainsLine("Viite ei tallennettavassa muodossa."));
        assertTrue(rightFieldValues(book1, 1990, "title", "asdasd", "authors", "asdasdasd", "publisher", "asdasddsa"));
    }
    
    @Test
    public void validChangesCanBeSaved(){
        when(editor.getTrueRef()).thenReturn(book1);
        when(editor.getEditRef()).thenReturn(edited);
        command.execute(null);
        
        assertTrue(!io.outputsContainsLine("Viite ei tallennettavassa muodossa."));
        assertTrue(rightFieldValues(book1, 1900, "title", "satsatsat", "authors", "satsat", "publisher", "satsatsatsa"));
    }
    
    @Test
    public void invalidChangesArentSaved(){
        when(editor.getTrueRef()).thenReturn(edited);
        when(editor.getEditRef()).thenReturn(new BookRef());
        command.execute(null);
        
        assertTrue(io.outputsContainsLine("Viite ei tallennettavassa muodossa."));
        assertTrue(rightFieldValues(edited, 1900, "title", "satsatsat", "authors", "satsat", "publisher", "satsatsatsa"));
    }
    
    @Test
    public void commandStopsMenuRightTimes(){
        when(editor.getTrueRef()).thenReturn(book1).thenReturn(book1);
        when(editor.getEditRef()).thenReturn(new BookRef()).thenReturn(book1);
        
        assertTrue(command.execute(null));
        assertTrue(!command.execute(null));
    }
    
    private boolean rightFieldValues(Reference ref, int year, String... fieldNValue){
        for(int i = 0 ; i < fieldNValue.length ; i += 2){
            String field = ref.getField(fieldNValue[i]);
            String value = fieldNValue[i+1];
            if(!field.equals(value)) return false;
        }
        
        return ref.getYear() == year;
    }

    
}
