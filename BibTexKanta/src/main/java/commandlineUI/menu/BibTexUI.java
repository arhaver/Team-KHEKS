package commandlineUI.menu;

import bibtex.IBibtexTranslator;
import commandlineUI.Command;
import commandlineUI.QuitCommand;
import database.DAO;
import io.IFilewriter;
import io.IO;
import java.util.List;
import java.util.Map;
import reference.ArticleRef;
import reference.BookRef;

/*
Käyttöliittymäluokka joka hoitaa bibtex-tiedoston tulostamisen
*/
public class BibTexUI extends Menu{
    
    private final IBibtexTranslator translator;
    private final IFilewriter filewriter;
    
    private final IO io;
    private final DAO<BookRef> bookDAO;
    private final DAO<ArticleRef> articleDAO;
    
    public BibTexUI(IBibtexTranslator translator, IFilewriter filewriter, IO io, 
            DAO<BookRef> bookDAO, DAO<ArticleRef> articleDAO){
        super(io, new String[]{"\nBibText-tiedoston tulostus:"},
                new String[]{
                    "Vaihtoehdot:",
                    "1.Tulosta",
                    "Q.Peruuta"
                });
        
        this.io = io;
        this.bookDAO = bookDAO;
        this.articleDAO = articleDAO;
        
        this.translator = translator;
        this.filewriter = filewriter;
        
        addCommandsToMenu(super.getCommands());
    }

    @Override
    protected void addCommandsToMenu(Map<String, Command> menuCommandMap) {
        menuCommandMap.put("q", new QuitCommand());
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
        List<String> lines = translator.bibTex(bookDAO, articleDAO);
        filewriter.write(filename, lines);
    }
}
