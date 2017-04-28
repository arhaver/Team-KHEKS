package commandlineUI.menu.editing;

import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.editing.ChooseMenu;
import io.IO;
import java.util.Map;
import reference.Reference;
import service.search.SearchService;

public class SearchMenu extends Menu{

    private final ChooseMenu chooseMenu;
    private final SearchService search;
    
    public SearchMenu(IO io, ChooseMenu choose, SearchService search) {
        super(io, new String[0], 
                new String[]{
                    "Anna hakukriteeri (q peruuttaa):"
                });
        
        this.chooseMenu = choose;
        this.search = search;
        
        this.getCommands().put("q", new QuitCommand());
        
        this.setDefaultCommand(new PredefinedPrintCommand("Virheellinen hakukriteeri", io));
    }

    @Override
    public boolean actOnLine(String line){
        try{
            Map<Integer, Reference> searched = search.searchReferenceMap(chooseMenu.getReferences(), line);
            chooseMenu.setReferences(searched);
            return false;
        }catch(Exception e){}
        
        return super.actOnLine(line);
    }
    
}
