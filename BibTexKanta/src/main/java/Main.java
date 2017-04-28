
import bibtex.Translator;
import io.ConsoleIO;
import commandlineUI.menu.MainMenu;
import database.InMemoryDAO;
import io.FileWriter;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import service.BibTexIdService;
import service.DaoService;

public class Main {

    public static void main(String[] args) {
        InMemoryDAO<BookRef> bdao = new InMemoryDAO<>();
        InMemoryDAO<ArticleRef> adao = new InMemoryDAO<>();
        InMemoryDAO<InproceedingsRef> idao = new InMemoryDAO<>();   
        
        ConsoleIO io = new ConsoleIO();
        FileWriter writer = new FileWriter();
        Translator trans = new Translator();
        BibTexIdService service = new BibTexIdService();
        generateReferences(service, adao, bdao, idao);
        
        MainMenu mm = new MainMenu(adao, bdao, idao, io, writer, trans, service);
        mm.execute(null);
    }
    
        private static void generateReferences (BibTexIdService service, InMemoryDAO<ArticleRef> adao, InMemoryDAO<BookRef> bdao, InMemoryDAO<InproceedingsRef> idao) {
        
        //BookRefs
        BookRef book1 = new BookRef();
        book1.setYear(1999);
        book1.setField("title", "AwesomeBook I");
        book1.setField("authors", "Writer, Tim and Helper, Tina");
        book1.setField("publisher", "Academic Awesome");
        book1.setField("address", "Philadelpia, PA");
        book1.addTag("XP");
        
        BookRef book2 = new BookRef();
        book2.setYear(2000);
        book2.setField("title", "AwesomeBook II");
        book2.setField("authors", "Writer, Tim, Helper, Tina and Secondhelper, Tom");
        book2.setField("publisher", "Academic Awesome");
        book2.setField("address", "PA, USA");
        book2.addTag("XP");
        book2.addTag("Scrum");
        
        BookRef book3 = new BookRef();
        book3.setYear(2012);
        book3.setField("title", "The Book on Scrum");
        book3.setField("authors", "Ellison, Ewald");
        book3.setField("publisher", "University Press");
        book3.setField("address", "NY, USA");
        book3.addTag("Agile");
        book3.addTag("Scrum");
        book3.addTag("favourite");
        
        service.generateId(book1);
        service.generateId(book2);
        service.generateId(book3);
        bdao.add(book1);
        bdao.add(book2);
        bdao.add(book3);
    }
}
