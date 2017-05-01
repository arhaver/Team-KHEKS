package commandlineUI.menu.adder;

import commandlineUI.AddressCommand;
import commandlineUI.BibTexIdCommand;
import commandlineUI.Command;
import commandlineUI.EditionCommand;
import commandlineUI.MonthCommand;
import commandlineUI.NoteCommand;
import commandlineUI.PrintStatusCommand;
import commandlineUI.PublisherCommand;
import commandlineUI.SaveEditCommand;
import commandlineUI.SeriesCommand;
import commandlineUI.common.QuitCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.BookRef;
import reference.Reference;
import service.BibTexIdService;

public class BookEditor extends AbstractEditor{
    
    public BookEditor(DAO dao, IO io, BibTexIdService service) {
        super(dao, io, new String[0], 
                new String[]
                {
                    "Kirjaviitteen muokkaaminen:\n",
                    "1 Teoksen nimi",
                    "2 Kirjoittaja(t)",
                    "3 Julkaisuvuosi",
                    "4 Kustantaja",
                    "5 Kustantajan osoite",
                    "6 BibTex -tunniste",
                    "7 Tallenna ja lopeta",
                    "8 Näytä syötetyt tiedot",
                    "9 Lopeta tallentamatta",
                    "10 Muokkaa viitteen tägejä",
                    "11 Kirjan numero",
                    "12 Painos",
                    "13 Kuukausi",
                    "14 Lisätietoja"
                });

        Map<String, Command> commands = super.getCommands();

        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io, dao, service));
        commands.put("7", new SaveEditCommand(this, io));
        commands.put("8", new PrintStatusCommand(io));
        commands.put("9", new QuitCommand());
        commands.put("10", new TagEditor(io));
        commands.put("11", new SeriesCommand(io));
        commands.put("12", new EditionCommand(io));
        commands.put("13", new MonthCommand(io));
        commands.put("14", new NoteCommand(io));
    }

    @Override
    protected Reference createReference() {
        return new BookRef();
    }
}
