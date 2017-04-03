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

/**
 *
 * @author klint
 */
public class InMemoryDAOTest {
    
    InMemoryDAO dao;
    
    @Before
    public void setUp() {
        dao = new InMemoryDAO();
    }
    
    @Test
    public void InitializingWorks(){
        assertTrue(dao.findAll() != null);
    }
    
    @Test
    public void AddingWorks(){
        assertTrue(dao.add("teststring"));
        assertEquals(dao.findAll().size(), 1);
    }
}
