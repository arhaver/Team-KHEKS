import bibtex.Translator;
import io.ConsoleIO;
import commandlineUI.menu.MainMenu;
import database.DaoService;
import database.InMemoryDAO;
import io.FileWriter;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;

public class Main {    
      
    public static void main(String[] args){
        InMemoryDAO<BookRef> bdao = new InMemoryDAO<>();
        InMemoryDAO<ArticleRef> adao = new InMemoryDAO<>();
        InMemoryDAO<InproceedingsRef> idao = new InMemoryDAO<>();
        
        DaoService ds = new DaoService(bdao, adao, idao);
        bdao.setDaoService(ds);
        adao.setDaoService(ds);
        idao.setDaoService(ds);      
        
        ConsoleIO io = new ConsoleIO();
        FileWriter writer = new FileWriter();
        Translator trans = new Translator();
        
        MainMenu mm = new MainMenu(adao, bdao, idao, io, writer, trans);
        mm.execute(null);
    }
}
