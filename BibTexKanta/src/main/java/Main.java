
import commandlineUI.MainMenu;
import database.Database;
import database.InMemoryDAO;
import reference.BookRef;

public class Main {    
      
    public static void main(String[] args){
        
        InMemoryDAO<BookRef> bookDAO = new InMemoryDAO<>();
        MainMenu mm = new MainMenu(bookDAO);
        mm.openMainMenu();        
    }
}
