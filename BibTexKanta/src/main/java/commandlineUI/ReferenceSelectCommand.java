/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import commandlineUI.menu.editing.ChooseEditedMenu;
import database.DAO;
import io.IO;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class ReferenceSelectCommand implements Command {

    private IO io;
    private String nro;
    ChooseEditedMenu cm;

    public ReferenceSelectCommand(IO io, String nro, ChooseEditedMenu cm) {
        this.io = io;
        this.nro = nro;
        this.cm = cm;
    }

    @Override
    public boolean execute(Reference ignorable) {
        if (!cm.setSelection(nro))
            io.print("Virheellinen valinta!");
        return true;
    }
}
