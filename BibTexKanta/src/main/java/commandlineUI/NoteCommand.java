package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class NoteCommand implements Command {

    IO io;

    public NoteCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {

        String note = io.readLine("Lisätietoja:");
        if (ref.setField("note", note)) {
            return true;
        }
        io.print("Lisäys '" + note + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
