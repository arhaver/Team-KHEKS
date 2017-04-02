package reference;

public abstract class AbstractReference {

    protected String title;
    protected String authors;
    protected String year;

    public AbstractReference(String authors, String title, String year) {
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
}
