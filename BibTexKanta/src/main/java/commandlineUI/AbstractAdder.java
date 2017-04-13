package commandlineUI;

import database.BookDAO;
import database.DAO;
import io.IO;
import database.Database;
import java.util.HashMap;
import reference.BookRef;
import reference.Reference;

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
    
    protected void loop (String prompt, Reference ref) {
        String command;
        boolean again = true;
        while (again) {
            this.listOptions();
            command = io.readLine(prompt);
            Command doNow = commands.getOrDefault(command, new NothingCommand(io));
            again = doNow.execute(ref);
        }
    }
}
