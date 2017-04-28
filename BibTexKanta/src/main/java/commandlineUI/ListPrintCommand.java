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
import commandlineUI.SelectCommand;

/**
 *
 * @author mikko
 */
public class ListPrintCommand implements Command {
    
    private IO io;
    private HashMap<String, Reference> refMap;
    private DAO[] daos;
    private SelectCommand sc;

    public ListPrintCommand(IO io, SelectCommand sc,  DAO... daos) {
        this.io = io;
        this.daos = daos;
        this.sc = sc;
    }
    
    public void setRefMap(HashMap<String, Reference> refMap){
        this.refMap = refMap;
    }

    @Override
    public boolean execute(Reference ref) {
        PrintRef printer = new PrintRef(daos[0], daos[1], null, io);
        
        io.print("Listaus alkaa:\n");
        for (String s : refMap.keySet()) {
            if (s.equals("0"))
                continue;
            io.print("Viite " + s + ":\n");
            printer.printAnyRef(refMap.get(s));           
        }
        sc.execute(ref);
        
           
        return true; 
    }
}