package commandlineUI;

import database.Database;
import database.BookDAO;
import database.DAO;
import reference.BookRef;
import io.IO;

public class BookAdder extends AbstractAdder<BookRef> {

    private String authors;
    private String publisher;
    private String address;
    private int year;
    private String title;

    public BookAdder(DAO<BookRef> bookDao, IO io) {

        super(bookDao, io);

        options = new String[10];
        options[0] = "Kirjaviitteen lisääminen:\n";
        options[1] = "1 Teoksen nimi";
        options[2] = "2 Kirjoittaja(t)";
        options[3] = "3 Julkaisuvuosi";
        options[4] = "4 Kustantaja";
        options[5] = "5 Kustantajan osoite";
        options[6] = "6 Näytä syötetyt tiedot";
        options[7] = "7 Tallenna ja lopeta";
        options[8] = "8 Listaa vaihtoehdot";
        options[9] = "9 Lopeta tallentamatta";

        super.setOptions(options);

    }

    public void addBookToDB() {
        loop();
    }

    protected String addPublisher() {
        return io.readLine("Anna julkaisija:");
    }

    protected String addAddress() {
        return io.readLine("Anna julkaisijan osoite:");
    }

    protected boolean saveBookToDatabase() {
        // Palauttaa true jos tallennus ei onnistu
        if (!bookReadyForDb()) {
            io.print("Tallennus epäonnistui\n");
            return true; // HUOM!!
        }

        try {
            BookRef book = new BookRef(authors, title, publisher, Integer.toString(year), address);
            dao.add(book);
            io.print("Viite lisätty onnistuneesti\n");
            return false;
        } catch (Exception e) {
            io.print(e.getMessage());
            return true;

        }
    }

    protected void printBookStatus() {
        if (this.title != null) {
            io.print("Nimi: " + this.title);
        }
        if (this.authors != null) {
            io.print("Tekijä(t): " + this.authors);
        }
        if (this.year > 0) {
            io.print("Julkaisuvuosi: " + this.year);
        }
        if (this.publisher != null) {
            io.print("Kustantaja: " + this.publisher);
        }
        if (this.address != null) {
            io.print("Kustantajan osoite:" + this.address);
        }
    }

    private boolean bookReadyForDb() {
        return (isValidString(authors) && isValidString(title) && isValidYear(year) && isValidString(publisher));
    }

    public void loop() {

        String response, command;
        boolean responseValid;
        boolean again = true;

        while (again) {

            this.listOptions();
            command = io.readLine("Valitse toiminto (1-9)");

            if (command.isEmpty()) {
                listOptions();
            }

            switch (command) {
                case "1":
                    response = addTitle();
                    responseValid = this.isValidString(response);
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
                    userFeedback(response, responseValid);
                    break;

                case "5":
                    response = addAddress();
                    responseValid = isValidString(response);
                    if (responseValid) {
                        this.address = response;
                    }
                    userFeedback(response, responseValid);
                    break;

                case "6":
                    printBookStatus();
                    break;

                case "7":
                    again = saveBookToDatabase();
                    break;

                case "8":
                    listOptions();
                    break;

                case "9":
                    again = false;
                    break;

                default:
                    break;
            }

        }
    }
}
