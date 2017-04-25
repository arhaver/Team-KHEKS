package commandlineUI.menu.adder;

import commandlineUI.AuthorsCommand;
import commandlineUI.Command;
import commandlineUI.PublisherCommand;
import commandlineUI.TitleCommand;
import commandlineUI.YearCommand;
import commandlineUI.menu.Menu;
import commandlineUI.common.PredefinedPrintCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.Reference;

public abstract class AbstractAdder<T extends Reference> extends Menu{

    protected Reference newReference;

    public AbstractAdder(DAO<T> dao, IO io, String[] startLines, String[] askPrompts) {
        super(io, startLines, askPrompts);
        
        
        Map<String, Command> commands = super.getCommands();
        commands.put("1", new TitleCommand(io));
        commands.put("2", new AuthorsCommand(io));
        commands.put("3", new YearCommand(io));
        
        super.setDefaultCommand(new PredefinedPrintCommand("Virheellinen käsky", io));
    }
    
    protected abstract Reference createReferenceSkeleton();

    @Override
    public boolean execute(Reference reference){
        newReference = createReferenceSkeleton();
        
        return super.execute(reference);
    }
    
    @Override
    public Reference referenceToGiveToCommands(){
        return newReference;
    }
}
