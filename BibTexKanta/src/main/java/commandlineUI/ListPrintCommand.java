/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.editing.ChooseEditedMenu;
import database.DAO;
import io.IO;
import java.util.HashMap;
import java.util.Map;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class ListPrintCommand implements Command {
    
    private IO io;
    private HashMap<String, Reference> refMap;
    private DAO[] daos;

    
    public ListPrintCommand(IO io, DAO... daos) {
        this.io = io;
        this.daos = daos;
    }
    
    public void setRefMap(HashMap<String, Reference> refMap){
        this.refMap = refMap;
    }

    @Override
    public boolean execute(Reference ref) {
        PrintRef printer = new PrintRef(daos[0], daos[1], io);
        
        io.print("Listaus alkaa:\n");
        for (String s : refMap.keySet()) {
            if (s.equals("q"))
                continue;
            io.print("Viite " + s + ":\n");
            printer.printAnyRef(refMap.get(s));           
        }
           
        return true; 
    }
}