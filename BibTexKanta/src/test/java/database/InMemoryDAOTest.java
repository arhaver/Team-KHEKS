/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import reference.BookRef;
import reference.Reference;
import service.DaoService;

/**
 *
 * @author klint
 */
public class InMemoryDAOTest {
    
    InMemoryDAO dao;
    
    @Before
    public void setUp() {       
        dao = new InMemoryDAO();
        DaoService ds = new DaoService();
        dao.setDaoService(ds);
    }
    
    @Test
    public void InitializingWorks(){
        assertTrue(dao.findAll() != null);
    }
    
    @Test
    public void AddingWorks(){
        Reference ref = new BookRef();
        ref.setField("title", "title");
        ref.setField("authors", "title");
        ref.setField("publisher", "title");
        ref.setYear(1999);
        assertTrue(dao.add(ref));
        assertEquals(dao.findAll().size(), 1);
    }
}
