package commandlineUI.menu.editing;

import commandlineUI.menu.Menu;
import io.IO;
import java.util.List;
import reference.Reference;

public class ChooseEditedMenu extends Menu {

    private List<Reference> references;

    public ChooseEditedMenu(IO io) {
        super(io, new String[]{"alkutekstit tähän"},
                new String[]{"joka käskyn jälkeen näkyvät tekstit tähän"});
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

}
