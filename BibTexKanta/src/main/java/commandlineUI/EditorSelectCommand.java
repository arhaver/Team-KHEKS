/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI;

import commandlineUI.menu.adder.AbstractEditor;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import commandlineUI.menu.adder.InproceedingsEditor;
import database.DAO;
import io.IO;
import java.util.HashMap;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;

public class EditorSelectCommand implements Command {
    
    private IO io;
    private ArticleEditor ae;
    private DAO[] daos;
    private BookEditor be;
    private Reference ref;
    private InproceedingsEditor ie;

    
    public EditorSelectCommand(IO io, BookEditor be, ArticleEditor ae, InproceedingsEditor ie) {
        this.be = be;
        this.io = io;
        this.ae = ae;
        this.ie = ie;
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
        } else if (this.ref instanceof InproceedingsRef) {
            ie.setRef(this.ref);
            ie.execute(this.ref);
        } else {
            io.print("Valitse ensin viite!\n");
        }
        return true;
    }
}
