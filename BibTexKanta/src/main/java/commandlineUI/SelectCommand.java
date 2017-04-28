/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.Menu;
import commandlineUI.menu.editing.ChooseEditedMenu;
import database.DAO;
import io.IO;
import reference.Reference;
import service.BibTexIdService;

/**
 *
 * @author mikko
 */
public class SelectCommand implements Command {
    
    private IO io;
    private ChooseEditedMenu cemenu;

    
    public SelectCommand(IO io, ChooseEditedMenu cemenu) {
        this.io = io;
        this.cemenu = cemenu;
    }

    @Override
    public boolean execute(Reference ref) {
        String selection =  io.readLine("Anna viitteen numero (0 lopettaa):\n");
        
        while (!cemenu.setSelection(selection)) {      
            selection = io.readLine("Anna toimiva viitteen numero (0 lopettaa)");                
        } 
        if (!selection.toLowerCase().matches("q"))
            io.print("Valittu: " + selection);
        return true; 
    }
}
