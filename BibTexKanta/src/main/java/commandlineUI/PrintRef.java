package commandlineUI;

import database.DAO;
import io.IO;
import java.util.List;
import java.util.Map;
import reference.InproceedingsRef;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;

/*
Luokka joka tulostaa ihmisluettavasti tallennettavat referenssit
 */
public class PrintRef implements Command {

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
        if (artcls != null) {
            printArticles(artcls);
        }
        if (books != null) {
            printBooks(books);
        }
        if (inproceedings != null) {
            printInproceedings(inproceedings);
        }
    }

    public void printAnyRef(Reference newRef) {
        if (newRef instanceof ArticleRef) {
            printArticle(newRef);
        } else if (newRef instanceof BookRef) {
            printBook(newRef);
        } else if (newRef instanceof InproceedingsRef) {
            printInproceed(newRef);
        }
    }

    private void printArticle(Reference article) {
        io.print("Title: " + article.getField("title"));
        io.print("Author(s): " + article.getField("authors"));
        io.print("Year: " + article.getYear());
        if(article.getField("publisher") != null) io.print("Publisher: " + article.getField("publisher"));
        if(article.getField("address") != null) io.print("Address: " + article.getField("address"));
        io.print("Volume: " + article.getField("volume"));
        io.print("Journal: " + article.getField("journal"));
        if(article.getField("number") != null) io.print("Number: " + article.getField("number"));
        if(article.getField("pages") != null) io.print("Pages: " + article.getField("pages"));
        if(article.getField("month") != null) io.print("Month: " +article.getField("month"));
        if(article.getField("note") != null) io.print("Notes: " +article.getField("note"));
        io.print("Tex Id: " + article.getField("bibTexId"));
        io.print("");
    }

    private void printBook(Reference book) {
        io.print("Title: " + book.getField("title"));
        io.print("Author(s): " + book.getField("authors"));
        io.print("Year: " + book.getYear());
        io.print("Publisher: " + book.getField("publisher"));
        if(book.getField("address") != null) io.print("Address: " + book.getField("address"));
        if(book.getField("series") != null) io.print("Series: " +book.getField("series"));
        if(book.getField("edition") != null) io.print("Edition: " +book.getField("edition"));
        if(book.getField("month") != null) io.print("Month: " +book.getField("month"));
        if(book.getField("note") != null) io.print("Notes: " +book.getField("note"));
        io.print("Tex Id: " + book.getField("bibTexId"));
        io.print("");
    }

    private void printInproceed(Reference inproceed) {
        io.print("Title: " + inproceed.getField("title"));
        io.print("Book Title: " + inproceed.getField("booktitle"));
        io.print("Author(s): " + inproceed.getField("authors"));
        io.print("Year: " +inproceed.getYear());
        if(inproceed.getField("pages") != null) io.print("Pages: " + inproceed.getField("pages"));
        if(inproceed.getField("publisher") != null) io.print("Publisher: " + inproceed.getField("publisher"));
        if(inproceed.getField("address") != null) io.print("Address: " + inproceed.getField("address"));
        if(inproceed.getField("editor") != null) io.print("Editor: " +inproceed.getField("editor"));
        if(inproceed.getField("number") != null) io.print("Number: " +inproceed.getField("number"));
        if(inproceed.getField("series") != null) io.print("Series: " +inproceed.getField("series"));
        if(inproceed.getField("month") != null) io.print("Month: " +inproceed.getField("month"));
        if(inproceed.getField("organization") != null) io.print("Organization: " +inproceed.getField("organization"));
        if(inproceed.getField("note") != null) io.print("Notes: " +inproceed.getField("note"));
        io.print("Tex Id: " + inproceed.getField("bibTexId"));
        io.print("");
    }

    private void printArticles(List<Reference> articles) {
        for (Reference article : articles) {
            printArticle(article);
        }
    }

    private void printBooks(List<Reference> books) {
        for (Reference book : books) {
            printBook(book);
        }
    }
    
    private void printInproceedings(List<Reference> inproceedings) {
        for(Reference inproceed : inproceedings) {
            printInproceed(inproceed);
        }
    }

    @Override
    public boolean execute(Reference ref) {
        printRef();

        return true;
    }
}