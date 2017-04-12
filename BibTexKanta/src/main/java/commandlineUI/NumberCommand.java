package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;

public class NumberCommand implements Command {
    
    IO io;
    public NumberCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String number =  io.readLine("Anna numero:");
        if (ref.setNumber(number))
            return true;
        io.print("Lisäys '" +number+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}

