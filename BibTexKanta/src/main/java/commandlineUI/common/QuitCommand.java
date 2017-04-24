package commandlineUI.common;

import commandlineUI.Command;
import reference.Reference;

public class QuitCommand implements Command {

    @Override
    public boolean execute(Reference ref) {
        return false; // Näin menee
    }
}
