package commandlineUI;

import database.DAO;
import io.IO;
import java.util.HashMap;
import reference.Reference;

public class BookAdder extends AbstractAdder<Reference> {

    private Reference ref;

    public BookAdder(DAO<Reference> dao, IO io) {

        super(dao, io);

        this.options = new String[10];
        options[0] = "Kirjaviitteen lisääminen:\n";
        options[1] = "1 Teoksen nimi";
        options[2] = "2 Kirjoittaja(t)";
        options[3] = "3 Julkaisuvuosi";
        options[4] = "4 Kustantaja";
        options[5] = "5 Kustantajan osoite";
        options[6] = "6 BibTex -tunniste";
        options[7] = "7 Tallenna ja lopeta";
        options[8] = "8 Näytä syötetyt tiedot";
        options[9] = "9 Lopeta tallentamatta";

        this.commands = new HashMap<>();
        SaveToDbCommand stdb = new SaveToDbCommand(io);
        stdb.setDao(dao);

        commands.put("1", new TitleCommand(io));
        commands.put("2", new AuthorsCommand(io));
        commands.put("3", new YearCommand(io));
        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io));
        commands.put("7", stdb);
        commands.put("8", new PrintStatusCommand(io));
        commands.put("9", new QuitCommand(io));

    }

    public void addBookToDB() {
        ref = new Reference("book");
        String command;
        boolean again = true;
        while (again) {
            this.listOptions();
            command = io.readLine("Valitse toiminto (1-9)");
            Command doNow = commands.getOrDefault(command, new NothingCommand(io));
            again = doNow.execute(ref);
        }
    }
}
