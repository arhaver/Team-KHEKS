package commandlineUI;

import commandlineUI.menu.adder.AbstractEditor;
import io.IO;
import java.util.List;
import java.util.Map;
import reference.Reference;

public class SaveEditCommand implements Command {

    private final AbstractEditor editor;
    private final IO io;

    public SaveEditCommand(AbstractEditor editor, IO io) {
        this.editor = editor;
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        Reference edited = editor.getEditRef();
        Reference real = editor.getTrueRef();

        if (!edited.readyForDb()) {
            io.print("Viite ei tallennettavassa muodossa.");
            return true;
        }

        Map<String, String> fields = edited.getAllFields();
        for (String field : fields.keySet()) {
            real.setField(field, fields.get(field));
        }

        real.setYear(edited.getYear());

        return false;
    }

}
