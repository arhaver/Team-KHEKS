
package reference;

public class PrintRef {
    
    private BookRef bookRef;
    
    public PrintRef() {
        this.bookRef = BookRef.getBookRef();
    }
    
    public void printRef() {
        try {
            System.out.println("Author(s): " +bookRef.authors);
        } catch (NullPointerException e){
            System.out.println("Authors(s):");
        }
        try {
            System.out.println("Title: " +bookRef.title);
        } catch (NullPointerException e) {
            System.out.println("Title: ");
        }
        try {
            System.out.println("Year: " +bookRef.year);
        } catch (NullPointerException e) {
            System.out.println("Year: ");
        }
        try {
            System.out.println("Publisher: " +bookRef.getPublisher());
        } catch (NullPointerException e) {
            System.out.println("Publisher: ");
        }
        try {
            System.out.println("Address: " +bookRef.getAddress());
        } catch (NullPointerException e) {
            System.out.println("Address: ");
        }
    }
}
