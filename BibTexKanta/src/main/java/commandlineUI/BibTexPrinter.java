package commandlineUI;

import io.IO;

public class BibTexPrinter {
    
    private final IO io;
    
    public BibTexPrinter(IO io){
        this.io = io;
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
            io.print("BibText tulostettu tiedostoon "+filename);
            return true;
        }catch(Exception e){
            io.print("Virhe BibTex-tiedostoa luodessa: "+e.getMessage());
            return false;
        }
    }
    
    private void printBibTex(String filename) throws Exception{
        
    }
}
