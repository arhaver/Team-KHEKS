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
import io.IO;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import reference.Reference;

public class EditorSelectCommand implements Command {
    
    private ArticleEditor ae;
    private BookEditor be;
    private InproceedingsEditor ie;
    
    public EditorSelectCommand(IO io, BookEditor be, ArticleEditor ae, InproceedingsEditor ie) {
        this.be = be;
        this.ae = ae;
        this.ie = ie;
    }

    @Override
    public boolean execute(Reference chosen) {
        
        AbstractEditor ed;
        
        if (chosen instanceof BookRef) {
            ed = be;
        } else if (chosen instanceof InproceedingsRef) {
            ed = ie;
        }else{
            ed = ae;
        }
        
        ed.setRef(chosen);
        ed.execute(chosen);
        
        return true;
    }
}
