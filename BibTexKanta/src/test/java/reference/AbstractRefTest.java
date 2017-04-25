package reference;

import database.InMemoryDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author klint
 */
public class AbstractRefTest {
    
    BookRef book1;
    BookRef book2;
    BookRef book3;
    BookRef book4;
    
    @Before
    public void setUp() {
        book1 = new BookRef();
        book1.setField("title", "title1");
        book1.setField("authors", "title2");
        book1.setField("publisher", "title3");
        book1.setYear(1990);
        
        book2 = new BookRef();
        book2.setField("title", "title1");
        book2.setField("authors", "title2");
        book2.setField("publisher", "title3");
        book2.setYear(1990);
        
        book3 = new BookRef();
        book3.setField("title", "title2");
        book3.setField("authors", "title2");
        book3.setField("publisher", "title3");
        book3.setYear(1990);
        
        book4 = new BookRef();
        book4.setField("title", "title1");
        book4.setField("authors", "title2");
        book4.setField("publisher", "title3");
        book4.setYear(1991);
    }
    
    @Test
    public void equalsWorks(){
        assertTrue(!book1.equals(null));
        assertTrue(!book1.equals(new InMemoryDAO()));
        assertTrue(!book1.equals(new ArticleRef()));
        assertTrue(book1.equals(book1));
        assertTrue(book1.equals(book2));
        assertTrue(!book1.equals(book3));
        assertTrue(!book1.equals(book4));
    }
}
