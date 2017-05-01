package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class EditorCommand implements Command {

    IO io;

    public EditorCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String editor = io.readLine("Anna toimittaja:\n");
        if (ref.setField("editor", editor)) {
            return true;
        }
        io.print("Lisäys '" + editor + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
