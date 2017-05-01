package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class MonthCommand implements Command {

    IO io;

    public MonthCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {

        String month = io.readLine("Anna kuukausi:");
        if (ref.setField("month", month)) {
            return true;
        }
        io.print("Lisäys '" + month + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
