package commandlineUI.menu.adder;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;

public class AbstractEditorTest {
    
    private AbstractEditor editor;
    private BookRef book1;
    
    @Before
    public void setUp() {
        editor = new AbstractEditor(null, null, null, null);
        
        book1 = new BookRef();
        book1.setField("title", "asdasd");
        book1.setField("authors", "asdasdasd");
        book1.setField("publisher", "asdasddsa");
        book1.setYear(1990);
        
        editor.setRef(book1);
    }
    
    @Test
    public void trueRefGivesOriginalRef(){
        assertTrue(editor.getTrueRef().equals(book1));
    }
    
    @Test
    public void editRefIsInitializedRight(){
        assertTrue(editor.getEditRef().equals(book1));
    }
    
    @Test
    public void editingEditRefDoesntChangeTrueRef(){
        editor.getEditRef().setYear(5);
        assertTrue(!editor.getEditRef().equals(book1));
    }
    
}
