package commandlineUI.menu.editing;

import commandlineUI.Command;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;
import service.BibTexIdService;
import service.DaoService;

public class ChooseMenuInitializer implements Command {

    private final BibTexIdService bibservice;
    private final DaoService daoService;
    
    private final ChooseMenu chooseMenu;

    public ChooseMenuInitializer(IO io, DAO<BookRef> bdao, DAO<ArticleRef> adao, DAO<InproceedingsRef> idao, BibTexIdService service) {
        this.bibservice = service;
        this.daoService = new DaoService(bdao, adao, idao);
        
        chooseMenu = new ChooseMenu(io, bibservice, adao, bdao, idao);
    }

    @Override
    public boolean execute(Reference ref) {
        Map<Integer, Reference> refMap = daoService.getReferencesNumbermapped();
        chooseMenu.setReferences(refMap);
        chooseMenu.execute(null);
        return true;
    }

}
