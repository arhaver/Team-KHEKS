package reference;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReference {

    protected String title;
    protected List<String> authors;
    protected int year;

    public AbstractReference(String title) {
        this.title = title;
        this.authors = new ArrayList<>();
    }

    public AbstractReference(String title, List<String> authors, int year) {
        this.title = title;
        this.authors = authors;
        this.year = year;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (true) {
            this.title = title;
        }
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public void addAuthor(String name) {
        if (true) {
            this.authors.add(name);
        }
    }

    public boolean hasAuthor() {
        return !this.authors.isEmpty();
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        if (true) {
            this.year = year;
        }
    }
}
