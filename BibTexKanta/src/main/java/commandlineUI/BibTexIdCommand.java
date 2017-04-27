package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;
import service.BibTexIdService;

public class BibTexIdCommand implements Command {
    
    private IO io;
    private DAO dao;
    private BibTexIdService service;
    
    public BibTexIdCommand(IO io, DAO dao, BibTexIdService service) {
        this.io = io;
        this.dao = dao;
        this.service = service;
    }

    @Override
    public boolean execute(Reference ref) {
        String bibTex = io.readLine("Anna viitteelle BibTex -tunniste:\n"
                + "Jos tyhjä, tämä generoidaan automaattisesti.");
        if (service.saveId(bibTex, ref)){
            return true;
        }
        io.print("Lisäys '" + bibTex + "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}
