package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class EditionCommand implements Command {

    IO io;

    public EditionCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {

        String edition = io.readLine("Anna painos:");
        if (ref.setField("edition", edition)) {
            return true;
        }
        io.print("Lisäys '" + edition + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
