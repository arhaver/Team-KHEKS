import io.ConsoleIO;
import commandlineUI.MainMenu;
import database.InMemoryDAO;
import reference.ArticleRef;
import reference.BookRef;

public class Main {    
      
    public static void main(String[] args){
        InMemoryDAO<BookRef> bdao = new InMemoryDAO<>();
        InMemoryDAO<ArticleRef> adao = new InMemoryDAO<>();
        ConsoleIO io = new ConsoleIO();
        MainMenu mm = new MainMenu(adao, bdao, io);
        mm.openMainMenu();        
    }
}
