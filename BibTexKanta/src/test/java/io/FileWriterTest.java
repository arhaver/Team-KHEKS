package io;

import io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FileWriterTest {
    
    List<String> lines;
    String address;
    FileWriter filewriter;
    Path testfile;
    
    public FileWriterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lines = Arrays.asList("The first line.", "The second line.");
        address = "TestiTiedosto";
        filewriter = new FileWriter();
        testfile = Paths.get(address);
    }
    
    @After
    public void tearDown(){
        try {
            Files.deleteIfExists(testfile);
        } catch (IOException ex) {
            Logger.getLogger(FileWriterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testWrite() throws Exception {
        
        boolean expResult = true;
        boolean result = filewriter.write(address, lines);
        assertEquals(expResult, result);
    }
    
}
