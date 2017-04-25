/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI.menu;

import commandlineUI.menu.BibTexUI;
import bibtex.IBibtexTranslator;
import database.DAO;
import database.InMemoryDAO;
import io.IFilewriter;
import io.StubIO;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.ArticleRef;
import reference.BookRef;

/**
 *
 * @author klint
 */
public class BibTexUITest {

    private class StubFilewriter implements IFilewriter {

        @Override
        public boolean write(String address, List<String> lines) throws Exception {
            if (address.equals("virhe")) {
                throw new Exception("testivirhe");
            }

            return true;
        }
    }

    private class StubBibtexTranslator implements IBibtexTranslator {

        @Override
        public List<String> bibTex(DAO... daos) {
            return null;
        }

        @Override
        public List<String> makeBookBibTex(DAO<BookRef> bookDAO, List<String> lines) {
            return null;
        }

        @Override
        public List<String> makeArticleBibTex(DAO<ArticleRef> articleDAO, List<String> lines) {
            return null;
        }
    }

    IBibtexTranslator t = new StubBibtexTranslator();
    IFilewriter w = new StubFilewriter();

    StubIO io;
    List<String> komennot;
    BibTexUI ui;

    @Before
    public void setUp() {
        komennot = new ArrayList<>();
        io = new StubIO(komennot);
        ui = new BibTexUI(t, w, io, new InMemoryDAO<>(), new InMemoryDAO<>());
    }

    @Test
    public void validInputWorks() {
        komennot.add("1");
        komennot.add("oikea");
        komennot.add("q");

        ui.execute(null);

        assertTrue(io.outputsContainsLine("BibTex tulostettu tiedostoon: oikea"));
        assertEquals(io.howManyInputsRead(), 2);
    }

    @Test
    public void invalidInputShowsError() {
        komennot.add("1");
        komennot.add("virhe");
        komennot.add("q");

        ui.execute(null);

        assertTrue(io.outputsContainsLine("Virhe BibTex-tiedostoa luodessa: testivirhe"));
        assertEquals(io.howManyInputsRead(), 3);
    }

    @Test
    public void illegalInputShowsError() {
        komennot.add("1231231");
        komennot.add("q");

        ui.execute(null);

        assertTrue(io.outputsContainsLine("Epävalidi operaatio"));
    }
}
