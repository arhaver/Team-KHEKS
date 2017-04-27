package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class PagesCommand implements Command {

    IO io;

    public PagesCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String pages = io.readLine("Anna sivut, joilla artikkeli on:");
        if (ref.setField("pages", pages)) {
            return true;
        }
        io.print("Lisäys '" + pages + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
