
import commandlineUI.BookAdder;
import database.Database;
import java.util.Scanner;


public class Main {    
      
    public static void main(String[] args){
<<<<<<< HEAD
        
        Scanner reader = new Scanner (System.in);
        Database db = new Database("SD");
        BookAdder ba = new BookAdder();
        
        System.out.println("");
        System.out.println("Lähdeviitteidenhallintaohjelma by Team-KHEKS.");
        System.out.println("");
        System.out.println("Tervetuloa!");
        System.out.println("");
        
=======
        
        Scanner reader = new Scanner (System.in);
        Database db = new Database("SD");
        BookAdder ba = new BookAdder(db);
        
        System.out.println("");
        System.out.println("Lähdeviitteidenhallintaohjelma by Team-KHEKS.");
        System.out.println("");
        System.out.println("Tervetuloa!");
        System.out.println("");
        
>>>>>>> master
        while (true){
            System.out.println("1 Lisää uusi kirja lähdeviitteisiin.");
            System.out.println("Q Poistu ohjelmasta.");
            System.out.println("Anna valinta: ");
            String response = reader.nextLine();
            
            if (response.matches("1")){
<<<<<<< HEAD
//                ba.addBookToDB(db);
=======
                ba.addBookToDB();
>>>>>>> master
            } else if(response.matches("q") || response.matches("Q")){
                break;                
            }
        }
    }
}
