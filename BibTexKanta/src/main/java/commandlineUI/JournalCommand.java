package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;

public class JournalCommand implements Command {
    
    IO io;
    public JournalCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String journal =  io.readLine("Anna lehti, jossa artikkeli julkaistu:");
        if (ref.setJournal(journal))
            return true;
        io.print("Lisäys '" +journal+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

    @Override
    public void setDao(DAO dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}