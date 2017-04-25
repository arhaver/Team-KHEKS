package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class YearCommand implements Command {

    IO io;

    public YearCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        int year = io.readInt("Anna julkaisuvuosi:");
        if (ref.setYear(year)) {
            return true;
        }
        io.print("Lisäys '" + year + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
