package commandlineUI;

import bibtex.IBibtexTranslator;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.List;
import reference.BookRef;

/*
Käyttöliittymäluokka joka hoitaa bibtex-tiedoston tulostamisen
*/
public class BibTexUI {
    
    private final IBibtexTranslator translator;
    private final IFilewriter filewriter;
    
    private final IO io;
    private final DAO<BookRef> bookDAO;
    
    public BibTexUI(IBibtexTranslator translator, IFilewriter filewriter, IO io, DAO<BookRef> bookDAO){
        this.io = io;
        this.bookDAO = bookDAO;
        
        this.translator = translator;
        this.filewriter = filewriter;
    }
    
    public void printLoop(){
        io.print("\nBibText-tiedoston tulostus:");
        
        while(true){
            switch(io.readLine("\nVaihtoehdot:\n1.Tulosta\nQ.Peruuta").toLowerCase()){
                case "1":
                    if(printOption()) return;
                    break;
                case "q":
                    return;
                default:
                    io.print("Epävalidi operaatio");
            }
        }
    }
    
    private boolean printOption(){
        String filename = io.readLine("Anna tiedostonimi ");
        try{
            printBibTex(filename);
            io.print("BibTex tulostettu tiedostoon: "+filename);
            return true;
        }catch(Exception e){
            io.print("Virhe BibTex-tiedostoa luodessa: "+e.getMessage());
            return false;
        }
    }
    
    private void printBibTex(String filename) throws Exception{
        List<String> lines = translator.bibTex(bookDAO);
        filewriter.write(filename, lines);
    }
}
