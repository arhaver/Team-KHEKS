/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI.common;

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
public class NothingCommandTest {
    
    @Test
    public void NothingHappens(){
        NothingCommand nothing = new NothingCommand();
        assertTrue(nothing.execute(null));
    }
}
