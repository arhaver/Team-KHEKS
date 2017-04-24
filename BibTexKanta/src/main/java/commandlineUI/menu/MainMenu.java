package commandlineUI.menu;

import commandlineUI.menu.adder.ArticleAdder;
import commandlineUI.menu.adder.BookAdder;
import bibtex.IBibtexTranslator;
import commandlineUI.Command;
import commandlineUI.PredefinedPrintCommand;
import commandlineUI.PrintRef;
import commandlineUI.QuitCommand;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;

public class MainMenu extends Menu{
    
    private final IO io;
    private final IBibtexTranslator translator;
    private final IFilewriter filewriter;
    
    private final DAO<ArticleRef> adao;
    private final DAO<BookRef> bdao;
    
    public MainMenu(DAO<ArticleRef> adao, DAO<BookRef> bdao, IO io, 
            IFilewriter filewriter, IBibtexTranslator translator) {
        super(io, 
                new String[]
                {
                    "\nLähdeviitehallintaohjelma by Team-KHEKS.", 
                    "\nTERVETULOA!"
                }, 
                new String[]
                {
                    "Päävalikko:\n",
                    "1 Lisää kirja viiteluetteloon",
                    "2 Lisää artikkeli viiteluetteloon",
                    "3 Listaa viitteet luettavassa muodossa",
                    "4 Tulosta viitteet tiedostoon BibTeX-muodossa",
                    "Q Lopeta ohjelma\n",
                    "Valitse toiminto"
                });
        
        this.io = io;
        this.translator = translator;
        this.filewriter = filewriter;
        
        this.adao = adao;
        this.bdao = bdao;
        
        addCommandsToMenu(super.getCommands());
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen komento!", io));
    }

    @Override
    protected void addCommandsToMenu(Map<String, Command> menuCommandMap) {
        menuCommandMap.put("1", new BookAdder(bdao, io));
        menuCommandMap.put("2", new ArticleAdder(adao, io));
        menuCommandMap.put("3", new PrintRef(bdao, adao, io));
        menuCommandMap.put("4", new BibTexUI(translator, filewriter, io, bdao, adao));
        menuCommandMap.put("q", new QuitCommand());
    }
}
