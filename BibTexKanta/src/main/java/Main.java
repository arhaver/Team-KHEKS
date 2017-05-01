
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
        BookRef book1, book2, book3, book4, book5, book6;
        
        book1 = new BookRef("AwesomeBook I", "Writer, Tim", "Academic Awesome", 2006);
        book1.setField("address", "Philadelpia, PA");
        book1.addTag("XP");
        book1.addTag("Agile");
        
        book2 = new BookRef("Easyname2", "Ellison, Ewald", "Academic Awesome", 2012);
        book2.setField("address", "PA, USA");
        book2.addTag("suosikki");
        
        book3 = new BookRef("The Book on Scrum", "Writer, Tim", "University Press", 2012);
        book3.setField("address", "NY, USA");
        book3.addTag("Agile");
        book3.addTag("Scrum");
        book3.addTag("suosikki");
        
        book4 = new BookRef("Easyname1", "Authors of books", "Publisher of books", 2012);
        book4.setField("address", "NY, USA");
        book4.addTag("suosikki");
        
        book5 = new BookRef("Address book", "Andy Addressable", "Address Publishing", 1990);
        book5.setField("address", "NY, USA");
        book5.addTag("suosikki");
        
        book6 = new BookRef("Kanban", "Eric Kan, Bart Ban", "K Publishing House", 2015);
        book6.setField("address", "NY, USA");
        book6.addTag("kanban");
        book6.addTag("agile");
  
        
        service.generateId(book1);
        service.generateId(book2);
        service.generateId(book3);
        service.generateId(book4);
        service.generateId(book5);
        service.generateId(book6);
        
        bdao.add(book1);
        bdao.add(book2);
        bdao.add(book3);
        bdao.add(book4);
        bdao.add(book5);
        bdao.add(book6);
    }
}
