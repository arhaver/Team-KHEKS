package commandlineUI;

import io.IO;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class RemoveTagCommand implements Command {

    IO io;

    public RemoveTagCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {

        String tag = io.readLine("Anna poistettava tägi:");
        if (ref.removeTag(tag)) {
            io.print("Tägi '" + tag + "' poistettu\n");
            return true;
        }
        io.print("Tägin '" + tag + "' poisto ei onnistunut\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
