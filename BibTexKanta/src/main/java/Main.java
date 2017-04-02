
import commandlineUI.BookAdder;
import database.Database;


public class Main {
    
    public static void main(String[] args){
        Database db = new Database("SD");
        
        BookAdder ba = new BookAdder();
        ba.addBookToDB(db);
    }
}
