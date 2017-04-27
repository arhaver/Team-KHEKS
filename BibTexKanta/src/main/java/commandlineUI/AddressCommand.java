package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class AddressCommand implements Command {

    IO io;

    public AddressCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {

        String address = io.readLine("Anna julkaisijan osoite:");
        if (ref.setField("address", address)) {
            return true;
        }
        io.print("Lisäys '" + address + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
