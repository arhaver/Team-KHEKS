package commandlineUI.menu.editing;
import commandlineUI.SelectCommand;
import commandlineUI.Command;
import commandlineUI.EditorSelectCommand;
import commandlineUI.ListPrintCommand;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookAdder;
import commandlineUI.menu.adder.BookEditor;
import database.DAO;
import io.IO;
import java.util.HashMap;
import java.util.Map;
import reference.Reference;
import service.BibTexIdService;

public class ChooseEditedMenu extends Menu{
    
    private HashMap<String, Reference> refMap;
    private String selection;
    private DAO[] daos;
    private ListPrintCommand listPrinter;
    private EditorSelectCommand editorSelector;
    private BookEditor be;
    private ArticleEditor ae;
    
    public ChooseEditedMenu(IO io, BibTexIdService service, DAO... daos) {
        super(io, new String[0], 
                new String[]{
                    "Viitteen muokkaaminen/poistaminen:\n",
                    "S Valitse viite antamalla sen numero",
                    "E Muokkaa valittua viitettä",
                    "D Poista valittu viite",
                    "L Listaa viitteet",
                    "Q Palaa päävalikkoon\n",
                    "Valitse toiminto"
                });
        listPrinter = new ListPrintCommand(io, daos);
        be = new BookEditor(daos[1], io, service);
        ae = new ArticleEditor(daos[0], io, service);      
        editorSelector = new EditorSelectCommand(io, be, ae);
        
                ;
        Map<String, Command> menuCommandMap = super.getCommands();        
        menuCommandMap.put("s", new SelectCommand(io, this));
        menuCommandMap.put("e", editorSelector);
        menuCommandMap.put("l", listPrinter);
        menuCommandMap.put("q", new QuitCommand());     
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen komento!", io));
    }
    
    public void setReferences(HashMap<String, Reference> refMap){
        this.refMap = refMap;
        listPrinter.setRefMap(refMap);
        Map<String, Command> menuCommandMap = super.getCommands();  
        menuCommandMap.put("1", new PredefinedPrintCommand("\nKokeilu!", io));
    }
    
    public boolean setSelection(String selection) {
        if (refMap.containsKey(selection)) {
            this.selection = selection;         
            editorSelector.setRef(refMap.get(selection));
            return true;
        }
        return false;      
    }
    
    
    
}
