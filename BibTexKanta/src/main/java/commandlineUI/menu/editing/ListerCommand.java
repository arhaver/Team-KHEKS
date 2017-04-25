package commandlineUI.menu.editing;

import commandlineUI.Command;
import database.DAO;
import io.IO;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;

public class ListerCommand implements Command{
    
    private DAO<BookRef> bdao;
    private DAO<ArticleRef> adao;
    private IO io;

    public ListerCommand(IO io, DAO<BookRef> bdao, DAO<ArticleRef> adao) {
        this.bdao = bdao;
        this.adao = adao;
        this.io = io;
    }

    @Override
    public boolean execute(Reference ref) {
        //annan listan refeistä muokkausvalintavalikolle --> todo
        return true;
    }
}
