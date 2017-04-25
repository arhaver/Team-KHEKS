package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;

public class BookTitleCommand implements Command {
    
    IO io;
    public BookTitleCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        String booktitle =  io.readLine("Anna konferenssijulkaisun nimi:");
        if (ref.setField("booktitle", booktitle))
            return true;
        io.print("Lisäys '" +booktitle+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}

