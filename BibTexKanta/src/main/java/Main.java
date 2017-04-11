import io.ConsoleIO;
import commandlineUI.MainMenu;
import database.InMemoryDAO;
import reference.Reference;

public class Main {    
      
    public static void main(String[] args){
        InMemoryDAO<Reference> dao = new InMemoryDAO<>();
        ConsoleIO io = new ConsoleIO();
        MainMenu mm = new MainMenu(dao, io);
        mm.openMainMenu();        
    }
}
