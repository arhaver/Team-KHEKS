package commandlineUI;

import database.DAO;
import io.IO;
import java.util.HashMap;
import reference.Reference;

public class PrintStatusCommand implements Command {

    IO io;
    DAO dao;

    public PrintStatusCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        
        io.print(ref.toString());
        return true;
  }
}
