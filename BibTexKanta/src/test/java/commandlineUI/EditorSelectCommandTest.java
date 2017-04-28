package commandlineUI;

import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import commandlineUI.menu.adder.InproceedingsEditor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;

public class EditorSelectCommandTest {
    
    private EditorSelectCommand esc;
    
    private BookRef br;
    private ArticleRef ar;
    private InproceedingsRef ir;
    
    private ArticleEditor ae;
    private BookEditor be;
    private InproceedingsEditor ie;
    
    @Before
    public void setUp() {
        ae = mock(ArticleEditor.class);
        be = mock(BookEditor.class);
        ie = mock(InproceedingsEditor.class);
        
        esc = new EditorSelectCommand(be, ae, ie);
        
        br = new BookRef();
        ar = new ArticleRef();
        ir = new InproceedingsRef();
    }
    
    @Test
    public void commandCallsRightEditor(){
        esc.execute(br);
        
        verify(be, times(1)).setRef(eq(br));
        verify(be, times(1)).execute(eq(br));
        verify(ae, times(0)).execute(any());
        verify(ae, times(0)).setRef(any());
        verify(ie, times(0)).execute(any());
        verify(ie, times(0)).setRef(any());
        
        esc.execute(ar);
        
        verify(be, times(1)).setRef(eq(br));
        verify(be, times(1)).execute(eq(br));
        verify(ae, times(1)).execute(eq(ar));
        verify(ae, times(1)).setRef(eq(ar));
        verify(ie, times(0)).execute(any());
        verify(ie, times(0)).setRef(any());
        
        esc.execute(ir);
        
        verify(be, times(1)).setRef(eq(br));
        verify(be, times(1)).execute(eq(br));
        verify(ae, times(1)).execute(eq(ar));
        verify(ae, times(1)).setRef(eq(ar));
        verify(ie, times(1)).execute(eq(ir));
        verify(ie, times(1)).setRef(eq(ir));
    }
    
}
