package commandlineUI;

import database.BookDAO;
import database.DAO;
import io.IO;
import database.Database;
import java.util.HashMap;
import reference.BookRef;

public abstract class AbstractAdder<T> {

    protected final int CURRENT_YEAR = 2017;
    protected DAO dao;
    protected IO io;
    protected String[] options;
    protected HashMap<String, Command> commands;

    public AbstractAdder(DAO<T> dao, IO io) {
        this.dao = dao;
        this.io = io;
    }

    protected void setOptions(String[] options) {
        this.options = options;
    }

    protected void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    protected void listOptions() {
        io.print("");
        for (int a = 0; a < this.options.length; a++) {
            io.print(options[a]);
        }
    }
}
