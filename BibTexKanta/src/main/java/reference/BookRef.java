package reference;

public class BookRef extends AbstractReference implements Reference {

    public BookRef() {
        fields.put("publisher", null);
        fields.put("address", null);

    }
    
    public BookRef(String title, String authors, String publisher, int year){
        this();
        fields.put("title", title);
        fields.put("authors", authors);
        fields.put("publisher", publisher);
        
        this.year = year;
    }

    /*
    public BookRef(String authors, String title, String publisher, String year, String address) {
        fields.put("publisher", publisher);
        fields.put("address", address);
        fields.put("title", title);
        try {
            this.year = Integer.getInteger(year);
        } catch (Error e) {

        }
    }
     */
    @Override
    public boolean readyForDb() {
        return (isValidString("title", fields.get("title")) 
                && isValidString("authors", fields.get("authors")) 
                && isValidYear(year) 
                && isValidString("publisher", fields.get("publisher")));
    }

}
