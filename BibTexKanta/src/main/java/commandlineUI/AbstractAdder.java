package commandlineUI;

import database.BookDAO;
import database.DAO;
import io.IO;
import database.Database;
import reference.BookRef;

public abstract class AbstractAdder<T> {
    
    protected final int CURRENT_YEAR = 2017;
    protected DAO dao;
    protected IO io;
    protected String[] options;

    public AbstractAdder(DAO<T> dao, IO io) {
        this.dao = dao;
        this.io = io;
    }
    
    protected void setOptions(String[] options) {
        this.options = options;        
    }

    protected void userFeedback(String input, boolean success) {

        if (!success) {
            io.print("Lisäys '" + input + "' virheellinen\n");
        }
    }

    protected String addTitle() {
        return io.readLine("Anna teoksen nimi:");
    }

    protected String addAuthor() {
        return io.readLine("Anna tekijä(t):");
    }

    protected int addYear() {
        return io.readInt("Anna julkaisuvuosi:");
    }

    protected void listOptions() {
        io.print("");
        for (int a = 0; a < this.options.length; a++) {
            io.print(options[a]);
        }
    }

    protected boolean isValidYear(int year) {
        if (0 < year && year <= CURRENT_YEAR) {
            return true;
        }
        return false;
    }

    protected boolean isValidString(String input) {

        if (input != null && input.length() > 2) {
            return true;
        }
        return false;
    }

}
