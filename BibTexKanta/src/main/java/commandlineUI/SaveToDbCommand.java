package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class SaveToDbCommand implements Command {

    IO io;
    DAO dao;

    public SaveToDbCommand(IO io, DAO dao) {
        this.io = io;
        this.dao = dao;
    }

    @Override
    public boolean execute(Reference ref) {
        // Palauttaa true jos tallennus ei onnistu
        if (!ref.readyForDb()) {
            io.print("Tallennus epäonnistui\n");
            return true; // HUOM!!
        }

        try {
            dao.add(ref);
            io.print("Viite lisätty onnistuneesti\n");
            return false;
        } catch (Exception e) {
            io.print("Tallennus epäonnistui");
            io.print(e.getMessage());
            return true; //Näin tää nyt menee

        }
    }
}
