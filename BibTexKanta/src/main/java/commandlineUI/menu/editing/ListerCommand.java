package commandlineUI.menu.editing;

import commandlineUI.Command;
import database.DAO;
import io.IO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;
import reference.Reference;
import service.BibTexIdService;

public class ListerCommand implements Command{
    
    private DAO<BookRef> bdao;
    private DAO<ArticleRef> adao;
    private IO io;
    private BibTexIdService service;

    public ListerCommand(IO io, DAO<BookRef> bdao, DAO<ArticleRef> adao, BibTexIdService service) {
        this.bdao = bdao;
        this.adao = adao;
        this.io = io;
        this.service = new BibTexIdService();
    }

    @Override
    public boolean execute(Reference ref) {
        HashMap<String, Reference> refMap = buildList();
        ChooseEditedMenu cm = new ChooseEditedMenu(io, service, adao, bdao);
        cm.setReferences(refMap);
        cm.execute(null);
        return true;
    }
    private HashMap<String, Reference> buildList() {
        
        HashMap<String, Reference> returnable = new HashMap<>();
        returnable.put("q", null);
        
        BookRef myBook = new BookRef();
        myBook.setField("title", "kirja1");
        myBook.setField("authors", "jätkät");
        myBook.setField("publisher", "otava");
        myBook.setYear(1999);
        
        BookRef myBook2 = new BookRef();
        myBook2.setField("title", "kirja2");
        myBook2.setField("authors", "käpistäjät");
        myBook2.setField("publisher", "tammi");
        myBook2.setYear(2000);
        
        returnable.put("8", myBook);
        returnable.put("9", myBook2);
  
        List<BookRef> books = bdao.findAll();
        List<ArticleRef> articles = adao.findAll();
        
        int i = 1;
        for (Reference book : books)
            returnable.put(Integer.toString(i++), book);
        for (Reference article : articles)
            returnable.put(Integer.toString(i++), article);

       
        return returnable;       
    }
    
}
