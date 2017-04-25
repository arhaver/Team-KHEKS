package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class BibTexIdCommand implements Command {

    IO io;

    public BibTexIdCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String bibTex = io.readLine("Anna viitteelle BibTex -tunniste:\n"
                + "Jos tyhjä, tämä generoidaan automaattisesti.");
        if (ref.setField("bibTexId", bibTex)) {
            return true;
        }
        io.print("Lisäys '" + bibTex + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
