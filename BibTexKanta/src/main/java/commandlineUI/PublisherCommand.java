package commandlineUI;
import database.DAO;
import io.IO;
import reference.Reference;

public class PublisherCommand implements Command {
    
    IO io;
    public PublisherCommand(IO io) {
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        
        
        String publisher = io.readLine("Anna julkaisija:");
        if (ref.setField("publisher", publisher))
            return true;
        io.print("Lisäys '"+publisher+"' virheellinen\n");
        return true; // tää vaikuttaa oudolta mutta näin tää nyt menee
    }  
}
