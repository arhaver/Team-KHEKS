package commandlineUI;

import database.DAO;
import io.IO;
import reference.Reference;

public class MainMenu {
    
    private final IO io;
    private final DAO<Reference> dao;
    private final String[] options = new String[6];
    
    public MainMenu(DAO<Reference> dao, IO io) {
        this.io = io;
        this.dao = dao;
        this.options[0] = "Päävalikko:\n";
        this.options[1] = "1 Lisää kirja viiteluetteloon";
        this.options[2] = "2 Lisää artikkeli viiteluetteloon";
        this.options[3] = "3 Listaa viitteet luettavassa muodossa";
        this.options[4] = "4 Listaa viitteet BibTeX-muodossa";
        this.options[options.length - 1] = "Q Lopeta ohjelma";
    }
    
    public void loop() {
        String command;
        boolean again = true;
        
        while (again) {
            
            listOptions();
            command = io.readLine("\nValitse toiminto");
            
            switch (command) {
                case "1":
                    BookAdder newbook = new BookAdder(dao, io);
                    newbook.addBookToDB();
                    break;

                case "2":
                    ArticleAdder newarticle = new ArticleAdder(dao, io);
                    newarticle.addArticleToDB();
                    break;
/**
                case "3":
                    PrintRef humanprint = new PrintRef(bookDAO, io);
                    humanprint.printRef;
                    break;
**/
                case "4":
                    BibTexPrinter newbibprinter = new BibTexPrinter(io);
                    newbibprinter.printLoop();
                    break;

                case "q":
                    again = false;
                    break;
                
                case "Q":
                    again = false;
                    break;
                    
                default:
                    io.print("\nVirheellinen komento");
            }
        }
    }
    
    public void openMainMenu() {

        io.print("\nLähdeviitehallintaohjelma by Team-KHEKS.");
        io.print("\nTERVETULOA!");
        loop();
    }
    
    private void listOptions() {
        io.print("");
        for (String option : this.options) {
            io.print(option);
        }        
    }
}
