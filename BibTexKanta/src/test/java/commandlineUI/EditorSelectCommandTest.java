package commandlineUI;

import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import database.DAO;
import database.InMemoryDAO;
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

public class EditorSelectCommandTest {
    
    private EditorSelectCommand esc;
    
    private BookRef br;
    private ArticleRef ar;
    
    ArticleEditor ae;
    BookEditor be;
    
    @Before
    public void setUp() {
        ae = mock(ArticleEditor.class);
        be = mock(BookEditor.class);
        
        esc = new EditorSelectCommand(be, ae);
        
        br = new BookRef();
        ar = new ArticleRef();
    }
    
    @Test
    public void commandCallsRightEditor(){
        esc.execute(br);
        
        verify(be, times(1)).setRef(eq(br));
        verify(be, times(1)).execute(eq(br));
        verify(ae, times(0)).execute(any());
        verify(ae, times(0)).setRef(any());
        
        esc.execute(ar);
        
        verify(be, times(1)).setRef(eq(br));
        verify(be, times(1)).execute(eq(br));
        verify(ae, times(1)).execute(eq(ar));
        verify(ae, times(1)).setRef(eq(ar));
    }
    
}
