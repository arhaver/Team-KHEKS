package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;

public class VolumeCommand implements Command {
    
    IO io;
    public VolumeCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String volume =  io.readLine("Anna vuosikerta:");
        if (ref.setVolume(volume))
            return true;
        io.print("Lisäys '" +volume+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

    @Override
    public void setDao(DAO dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
