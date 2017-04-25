/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reference;

/**
 *
 * @author Arto
 */
public class InproceedingsRef extends AbstractReference implements Reference {

    public InproceedingsRef() {
        fields.put("booktitle", null);
        fields.put("pages", null);
        fields.put("publisher", null);
        fields.put("address", null);
    }

    /*
    public InproceedingsRef(String authors, String title, String booktitle, String year, String publisher, String address, String pages, String bibtexid) {
        fields.put("authors", authors);
        fields.put("title", title);
        fields.put("booktitle", booktitle);
        fields.put("publisher", publisher);  
        fields.put("address", address);
        fields.put("pages", pages);
        fields.put("bibTexId", bibtexid);
        
        try {
            this.year = Integer.getInteger(year);
        } catch (Error e) {

        }
    }
     */
    @Override
    public boolean readyForDb() {
        return (isValidString("title", fields.get("title")) && isValidString("authors", fields.get("authors")) && isValidYear(year) && isValidString("booktitle", fields.get("booktitle")));
    }

}
