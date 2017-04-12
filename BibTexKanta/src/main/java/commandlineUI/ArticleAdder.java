package commandlineUI;
import database.DAO;
import io.IO;
import java.util.HashMap;
import reference.Reference;

public class ArticleAdder extends AbstractAdder<Reference> {

    private Reference ref;

    public ArticleAdder(DAO<Reference> dao, IO io) {

        super(dao, io);

        this.options = new String[14];
        options[0] = "Artikkeliviitteen lisääminen:\n";
        options[1] = "1 Artikkelin nimi";
        options[2] = "2 Kirjoittaja(t)";
        options[3] = "3 Julkaisuvuosi";
        options[4] = "4 Kustantaja";
        options[5] = "5 Kustantajan osoite";
        options[6] = "6 BibTex -tunniste";
        options[7] = "7 Vuosikerta";
        options[8] = "8 Numero";
        options[9] = "9 Lehti";
        options[10] = "10 Sivut";        
        options[11] = "11 Tallenna ja lopeta";
        options[12] = "12 Näytä syötetyt tiedot";
        options[13] = "13 Lopeta tallentamatta";

        this.commands = new HashMap<>();
        
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
        commands.put("11", new SaveToDbCommand(io));
        commands.put("12", new PrintStatusCommand(io));
        commands.put("13", new QuitCommand(io));
        
        commands.get("11").setDao(this.dao);

    }

    public void addArticleToDB() {
        ref = new Reference("article");
        String command;
        boolean again = true;
        while (again) {
            this.listOptions();
            command = io.readLine("Valitse toiminto (1-13)"); 
            Command doNow = commands.getOrDefault(command, new NothingCommand(io));
            again = doNow.execute(ref);
        }
    }
}
