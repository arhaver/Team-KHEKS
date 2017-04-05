import io.ConsoleIO;
import commandlineUI.MainMenu;
import database.Database;

public class Main {    
      
    public static void main(String[] args){
        ConsoleIO io = new ConsoleIO();
        Database db = new Database("SD");
        MainMenu mm = new MainMenu(db, io);
        mm.openMainMenu();        
    }
}
