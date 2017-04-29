package commandlineUI;

import io.IO;
import java.util.Set;
import reference.Reference;

public class ListTagsCommand implements Command {

    IO io;
    Reference ref;

    public ListTagsCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        this.ref = ref;
        Set<String> tags = ref.getTags();
         io.print("Tägit:");
        for (String tag : tags)
            io.print("\t" + tag);
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }

}
