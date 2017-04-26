package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;
import service.BibTexIdService;

public class BibTexIdCommand implements Command {
    
    IO io;
    DAO dao;
    public BibTexIdCommand(IO io, DAO dao) {
        this.io = io;
        this.dao = dao;
    }

    @Override
    public boolean execute(Reference ref) {
        String bibTex =  io.readLine("Anna viitteelle BibTex -tunniste:\n"
                + "Jos tyhjä, tämä generoidaan automaattisesti.");
        BibTexIdService btids = dao.getDaoService().getBibTexIdService();
        if (btids.saveId(bibTex, ref))
            return true;
        io.print("Lisäys '" +bibTex+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}