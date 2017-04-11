package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class QuitCommand implements Command {

    private IO io;
    
    public QuitCommand (IO io) {
        this.io = io;
    
}

    @Override
    public boolean execute(Reference ref) {
        return false; // Näin menee
    }

    @Override
    public void setDao(DAO dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
