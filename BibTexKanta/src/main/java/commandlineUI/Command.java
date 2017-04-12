package commandlineUI;

import database.DAO;
import reference.Reference;

public interface Command {
    boolean execute(Reference ref);
}
