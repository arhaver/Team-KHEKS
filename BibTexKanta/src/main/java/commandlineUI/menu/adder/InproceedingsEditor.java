package commandlineUI.menu.adder;

import commandlineUI.AddressCommand;
import commandlineUI.BibTexIdCommand;
import commandlineUI.BookTitleCommand;
import commandlineUI.Command;
import commandlineUI.PagesCommand;
import commandlineUI.PrintStatusCommand;
import commandlineUI.PublisherCommand;
import commandlineUI.SaveEditCommand;
import commandlineUI.common.QuitCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.InproceedingsRef;
import reference.Reference;
import service.BibTexIdService;

public class InproceedingsEditor extends AbstractEditor{
    
    public InproceedingsEditor(DAO dao, IO io, BibTexIdService service) {
        super(dao, io, new String[0],
                new String[]{
                    "Konferenssijulkaisuviitteen muokkaaminen:\n",
                    "1 Artikkelin nimi",
                    "2 Kirjoittaja(t)",
                    "3 Julkaisuvuosi",
                    "4 Kustantaja",
                    "5 Kustantajan osoite",
                    "6 BibTex -tunniste",
                    "7 Kirjan nimi",
                    "8 Sivut",
                    "9 Tallenna ja lopeta",
                    "10 Näytä syötetyt tiedot",
                    "11 Lopeta tallentamatta",
                    "12 Muokkaa viitteen tägejä"
                });

        Map<String, Command> commands = super.getCommands();

        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io, dao, service));
        commands.put("7", new BookTitleCommand(io));
        commands.put("8", new PagesCommand(io));
        commands.put("9", new SaveEditCommand(this, io));
        commands.put("10", new PrintStatusCommand(io));
        commands.put("11", new QuitCommand());
        commands.put("12", new TagEditor(io));
    }

    @Override
    protected Reference createReference() {
        return new InproceedingsRef();
    }
}
