package commandlineUI.menu.adder;

import commandlineUI.AddressCommand;
import commandlineUI.AuthorsCommand;
import commandlineUI.BibTexIdCommand;
import commandlineUI.Command;
import commandlineUI.JournalCommand;
import commandlineUI.NumberCommand;
import commandlineUI.PagesCommand;
import commandlineUI.PrintStatusCommand;
import commandlineUI.PublisherCommand;
import commandlineUI.QuitCommand;
import commandlineUI.SaveToDbCommand;
import commandlineUI.TitleCommand;
import commandlineUI.VolumeCommand;
import commandlineUI.YearCommand;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.Reference;

public class ArticleAdder extends AbstractAdder<ArticleRef> {

    public ArticleAdder(DAO<ArticleRef> dao, IO io) {

        super(dao, io, new String[]{},
                new String[]
                {
                    "Artikkeliviitteen lisääminen:\n",
                    "1 Artikkelin nimi",
                    "2 Kirjoittaja(t)",
                    "3 Julkaisuvuosi",
                    "4 Kustantaja",
                    "5 Kustantajan osoite",
                    "6 BibTex -tunniste",
                    "7 Vuosikerta",
                    "8 Numero",
                    "9 Lehti",
                    "10 Sivut",
                    "11 Tallenna ja lopeta",
                    "12 Näytä syötetyt tiedot",
                    "13 Lopeta tallentamatta"
                }
                );

        addCommandsToMenu(super.getCommands());
    }

    @Override
    protected void addCommandsToMenu(Map<String, Command> commands) {
        SaveToDbCommand stdb = new SaveToDbCommand(io);
        stdb.setDao(dao);
        
        commands.put("1", new TitleCommand(io));
        commands.put("2", new AuthorsCommand(io));
        commands.put("3", new YearCommand(io));
        commands.put("4", new PublisherCommand(io));
        commands.put("5", new AddressCommand(io));
        commands.put("6", new BibTexIdCommand(io));
        commands.put("7", new VolumeCommand(io));
        commands.put("8", new NumberCommand(io));
        commands.put("9", new JournalCommand(io));
        commands.put("10", new PagesCommand(io));
        commands.put("11", stdb);
        commands.put("12", new PrintStatusCommand(io));
        commands.put("13", new QuitCommand());
    }

    @Override
    protected Reference createReferenceSkeleton() {
        return new ArticleRef();
    }
}
