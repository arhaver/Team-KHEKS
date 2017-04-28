package commandlineUI.menu.editing;

import commandlineUI.Command;
import reference.Reference;
import service.DaoService;

public class ChooseMenuResetter implements Command{

    private final ChooseMenu chooseMenu;
    private final DaoService daoService;

    public ChooseMenuResetter(ChooseMenu chooseMenu, DaoService daoService) {
        this.chooseMenu = chooseMenu;
        this.daoService = daoService;
    }
    
    @Override
    public boolean execute(Reference ref) {
        chooseMenu.setReferences(daoService.getReferencesNumbermapped());
        
        return true;
    }
    
}
