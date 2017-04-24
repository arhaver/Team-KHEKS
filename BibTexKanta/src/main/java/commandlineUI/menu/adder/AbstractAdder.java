package commandlineUI.menu.adder;

import commandlineUI.Command;
import commandlineUI.menu.Menu;
import commandlineUI.PredefinedPrintCommand;
import database.DAO;
import io.IO;
import java.util.HashMap;
import reference.Reference;

public abstract class AbstractAdder<T extends Reference> extends Menu{

    protected final int CURRENT_YEAR = 2017;
    protected DAO dao;
    protected IO io;
    protected String[] options;
    protected HashMap<String, Command> commands;
    protected Reference newReference;

    public AbstractAdder(DAO<T> dao, IO io, String[] startLines, String[] askPrompts) {
        super(io, startLines, askPrompts);
        
        this.dao = dao;
        this.io = io;
        
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
