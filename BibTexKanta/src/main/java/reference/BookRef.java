package reference;

public class BookRef extends AbstractReference {
    
    private String publisher;
    private String address;
    
    public BookRef(String authors, String title, String publisher, String year, String address) {
        super(authors, title, year);
        
        this.publisher = publisher;
        this.address = address;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
