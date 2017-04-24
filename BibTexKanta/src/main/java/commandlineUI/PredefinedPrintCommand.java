package commandlineUI;

import io.IO;
import reference.Reference;

public class PredefinedPrintCommand implements Command{
    
    private final String toPrint;
    private final IO io;

    public PredefinedPrintCommand(String toPrint, IO io) {
        this.toPrint = toPrint;
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        io.print(toPrint);
        return true;
    }
    
    
}
