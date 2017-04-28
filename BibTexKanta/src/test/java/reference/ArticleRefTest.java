/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arto
 */
public class ArticleRefTest {

    ArticleRef ref;

    String title;
    String authors;
    String volume;
    String publisher;
    String address;
    String journal;
    String number;
    String pages;
    String bibTexId;
    int year;

    @Before
    public void setUp() {
        ref = new ArticleRef();
        title = "Artikkelin nimi";
        authors = "Kirjoittajat";
        journal = "Julkaisun nimi";
        year = 1999;
        volume = "34 vk.";
        number = "3b";        
    }

    @Test
    public void constructorWithoutParametersSetsTitleNull() {
        assertEquals(null, ref.getField("title"));
    }

    @Test
    public void constructorWithoutParametersSetsAuthorsNull() {
        assertEquals(null, ref.getField("authors"));
    }

    @Test
    public void constructorWithoutParametersSetsBibTexIdNull() {
        assertEquals(null, ref.getField("bibTexId"));
    }

    @Test
    public void constructorWithoutParametersSetsJournalNull() {
        assertEquals(null, ref.getField("booktitle"));
    }

    @Test
    public void constructorWithoutParametersSetsPagesNull() {
        assertEquals(null, ref.getField("pages"));
    }

    @Test
    public void constructorWithoutParametersSetsPublisherNull() {
        assertEquals(null, ref.getField("publisher"));
    }

    @Test
    public void constructorWithoutParametersSetsAddressNull() {
        assertEquals(null, ref.getField("address"));
    }
    
    @Test
    public void constructorWithoutParametersSetsVolumeNull() {
        assertEquals(null, ref.getField("volume"));
    }
    
    @Test
    public void constructorWithoutParametersSetsNumberNull() {
        assertEquals(null, ref.getField("Number"));
    }

    @Test
    public void constructorWithoutParametersSetsYearMinusOne() {
        assertEquals(-1, ref.getYear());
    }

    @Test
    public void constructorWithoutParametersWithOutSettingIsNotDbReady() {
        assertTrue(!ref.readyForDb());
    }

    @Test
    public void setterSetsFieldCorrect() {
        ref.setField("title", title);
        assertEquals(title, ref.getField("title"));
    }

    @Test
    public void getterGivesNullIfFieldNameDoesNotExsist() {
        ref.setField("title", title);
        assertEquals(null, ref.getField("otsikko"));
    }
    
    @Test
    public void isNotReadyForDbWithoutTitle() {
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("volume", volume);
        ref.setField("journal", journal);
        ref.setField("number", number);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutAuthors() {
        ref.setField("title", title);
        ref.setYear(year);
        ref.setField("volume", volume);
        ref.setField("journal", journal);
        ref.setField("number", number);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutVolume() {
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("title", title);
        ref.setField("journal", journal);
        ref.setField("number", number);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutJournal() {
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("title", title);
        ref.setField("volume", volume);
        ref.setField("number", number);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutNumber() {
        ref.setField("authors", authors);
        ref.setYear(year);
        ref.setField("title", title);
        ref.setField("journal", journal);
        ref.setField("volume", volume);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isNotReadyForDbWithoutYear() {
        ref.setField("authors", authors);
        ref.setField("title", title);
        ref.setField("volume", volume);
        ref.setField("journal", journal);
        ref.setField("number", number);
        assertTrue(!ref.readyForDb());
    }
    
    @Test
    public void isReadyForDbWithTitleAuthorsYearJournalVolumeAndNumber() {
        ref.setField("authors", authors);
        ref.setField("title", title);
        ref.setField("volume", volume);
        ref.setField("journal", journal);
        ref.setField("number", number);
        ref.setYear(year);
        assertTrue(ref.readyForDb());
    }
    
    @Test
    public void constructorWithParametersConstructsRight(){
        ref = new ArticleRef(title, authors, volume, journal, number, year);
        assertTrue(ref.getField("title").equals(title));
        assertTrue(ref.getField("authors").equals(authors));
        assertTrue(ref.getField("volume").equals(volume));
        assertTrue(ref.getField("journal").equals(journal));
        assertTrue(ref.getField("number").equals(number));
        assertEquals(year, ref.getYear());
    }
}
