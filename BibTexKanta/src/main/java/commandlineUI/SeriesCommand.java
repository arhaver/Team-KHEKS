package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class SeriesCommand implements Command {

    IO io;

    public SeriesCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {

        String series = io.readLine("Anna sarjan numero:");
        if (ref.setField("series", series)) {
            return true;
        }
        io.print("Lisäys '" + series + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
