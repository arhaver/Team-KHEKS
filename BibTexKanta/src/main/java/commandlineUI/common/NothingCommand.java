package commandlineUI.common;

import commandlineUI.Command;
import reference.Reference;

public class NothingCommand implements Command {

    @Override
    public boolean execute(Reference ref) {
        return true;
    }

}
