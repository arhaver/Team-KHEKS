package reference;

public class BookRef extends Reference {
    
    private String publisher;
    private String address;
    
    public BookRef(String authors, String title, String publisher, String year, String address) {
        super("book");
        super.setPublisher(publisher);
        super.setAuthors(authors);
        super.setYear(Integer.parseInt(year));
        super.setAddress(address);
    }    
}
