package commandlineUI.menu.adder;

import commandlineUI.menu.Menu;
import commandlineUI.common.PredefinedPrintCommand;
import database.DAO;
import io.IO;
import reference.Reference;

public abstract class AbstractAdder<T extends Reference> extends Menu{

    protected Reference newReference;

    public AbstractAdder(DAO<T> dao, IO io, String[] startLines, String[] askPrompts) {
        super(io, startLines, askPrompts);
        
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
