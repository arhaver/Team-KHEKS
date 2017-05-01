
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
        
        InproceedingsRef iref1, iref2, iref3;
        
        iref1 = new InproceedingsRef("Important Article on Scrum", "Tim Tam", "Articles on Agile", 2016);
        iref1.addTag("scrum");
        iref1.addTag("agile");
        
        iref2 = new InproceedingsRef("Important Article on XP", "Will Guy", "Articles on Agile", 2016);
        iref2.addTag("xp");
        iref2.addTag("agile");
        
        iref3 = new InproceedingsRef("Important Article on Kanban", "Derek Dude", "Articles on Agile", 2016);
        iref3.addTag("kanban");
        iref3.addTag("agile");
        
        service.generateId(iref1);
        service.generateId(iref2);
        service.generateId(iref3);
        
        idao.add(iref1);
        idao.add(iref2);
        idao.add(iref3);
        
        ArticleRef aref1, aref2, aref3;
        
        aref1 = new ArticleRef("XP XPlored", "Tammy Tongue", "9", "Journal on Agile", "12", 2016);
        aref1.addTag("XP");
        aref1.addTag("agile");
        
        aref2 = new ArticleRef("The Problem With Waterfall", "Chad Chiq", "9", "Journal on Waterfall", "55", 2012);
        aref2.addTag("Waterfall");
        aref2.addTag("problem"); 
        
        aref3 = new ArticleRef("The Problem With CS", "Chad Chiq", "11", "Journal on Computer Science", "16", 2010);
        aref3.addTag("Computer Science");
        aref3.addTag("CS");
        aref3.addTag("problem");
        
        service.generateId(aref1);
        service.generateId(aref2);
        service.generateId(aref3);
        adao.add(aref1);
        adao.add(aref2);
        adao.add(aref3);
        
    }
}
