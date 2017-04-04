
import commandlineUI.MainMenu;
import database.Database;

public class Main {    
      
    public static void main(String[] args){
        
        Database db = new Database("SD");
        MainMenu mm = new MainMenu(db);
        mm.openMainMenu();        
    }
}
