package commandlineUI.menu.editing;

import commandlineUI.SelectCommand;
import commandlineUI.Command;
import commandlineUI.DeleteCommand;
import commandlineUI.EditorSelectCommand;
import commandlineUI.ListPrintCommand;
import commandlineUI.ReferenceSelectCommand;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookAdder;
import commandlineUI.menu.adder.BookEditor;
import commandlineUI.menu.adder.InproceedingsEditor;
import database.DAO;
import io.IO;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import reference.Reference;
import service.BibTexIdService;

public class ChooseEditedMenu extends Menu {

    private HashMap<String, Reference> refMap;
    private String selection;
    private DAO[] daos;
    private ListPrintCommand listPrinter;
    private EditorSelectCommand editorSelector;
    private DeleteCommand deleteConfirmer;
    private BookEditor be;
    private ArticleEditor ae;
    private InproceedingsEditor ie;
    
    private SelectCommand sc;

    public ChooseEditedMenu(IO io, BibTexIdService service, DAO... daos) {
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
        ie = new InproceedingsEditor(daos[2], io, service);
        sc = new SelectCommand(io, this);
        listPrinter = new ListPrintCommand(io, sc, daos);
        editorSelector = new EditorSelectCommand(io, be, ae, ie);
        deleteConfirmer = new DeleteCommand(io, this, daos);

        Map<String, Command> menuCommandMap = super.getCommands();
        menuCommandMap.put("e", editorSelector);
        menuCommandMap.put("l", listPrinter);
        menuCommandMap.put("d", deleteConfirmer);
        menuCommandMap.put("q", new QuitCommand());
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen viitteen numero tai komento!", io));
    }

    public void setReferences(HashMap<String, Reference> refMap) {
        this.refMap = refMap;
        listPrinter.setRefMap(refMap);

    }

    public boolean setSelection(String selection) {
        if (refMap.containsKey(selection)) {
            this.selection = selection;
            editorSelector.setRef(refMap.get(selection));
            return true;
        }
        return false;
    }

    public String getSelection() {
        return this.selection;
    }
    
    public Reference getSingleReference(String key) {
        return (this.refMap.get(key));
    }
    
    public boolean RemoveReference(String key) {
        this.refMap.remove(key);
        return true;

    }
}
