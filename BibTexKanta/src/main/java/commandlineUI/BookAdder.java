package commandlineUI;

import database.Database;
import database.BookDAO;
import reference.BookRef;
import io.IO;
import io.ConsoleIO;

public class BookAdder {

    private final IO io;
    private final Database db;
    private final int CURRENT_YEAR = 2017;
    private String authors;
    private String publisher;
    private String address;
    private int year;
    private String title;
    private final String[] options = new String[10];

    public BookAdder(Database db) {
        this.io = new ConsoleIO();
        this.db = db;
        this.options[0] = "Kirjaviitteen lisääminen:\n";
        this.options[1] = "1 Teoksen nimi";
        this.options[2] = "2 Kirjoittaja(t)";
        this.options[3] = "3 Julkaisuvuosi";
        this.options[4] = "4 Kustantaja";
        this.options[5] = "5 Kustantajan osoite";
        this.options[6] = "6 Näytä syötetyt tiedot";
        this.options[7] = "7 Tallenna ja lopeta";
        this.options[8] = "8 Listaa vaihtoehdot";
        this.options[9] = "9 Lopeta tallentamatta";
    }

    public void loop() {

        String response, command;
        boolean responseValid;
        boolean again = true;
        listOptions();

        while (again) {

            command = io.readLine("\nValitse toiminto (1-9)");

            switch (command) {
                case "1":
                    response = addTitle();
                    responseValid = isValidString(response);
                    if (responseValid) {
                        this.title = response;
                    }
                    userFeedback(response, responseValid);
                    break;

                case "2":
                    response = addAuthor();
                    responseValid = isValidString(response);
                    if (responseValid) {
                        this.authors = response;
                    }
                    userFeedback(response, responseValid);
                    break;

                case "3":
                    int y = addYear();
                    responseValid = isValidYear(y);
                    if (responseValid) {
                        this.year = y;
                    }
                    userFeedback(Integer.toString(y), responseValid);
                    break;

                case "4":
                    response = addPublisher();
                    responseValid = isValidString(response);
                    if (responseValid) {
                        this.publisher = response;
                    }
                    userFeedback(publisher, responseValid);
                    break;

                case "5":
                    response = addAddress();
                    responseValid = isValidString(response);
                    if (responseValid) {
                        this.address = response;
                    }
                    userFeedback(publisher, responseValid);
                    break;

                case "6":
                    if (this.title != null)
                        io.print("Nimi: " + this.title);
                    if (this.authors != null) 
                        io.print("Tekijä(t): " + this.authors);
                    if (this.year > 0)
                        io.print("Julkaisuvuosi: " + this.year);                    
                    if (this.publisher != null)
                        io.print("Kustantaja: " + this.publisher);
                    if (this.address != null)
                        io.print("Kustantajan osoite:" + this.address);
                    break;

                case "7":
                    try {
                        BookRef book = new BookRef(authors, title, publisher, Integer.toString(year), address);
                        BookDAO bd = new BookDAO(db);
                        bd.add(book);
                        again = false;
                        io.print("Viite lisätty onnistuneesti\n");
                    } catch (Exception e) {
                        io.print("Tallennus epäonnistui\n");
                        io.print(e.getMessage());

                    }
                    break;

                case "8":
                    listOptions();
                    break;

                case "9":
                    again = false;
                    break;

                case "q":
                    again = false;
                    break;

                default:
                    break;
            }

        }
    }

    public void addBookToDB() {
        loop();

    }

    private void userFeedback(String input, boolean success) {

        if (!success)
            io.print("Lisäys '" + input + "' virheellinen\n");
    }

    private String addTitle() {
        return io.readLine("Anna teoksen nimi:");
    }

    private String addAuthor() {
        return io.readLine("Anna tekijä(t):");
    }

    private int addYear() {
        return io.readInt("Anna julkaisuvuosi:");
    }

    private String addPublisher() {
        return io.readLine("Anna julkaisija:");
    }

    private String addAddress() {
        return io.readLine("Anna julkaisijan osoite:");
    }

    private void listOptions() {
        io.print("");
        for (int a = 0; a < this.options.length; a++) {
            io.print(options[a]);
        }        
    }

    private boolean isValidYear(int year) {
        if (0 < year && year <= CURRENT_YEAR) {
            return true;
        }
        return false;
    }

    private boolean isValidString(String input) {

        if (input.length() > 2) {
            return true;
        }
        return false;
    }
}
