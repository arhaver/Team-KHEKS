package commandlineUI;

import database.DAO;
import service.BibTexIdService;
import io.IO;
import reference.Reference;

public class SaveToDbCommand implements Command {

    private IO io;
    private DAO dao;
    private BibTexIdService btids;

    public SaveToDbCommand(IO io, DAO dao, BibTexIdService bibtexservice) {
        this.io = io;
        this.dao = dao;
        this.btids = bibtexservice;
    }

    @Override
    public boolean execute(Reference ref) {
        // Palauttaa true jos tallennus ei onnistu
        if (!ref.readyForDb()) {
            io.print("Tallennus epäonnistui\n");
            return true; // HUOM!!
        }

        if (ref.getField("bibTexId") == null) {
           btids.generateId(ref);
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
