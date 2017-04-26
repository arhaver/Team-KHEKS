package commandlineUI.menu.adder;

import commandlineUI.AddressCommand;
import commandlineUI.BibTexIdCommand;
import commandlineUI.Command;
import commandlineUI.PrintStatusCommand;
import commandlineUI.PublisherCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.SaveToDbCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.Reference;
import reference.BookRef;

public class BookAdder extends AbstractAdder<BookRef> {

    public BookAdder(DAO<BookRef> dao, IO io) {
        super(dao, io, new String[]{},
                new String[]
                {
                    "Kirjaviitteen lisääminen:\n",
                    "1 Teoksen nimi",
                    "2 Kirjoittaja(t)",
                    "3 Julkaisuvuosi",
                    "4 Kustantaja",
                    "5 Kustantajan osoite",
                    "6 BibTex -tunniste",
                    "7 Tallenna ja lopeta",
                    "8 Näytä syötetyt tiedot",
                    "9 Lopeta tallentamatta"
                });

        Map<String, Command> commands = super.getCommands();
        
        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io, dao));
        commands.put("7", new SaveToDbCommand(io,dao));
        commands.put("8", new PrintStatusCommand(io));
        commands.put("9", new QuitCommand());
    }

    @Override
    protected Reference createReferenceSkeleton() {
        return new BookRef();
    }
}
