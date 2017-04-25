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
        String volume = io.readLine("Anna vuosikerta:");
        if (ref.setField("volume", volume)) {
            return true;
        }
        io.print("Lisäys '" + volume + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
