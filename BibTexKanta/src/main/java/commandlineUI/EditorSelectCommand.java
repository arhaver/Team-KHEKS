/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import io.IO;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;

/**
 *
 * @author mikko
 */
public class EditorSelectCommand implements Command {
    
    private IO io;
    private ArticleEditor ae;
    private BookEditor be;

    
    public EditorSelectCommand(IO io, BookEditor be, ArticleEditor ae) {
        this.be = be;
        this.io = io;
        this.ae = ae;
    }

    @Override
    public boolean execute(Reference chosen) {
        
        if (chosen instanceof BookRef) {
            be.setRef(chosen);
            be.execute(chosen);
        } else if (chosen instanceof ArticleRef) {
            ae.setRef(chosen);
            ae.execute(chosen);
        } else {
            io.print("Valitse ensin viite!\n");
        }
        return true;
    }
}
