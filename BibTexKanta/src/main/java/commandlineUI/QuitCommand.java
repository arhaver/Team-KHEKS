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
}
