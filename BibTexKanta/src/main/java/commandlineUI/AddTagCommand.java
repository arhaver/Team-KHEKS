package commandlineUI;

import io.IO;
import reference.Reference;

public class AddTagCommand implements Command {

    IO io;
    Reference ref;

    public AddTagCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        this.ref = ref;
        String tag = io.readLine("Anna uusi tägi:");
        if (ref.addTag(tag)) {
            io.print("Tägi '" + tag + "' lisätty onnistuneesti");
            return true;
        }
        io.print("Tägin '" + tag + "' lisäys ei onnistunut\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}