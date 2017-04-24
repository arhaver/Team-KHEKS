/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.BibTexUI;
import bibtex.IBibtexTranslator;
import database.DAO;
import database.InMemoryDAO;
import io.IFilewriter;
import io.IO;
import io.StubIO;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;

/**
 *
 * @author klint
 */
public class BibTexUITest {
    
    private class StubFilewriter implements IFilewriter{
        @Override
        public boolean write(String address, List<String> lines) throws Exception {
            if(address.equals("virhe")){
                throw new Exception("testivirhe");
            }
            
            return true;
        }
    }
    private class StubBibtexTranslator implements IBibtexTranslator{
        @Override
        public List<String> bibTex(DAO... daos) {
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
    public void validInputWorks(){
        komennot.add("1");
        komennot.add("oikea");
        komennot.add("q");
        
        ui.printLoop();
        
        assertTrue(io.outputsContainsLine("BibTex tulostettu tiedostoon: oikea"));
        assertEquals(io.howManyInputsRead(), 2);
    }
    
    @Test
    public void invalidInputShowsError(){
        komennot.add("1");
        komennot.add("virhe");
        komennot.add("q");
        
        ui.printLoop();
        
        assertTrue(io.outputsContainsLine("Virhe BibTex-tiedostoa luodessa: testivirhe"));
        assertEquals(io.howManyInputsRead(), 3);
    }
    
    @Test
    public void illegalInputShowsError(){
        komennot.add("1231231");
        komennot.add("q");
        
        ui.printLoop();
        
        assertTrue(io.outputsContainsLine("Epävalidi operaatio"));
    }
}

