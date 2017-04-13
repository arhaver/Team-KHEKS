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
                String title =  io.readLine("Anna teoksen nimi:");
        if (ref.setField("title", title))
            return true;
        io.print("Lisäys '" +title+ "' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }
}

