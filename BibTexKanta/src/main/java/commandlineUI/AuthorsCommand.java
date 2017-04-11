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
        if (ref.setAuthors(authors))
            return true;
        io.print("Lisäys '" +authors+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

    @Override
    public void setDao(DAO dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
