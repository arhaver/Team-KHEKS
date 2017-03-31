package reference;

public class BookRef extends AbstractReference {
    
    protected String publisher;
    public BookRef(String title) {
        super(title);
    }
    
    public void addPublisher (String publisher) {
        if (true)
            this.publisher = publisher;        
    }
    
}
