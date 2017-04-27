/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.adder.AbstractEditor;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import database.DAO;
import io.IO;
import java.util.HashMap;
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
    private DAO[] daos;
    private BookEditor be;
    private Reference ref;

    
    public EditorSelectCommand(IO io, BookEditor be, ArticleEditor ae) {
        this.be = be;
        this.io = io;
        this.ae = ae;
    }
    
    public void setRef(Reference ref) {
        this.ref = ref;        
    }


    @Override
    public boolean execute(Reference ignorable) {
        
        if (this.ref instanceof BookRef) {
            be.setRef(this.ref);
            be.execute(this.ref);
        } else if (this.ref instanceof ArticleRef) {
            ae.setRef(this.ref);
            ae.execute(this.ref);
        } else {
            io.print("Valitse ensin viite!\n");
        }
        return true;
    }
}
