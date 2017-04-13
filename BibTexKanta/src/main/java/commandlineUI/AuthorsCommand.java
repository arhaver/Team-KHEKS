package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;

public class AuthorsCommand implements Command {
    
    IO io;
    public AuthorsCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String authors =  io.readLine("Anna tekijä(t):");
        if (ref.setField("authors", authors))
            return true;
        io.print("Lisäys '" +authors+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
