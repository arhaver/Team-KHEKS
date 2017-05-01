package commandlineUI.menu.editing;

import commandlineUI.Command;
import commandlineUI.ListPrintCommand;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import commandlineUI.menu.adder.InproceedingsEditor;
import database.DAO;
import io.IO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import reference.Reference;
import service.BibTexIdService;
import service.DaoService;
import service.search.SearchService;

public class ChooseMenu extends Menu {

    private Map<Integer, Reference> refMap;
    
    private ListPrintCommand listPrinter;
    private EditOrDeleteMenu editOrDeleteMenu;
    private BookEditor be;
    private ArticleEditor ae;
    private InproceedingsEditor ie;

    public ChooseMenu(IO io, BibTexIdService service, DAO... daos) {
        super(io, new String[0],
                new String[]{
                    "\nVIITTEIDEN MUOKKAUS/POISTAMINEN\n",
                    "Valitse viite kirjoittamalla sen numero. Muut toiminnot:",
                    "S Rajaa hakua",
                    "R Nollaa haku",
                    "Q Palaa päävalikkoon\n"
                });
        
        DaoService daoService = new DaoService(daos);
        
        be = new BookEditor(daos[1], io, service);
        ae = new ArticleEditor(daos[0], io, service);
        ie = new InproceedingsEditor(daos[2], io, service);
        listPrinter = new ListPrintCommand(io, daos);
        editOrDeleteMenu = new EditOrDeleteMenu(io, this, be, ae, ie, daos);

        Map<String, Command> menuCommandMap = super.getCommands();
        menuCommandMap.put("s", new SearchMenu(io, this, new SearchService()));
        menuCommandMap.put("r", new ChooseMenuResetter(this, daoService));
        menuCommandMap.put("q", new QuitCommand());
        
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen viitteen numero tai komento!", io));
    }

    public void setReferences(Map<Integer, Reference> refMap) {
        this.refMap = refMap;
        listPrinter.setRefMap(refMap);
        listPrinter.execute(null);
    }
    
    public List<Reference> getReferences(){
        return new ArrayList<>(refMap.values());
    }
    
    public void removeReference(Reference reference){
        for(Integer key : refMap.keySet()){
            if(refMap.get(key).equals(reference)){
                refMap.remove(key);
                return;
            }
        }
    }
    
    @Override
    public boolean actOnLine(String line){
        try{
            Reference ref = refMap.get(Integer.parseInt(line));
            
            if(ref != null){
                editOrDeleteMenu.execute(ref);
                return true;
            }
        }catch(Exception e){}
        
        return super.actOnLine(line);
    }
    
    @Override
    public void beforeLoop(){
        listPrinter.execute(null);
        super.beforeLoop();
    }
}
