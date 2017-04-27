package commandlineUI.menu;

import commandlineUI.menu.adder.ArticleAdder;
import commandlineUI.menu.adder.BookAdder;
import bibtex.IBibtexTranslator;
import commandlineUI.Command;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.PrintRef;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.adder.InproceedingsAdder;
import commandlineUI.menu.editing.ListerCommand;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import service.BibTexIdService;

public class MainMenu extends Menu {

    public MainMenu(DAO<ArticleRef> adao, DAO<BookRef> bdao, DAO<InproceedingsRef> idao, IO io,
            IFilewriter filewriter, IBibtexTranslator translator, BibTexIdService service) {
        super(io,
                new String[]{
                    "\nLähdeviitehallintaohjelma by Team-KHEKS.",
                    "\nTERVETULOA!"
                },
                new String[]{
                    "Päävalikko:\n",
                    "1 Lisää kirja viiteluetteloon",
                    "2 Lisää artikkeli viiteluetteloon",
                    "3 Lisää konferenssijulkaisu viiteluetteloon",
                    "4 Listaa viitteet luettavassa muodossa",
                    "5 Tulosta viitteet tiedostoon BibTeX-muodossa",
                    "6 Muokkaa viitettä tai poista viite",
                    "Q Lopeta ohjelma\n",
                    "Valitse toiminto"
                });

        Map<String, Command> menuCommandMap = super.getCommands();

        menuCommandMap.put("1", new BookAdder(bdao, io, service));
        menuCommandMap.put("2", new ArticleAdder(adao, io, service));
        menuCommandMap.put("3", new InproceedingsAdder(idao, io, service));
        menuCommandMap.put("4", new PrintRef(bdao, adao, idao, io));
        menuCommandMap.put("5", new BibTexUI(translator, filewriter, io, bdao, adao, idao));
        menuCommandMap.put("6", new ListerCommand(io, bdao, adao, service));
        menuCommandMap.put("q", new QuitCommand());
        setDefaultCommand(new PredefinedPrintCommand("\nVirheellinen komento!", io));
    }
}
