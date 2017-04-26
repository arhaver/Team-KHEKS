
package bibtex;

import database.DAO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;

public class TranslatorTest implements IBibtexTranslator{
    
    DAO<BookRef> bookDAO;
    DAO<ArticleRef> articleDAO;
    DAO<InproceedingsRef> inDAO;
    List<String> list;
    
    public TranslatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Override
    public List<String> makeBookBibTex(DAO<BookRef> bookDAO, List<String> lines) {
        return null;}

    @Override
    public List<String> makeArticleBibTex(DAO<ArticleRef> articleDAO, List<String> lines) {
        return null;}

    @Override
    public List<String> makeInproceedingsBibTex(DAO<InproceedingsRef> inDAO, List<String> lines) {
        return null;}
    
    @Test
    public void returnsNullIfbookDAOnull() {
        bookDAO = null;
        List<String> list = new ArrayList<String>();
        list = makeBookBibTex(bookDAO, list);
        assertEquals(true, list == null);
    }
    
    @Test
    public void returnsNullIfarticleDAOnull() {
        articleDAO = null;
        List<String> list = new ArrayList<String>();
        list = makeArticleBibTex(articleDAO, list);
        assertEquals(true, list == null);
    }
    
    @Test
    public void returnsNullIfinproceedingsDAOnull() {
        inDAO = null;
        List<String> list = new ArrayList<String>();
        list = makeInproceedingsBibTex(inDAO, list);
        assertEquals(true, list == null);
    }
}
