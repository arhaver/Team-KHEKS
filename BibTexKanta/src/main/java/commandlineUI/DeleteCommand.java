/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.editing.ChooseMenu;
import database.DAO;
import io.IO;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class DeleteCommand implements Command {
    
    private IO io;
    private DAO[] daos;
    private ChooseMenu chooseMenu;
    
    public DeleteCommand(IO io, ChooseMenu chooseMenu, DAO... daos) {
        this.io = io;
        this.chooseMenu = chooseMenu;
        this.daos = daos;
    }

    @Override
    public boolean execute(Reference toDelete) {
        String commitment = io.readLine("Haluatko varmasti poistaa viitteen " + toDelete.getField("title") + " (k/e)?");
        if (commitment.equals("k"))
        {
            for (DAO d : daos) d.remove(toDelete);
            chooseMenu.removeReference(toDelete);
            io.print("\nViite poistettu onnistuneesti!\n");
            
            return false;
        } 
        
        io.print("Peruutetaan");
        
        return true; 
    }
}
