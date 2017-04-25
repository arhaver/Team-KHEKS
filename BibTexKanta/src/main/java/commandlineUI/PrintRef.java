package commandlineUI;

import database.DAO;
import io.IO;
import java.util.List;
import java.util.Map;
import reference.Reference;

/*
Luokka joka tulostaa ihmisluettavasti tallennettavat referenssit
 */
public class PrintRef implements Command {

    private final DAO adao, bdao;
    private final IO io;

    public PrintRef(DAO bdao, DAO adao, IO io) {
        this.adao = adao;
        this.bdao = bdao;
        this.io = io;
    }

    public void printRef() {
        List<Reference> artcls = adao.findAll();
        List<Reference> books = bdao.findAll();
        if (!artcls.isEmpty()) {
            printArticles(artcls);
        }
        if (!books.isEmpty()) {
            printBooks(books);
        }
    }

    private void printArticles(List<Reference> articles) {
        for (Reference article : articles) {
            io.print("Title: " + article.getField("title"));
            io.print("Author(s): " + article.getField("authors"));
            io.print("Year: " + article.getYear());
            io.print("Publisher: " + article.getField("publisher"));
            io.print("Address: " + article.getField("address"));
            io.print("Volume: " + article.getField("volume"));
            io.print("Journal: " + article.getField("journal"));
            io.print("Number: " + article.getField("number"));
            io.print("Pages: " + article.getField("pages"));
            io.print("");
        }
    }

    private void printBooks(List<Reference> books) {
        for (Reference book : books) {
            io.print("Title: " + book.getField("title"));
            io.print("Author(s): " + book.getField("authors"));
            io.print("Year: " + book.getYear());
            io.print("Publisher: " + book.getField("publisher"));
            io.print("Address: " + book.getField("address"));
            io.print("");
        }
    }

    @Override
    public boolean execute(Reference ref) {
        printRef();

        return true;
    }
}
