package commandlineUI;

import database.Database;
import java.util.Scanner;
import reference.BookRef;

public class BookAdder {

    private BookRef book;
    private Scanner reader;
    private boolean titleOK = false;
    private boolean authorOK = false;
    private boolean yearOK = false;
    private boolean publisherOK = false;
    private final String authorNext = "N";
    private final String publisherNext = "P";
    private final String yearNext = "Y";
    private final String titleNext = "T";
    private final String quitNext = "Q";
    private final String readyNext = "R";

    public BookAdder() {
        this.book = new BookRef("temp");
    }

    public void addBookToDB(Database db) {
        System.out.println("Kirjaviitteen syöttäminen.");
        reader = new Scanner(System.in);
        addDetails();
        if (titleOK && authorOK && yearOK && publisherOK) {
            //valmis! Tallenna olio tietokantaan!
        } else {
            if (!titleOK) {
                System.out.println("Teoksen nimi välttämätön!");
            }
            if (!authorOK) {
                System.out.println("Tekijän nimi puuttuu!");
            }
            if (!publisherOK) {
                System.out.println("Julkaisija puuttuu!");
            }
            if (!yearOK) {
                System.out.println("Julkaisuvuosi puuttuu!");
            }
            addDetails();
        }

    }

    private boolean addDetails() {
        String response = reader.nextLine();
        while (!(response.matches("X") || response.matches("Q"))) {
            switch (response) {
                case titleNext:
                    titleOK = addTitle();
                case authorNext:
                    authorOK = addAuthor();
                case yearNext:
                    yearOK = addYear();
                case publisherNext:
                    publisherOK = addPublisher();
                case quitNext:
                    return false;
                default:
                    listOptions();
            }
        }
        return true;
    }

    private boolean addTitle() {
        String title = reader.nextLine();
        if (true) {
            this.book.setTitle(title);
            return true;
        }
        return false;

    }

    private boolean addAuthor() {
        String author = reader.nextLine();
        if (true) {
            this.book.addAuthor(author);
            return true;
        }
        return false;

    }

    private boolean addYear() {
        int year = Integer.getInteger(reader.nextLine());
        if (true) {
            this.book.setYear(year);
            return true;
        }
        return false;

    }

    private boolean addPublisher() {
        String publisher = reader.nextLine();
        if (true) {
            this.book.addPublisher(publisher);
            return true;
        }
        return false;

    }

    private void listOptions() {
        System.out.println("Paina " + titleNext + " ja enter, jos haluat syöttää kirjan nimen.");
        System.out.println("Paina " + authorNext + " ja enter, jos haluat syöttää tekijän nimen.");
        System.out.println("Paina " + yearNext + " ja enter, jos haluat syöttää kirjan julkaisuvuoden.");
        System.out.println("Paina " + publisherNext + " ja enter, jos haluat syöttää kirjan kustantajan.");
        System.out.println("Paina " + readyNext + " ja enter, jos syöttö on valmis");
        System.out.println("Paina " + quitNext + " ja enter, jos haluat keskeyttää viitetiedon syöttämisen");
    }

}
