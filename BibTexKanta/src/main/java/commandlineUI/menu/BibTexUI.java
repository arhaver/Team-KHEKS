package commandlineUI.menu;

import bibtex.IBibtexTranslator;
import commandlineUI.BibTexCreationCommand;
import commandlineUI.Command;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;

/*
Käyttöliittymäluokka joka hoitaa bibtex-tiedoston tulostamisen
 */
public class BibTexUI extends Menu {

    public BibTexUI(IBibtexTranslator translator, IFilewriter filewriter, IO io,
            DAO<BookRef> bookDAO, DAO<ArticleRef> articleDAO) {
        super(io, new String[]{"\nBibText-tiedoston tulostus:"},
                new String[]{
                    "Vaihtoehdot:",
                    "1.Tulosta",
                    "Q.Peruuta"
                });

        Map<String, Command> menuCommandMap = super.getCommands();
        menuCommandMap.put("q", new QuitCommand());
        menuCommandMap.put("1", new BibTexCreationCommand(translator, filewriter, io, bookDAO, articleDAO));

        super.setDefaultCommand(new PredefinedPrintCommand("Epävalidi operaatio", io));
    }
}
