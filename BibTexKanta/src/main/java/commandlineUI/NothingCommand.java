package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class NothingCommand implements Command {

    private final IO io;
    
        
    public NothingCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        return true;
    }

    @Override
    public void setDao(DAO dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
