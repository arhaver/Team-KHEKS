package commandlineUI.menu.editing;

import commandlineUI.Command;
import commandlineUI.ListPrintCommand;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.Reference;
import service.BibTexIdService;

public class ChooseMenu extends Menu {

    private Map<Integer, Reference> refMap;
    
    private ListPrintCommand listPrinter;
    private EditOrDeleteMenu editOrDeleteMenu;
    private BookEditor be;
    private ArticleEditor ae;

    public ChooseMenu(IO io, BibTexIdService service, DAO... daos) {
        super(io, new String[0],
                new String[]{
                    "\nVIITTEIDEN MUOKKAUS/POISTAMINEN\n",
                    "L listaa viitteet ja valitse yksi",
                    "E Siirry muokkaammaan viitettä",
                    "D Poista viite",
                    "Q Palaa päävalikkoon\n",
                    "Valitse ensin viite, ja sitten sille toiminto\n"
                });
        be = new BookEditor(daos[1], io, service);
        ae = new ArticleEditor(daos[0], io, service);
        listPrinter = new ListPrintCommand(io, daos);
        editOrDeleteMenu = new EditOrDeleteMenu(io, be, ae, daos);

        Map<String, Command> menuCommandMap = super.getCommands();
        menuCommandMap.put("q", new QuitCommand());
        
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen viitteen numero tai komento!", io));
    }

    public void setReferences(Map<Integer, Reference> refMap) {
        this.refMap = refMap;
        listPrinter.setRefMap(refMap);
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
