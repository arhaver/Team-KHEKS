package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class OrganizationCommand implements Command {

    IO io;

    public OrganizationCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String organization = io.readLine("Anna tekijä(t):\n");
        if (ref.setField("organization", organization)) {
            return true;
        }
        io.print("Lisäys '" + organization + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
