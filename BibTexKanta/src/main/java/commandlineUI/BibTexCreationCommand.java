package commandlineUI;

import bibtex.IBibtexTranslator;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.List;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;

public class BibTexCreationCommand implements Command{

    private final IBibtexTranslator translator;
    private final IFilewriter filewriter;
    
    private final IO io;
    private final DAO<BookRef> bookDAO;
    private final DAO<ArticleRef> articleDAO;

    public BibTexCreationCommand(IBibtexTranslator translator, IFilewriter filewriter, IO io, DAO<BookRef> bookDAO, DAO<ArticleRef> articleDAO) {
        this.translator = translator;
        this.filewriter = filewriter;
        this.io = io;
        this.bookDAO = bookDAO;
        this.articleDAO = articleDAO;
    }
    
    @Override
    public boolean execute(Reference ref) {
        String filename = io.readLine("Anna tiedostonimi ");
        try{
            printBibTex(filename);
            io.print("BibTex tulostettu tiedostoon: "+filename);
            return false;
        }catch(Exception e){
            io.print("Virhe BibTex-tiedostoa luodessa: "+e.getMessage());
            return true;
        }
    }
    
    private void printBibTex(String filename) throws Exception{
        List<String> lines = translator.bibTex(bookDAO, articleDAO);
        filewriter.write(filename, lines);
    }
    
}
