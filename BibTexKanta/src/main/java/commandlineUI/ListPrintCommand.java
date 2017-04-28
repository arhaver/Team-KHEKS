/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import database.DAO;
import io.IO;
import java.util.Map;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class ListPrintCommand implements Command {
    
    private final IO io;
    private Map<Integer, Reference> refMap;
    private final DAO[] daos;

    public ListPrintCommand(IO io, DAO... daos) {
        this.io = io;
        this.daos = daos;
    }
    
    public void setRefMap(Map<Integer, Reference> refMap){
        this.refMap = refMap;
    }

    @Override
    public boolean execute(Reference ref) {
        PrintRef printer = new PrintRef(daos[0], daos[1], null, io);
        
        io.print("Listaus alkaa:\n");
        for (Integer i : refMap.keySet()) {
            io.print("Viite " + i + ":\n");
            printer.printAnyRef(refMap.get(i));           
        }
           
        return true; 
    }
}