/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.editing.ChooseEditedMenu;
import database.DAO;
import io.IO;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class DeleteCommand implements Command {
    
    private IO io;
    private ChooseEditedMenu cemenu;
    private DAO[] daos;

    
    public DeleteCommand(IO io, ChooseEditedMenu cemenu, DAO... daos) {
        this.io = io;
        this.cemenu = cemenu;
        this.daos = daos;
    }

    @Override
    public boolean execute(Reference ref) {
        String selection =  cemenu.getSelection();
        String commitment = io.readLine("Haluatko varmasti poistaa viitteen " + selection + " (k/e)?");
        if (commitment.equals("k"))
        {
            Reference removable = cemenu.getSingleReference(selection);
            for (DAO d : daos)
                d.remove(removable);
            cemenu.RemoveReference(selection);
            io.print("\nViite poistettu onnistuneesti!\n");
        } 
        
         return true; 
    }
}
