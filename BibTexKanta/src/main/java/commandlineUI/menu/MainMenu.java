package commandlineUI.menu;

import commandlineUI.menu.adder.ArticleAdder;
import commandlineUI.menu.adder.BookAdder;
import bibtex.IBibtexTranslator;
import commandlineUI.Command;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.PrintRef;
import commandlineUI.common.QuitCommand;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;

public class MainMenu extends Menu{
    
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
        
        Map<String, Command> menuCommandMap = super.getCommands();
        
        menuCommandMap.put("1", new BookAdder(bdao, io));
        menuCommandMap.put("2", new ArticleAdder(adao, io));
        menuCommandMap.put("3", new PrintRef(bdao, adao, io));
        menuCommandMap.put("4", new BibTexUI(translator, filewriter, io, bdao, adao));
        menuCommandMap.put("q", new QuitCommand());
        
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen komento!", io));
    }
}
