import io.ConsoleIO;
import commandlineUI.MainMenu;
import database.InMemoryDAO;
import reference.BookRef;

public class Main {    
      
    public static void main(String[] args){
        InMemoryDAO<BookRef> bookDAO = new InMemoryDAO<>();
        ConsoleIO io = new ConsoleIO();
        MainMenu mm = new MainMenu(bookDAO, io);
        mm.openMainMenu();        
    }
}
