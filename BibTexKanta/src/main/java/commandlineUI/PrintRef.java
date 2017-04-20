
package commandlineUI;
import database.DAO;
import io.IO;
import java.util.List;
import java.util.Map;
import reference.Reference;
/*
Luokka joka tulostaa ihmisluettavasti tallennettavat referenssit
*/
public class PrintRef {
    
    private DAO adao, bdao;
    private IO io;
    
    public PrintRef(DAO bdao, DAO adao, IO io) {
        this.adao = adao;
        this.bdao = bdao;
        this.io = io;
    }
    
    public void printRef() {
        List<Reference> adaos = adao.findAll();
        List<Reference> bdaos = bdao.findAll();
        if(!adaos.isEmpty()) printArticles(adaos);
        if(!bdaos.isEmpty()) printBooks(bdaos);
        }
    
    private void printArticles(List<Reference> adaos) {
        for(Reference ref : adaos) {
            Map<String, String> refs = ref.getAllFields();
            io.print("Publisher: " +refs.get("publisher"));
            io.print("Address: " +refs.get("address"));
            io.print("Journal: " +refs.get("journal"));
            io.print("Number: " +refs.get("number"));
            io.print("Volume: " +refs.get("volume"));
            io.print("Pages: " +refs.get("pages"));
            io.print("");
        }
    }
    
    private void printBooks(List<Reference> bdaos) {
        for(Reference ref : bdaos) {
            Map<String, String> refs = ref.getAllFields();
            io.print("Publisher: " +refs.get("publisher"));
            io.print("Address: " +refs.get("address"));
            io.print("Title: " +refs.get("title"));
            io.print("Year: " +refs.get("Year"));
            io.print("");
        }
    }
}
