
package commandlineUI;
import database.DAO;
import io.IO;
import java.util.List;
import java.util.Map;
import reference.InproceedingsRef;
import reference.Reference;
/*
Luokka joka tulostaa ihmisluettavasti tallennettavat referenssit
*/
public class PrintRef implements Command{
    
    private final DAO adao, bdao, idao;
    private final IO io;
    
    public PrintRef(DAO bdao, DAO adao, DAO idao, IO io) {
        this.adao = adao;
        this.bdao = bdao;
        this.idao = idao;
        this.io = io;
    }
    
    public void printRef() {
        List<Reference> artcls = adao.findAll();
        List<Reference> books = bdao.findAll();
        List<Reference> inproceedings = idao.findAll();
        if(artcls != null) printArticles(artcls);
        if(books != null) printBooks(books);
        if(inproceedings != null) printInproceedings(inproceedings);
        }
    
    private void printArticles(List<Reference> articles) {
        for(Reference article : articles) {
            io.print("Title: " +article.getField("title"));
            io.print("Author(s): " +article.getField("authors"));
            io.print("Year: " +article.getYear());
            io.print("Publisher: " +article.getField("publisher"));
            io.print("Address: " +article.getField("address"));
            io.print("Volume: " +article.getField("volume"));
            io.print("Journal: " +article.getField("journal"));
            io.print("Number: " +article.getField("number"));
            io.print("Pages: " +article.getField("pages"));
            io.print("Tex Id: " +article.getField("biTexId"));
            io.print("");
        }
    }
    
    private void printBooks(List<Reference> books) {
        for(Reference book : books) {
            io.print("Title: " +book.getField("title"));
            io.print("Author(s): " +book.getField("authors"));
            io.print("Year: " +book.getYear());
            io.print("Publisher: " +book.getField("publisher"));
            io.print("Address: " +book.getField("address"));
            io.print("Tex Id: " +book.getField("bibTexId"));
            io.print("");
        }
    }
    
    private void printInproceedings(List<Reference> inproceedings) {
        for(Reference inproceed : inproceedings) {
            io.print("Title: " + inproceed.getField("title"));
            io.print("Book Title: " +inproceed.getField("booktitle"));
            io.print("Author(s): " +inproceed.getField("authors"));
            io.print("Pages: " +inproceed.getField("pages"));
            io.print("Publisher: " +inproceed.getField("publisher"));
            io.print("Address: " +inproceed.getField("address"));
            io.print("Tex Id: " +inproceed.getField("bibTexId"));
            io.print("");
        }
    }

    @Override
    public boolean execute(Reference ref) {
        printRef();
        
        return true;
    }
}
