package commandlineUI.menu.adder;

import commandlineUI.AddressCommand;
import commandlineUI.BibTexIdCommand;
import commandlineUI.Command;
import commandlineUI.EditionCommand;
import commandlineUI.MonthCommand;
import commandlineUI.NoteCommand;
import commandlineUI.PrintStatusCommand;
import commandlineUI.PublisherCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.SaveToDbCommand;
import commandlineUI.SeriesCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.Reference;
import reference.BookRef;
import service.BibTexIdService;

public class BookAdder extends AbstractAdder<BookRef> {

    public BookAdder(DAO<BookRef> dao, IO io, BibTexIdService service) {
        super(dao, io, new String[]{},
                new String[]{
                    "Kirjaviitteen lisääminen:\n",
                    "1 Teoksen nimi",
                    "2 Kirjoittaja(t)",
                    "3 Julkaisuvuosi",
                    "4 Kustantaja",
                    "5 Kustantajan osoite",
                    "6 BibTex -tunniste",
                    "7 Tallenna ja lopeta",
                    "8 Näytä syötetyt tiedot",
                    "9 Lopeta tallentamatta",
                    "10 Lisää viitteeseen tägejä",
                    "11 Kirjan numero",
                    "12 Painos",
                    "13 Kuukausi",
                    "14 Lisätietoja"
                });

        Map<String, Command> commands = super.getCommands();

        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io, dao, service));
        commands.put("7", new SaveToDbCommand(io,dao, service));
        commands.put("8", new PrintStatusCommand(io));        
        commands.put("9", new QuitCommand());
        commands.put("10", new TagEditor(io));
        commands.put("11", new SeriesCommand(io));
        commands.put("12", new EditionCommand(io));
        commands.put("13", new MonthCommand(io));
        commands.put("14", new NoteCommand(io));
    }

    @Override
    protected Reference createReferenceSkeleton() {
        return new BookRef();
    }
}
