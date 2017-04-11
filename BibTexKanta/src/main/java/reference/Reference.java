package reference;

import java.util.HashMap;

public class Reference {

    private final int CURRENT_YEAR = 2017;

    private String type;
    private String title;
    private String authors;
    private String publisher;
    private String address;
    private String booktitle;
    private String bibTexId;
    private String pages;
    private String volume;
    private String number;
    private String journal;
    private int year;

    public Reference(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean setType(String type) {
        this.type = type;
        return true;
    }

    public String getTitle() {
        return title;
    }

    public boolean setTitle(String title) {
        if (isValidString(title)) {
            this.title = title;
            return true;
        }
        return false;
    }

    public String getAuthors() {
        return authors;
    }

    public boolean setAuthors(String authors) {
        if (isValidString(authors)) {
            this.authors = authors;
            return true;
        }
        return false;
    }

    public int getYear() {
        return year;
    }

    public boolean setYear(int year) {
        if (isValidYear(year)) {
            this.year = year;
            return true;
        }
        return false;
    }

    public String getPublisher() {
        return publisher;
    }

    public boolean setPublisher(String publisher) {
        if (isValidString(publisher)) {
            this.publisher = publisher;
            return true;
        }
        return false;
    }

    public String getAddress() {
        return address;
    }

    public boolean setAddress(String address) {
        if (isValidString(address)) {
            this.address = address;
            return true;
        }
        return false;
    }

    public String getBibTexId() {
        return bibTexId;
    }

    public boolean setBibTexId(String bibTexId) {
        if (isValidString(bibTexId)) {
            this.bibTexId = bibTexId;
            return true;
        }
        return false;
    }

    public String getVolume() {
        return volume;
    }

    public boolean setVolume(String volume) {
        if (isValidString(volume)) {
            this.volume = volume;
            return true;
        }
        return false;
    }

    public String getNumber() {
        return bibTexId;
    }

    public boolean setNumber(String number) {
        if (isValidString(number)) {
            this.number = number;
            return true;
        }
        return false;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public boolean setBooktitle(String booktitle) {
        if (isValidString(booktitle)) {
            this.booktitle = booktitle;
            return true;
        }
        return false;
    }

    public String getJournal() {
        return journal;
    }

    public boolean setJournal(String journal) {
        if (isValidString(journal)) {
            this.journal = journal;
            return true;
        }
        return false;
    }

    public String getPages() {
        return pages;
    }

    public boolean setPages(String pages) {
        this.pages = pages;
        return true;
    }

    public boolean isValidYear(int year) {
        return (0 < year && year <= CURRENT_YEAR);
    }

    public boolean isValidString(String input) {
        return input != null && input.length() > 2;
    }

    public boolean readyForDb() {
        if (this.type.matches("book")) {
            return (isValidString(authors) && isValidString(title) && isValidYear(year) && isValidString(publisher));
        } else if (this.type.matches("article")) {
            return (isValidString(authors) && isValidString(title) && isValidYear(year) && isValidString(journal));
        }
        return false;
    }

    public HashMap<String, String> getAllFields() {
        HashMap<String, String> all = new HashMap<>();
        all.put("title", title);
        all.put("authors", authors);
        all.put("publisher", publisher);
        all.put("address", address);
        all.put("booktitle", booktitle);
        all.put("bibTexId", bibTexId);
        all.put("pages", pages);
        all.put("volume", volume);
        all.put("number", number);
        all.put("journal", journal);
        all.put("year", Integer.toString(year));
        return all;
    }

    @Override
    public String toString() {
        String response = "";
        if (this.title != null) {
            response += "title: " + this.title + "\n";
        }
        if (this.authors != null) {
            response += "authors: " + this.authors + "\n";
        }
        if (this.publisher != null) {
            response += "publisher: " + this.publisher + "\n";
        }
        if (this.address != null) {
            response += "address: " + this.address + "\n";
        }
        if (this.booktitle != null) {
            response += "booktitle: " + this.booktitle + "\n";
        }
        if (this.bibTexId != null) {
            response += "bibTexId: " + this.bibTexId + "\n";
        }
        if (this.volume != null) {
            response += "volume: " + this.volume + "\n";
        }

        if (this.number != null) {
            response += "number: " + this.number + "\n";
        }

        if (this.journal != null) {
            response += "journal: " + this.journal + "\n";
        }

        if (this.year > 0) {
            response += "year: " + this.year + "\n";
        }
        return response;
    }

}
