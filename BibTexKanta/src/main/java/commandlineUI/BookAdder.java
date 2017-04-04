package commandlineUI;

import database.Database;
import database.BookDAO;
import reference.BookRef;
import io.IO;

public class BookAdder extends AbstractAdder {

    private String authors;
    private String publisher;
    private String address;
    private int year;
    private String title;

    public BookAdder(Database db, IO io) {

        super(db, io);

        String[] options = new String[10];
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

    public void loop() {

        String response, command;
        boolean responseValid;
        boolean again = true;
        this.listOptions();

        while (again) {

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

                default:
                    break;
            }

        }
    }
}
