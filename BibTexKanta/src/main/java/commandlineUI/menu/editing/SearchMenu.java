package commandlineUI.menu.editing;

import commandlineUI.Command;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.editing.ChooseMenu;
import io.IO;
import java.util.Map;
import reference.Reference;
import service.search.SearchService;

public class SearchMenu extends Menu{

    private final ChooseMenu chooseMenu;
    private final SearchService search;
    
    public SearchMenu(IO io, ChooseMenu choose, SearchService search) {
        super(io, new String[]{
            "Yksinkertainen haku:\n",
            " Tägillä: \t\"T:<haettu tägi>\" (esim. \"T:suosikki\")",
            " Kentällä: \t\"F:<kentän nimi>:<kentän arvo>\" (esim. \"F:title:Easyname1\")",
            "\nMonimutkaisempia hakuja:\n",
            " Negaatio: \t\"-(<haku>)\" (esim. \"-(F:title:Easyname1)\")",
            " Yhdiste: \t\"(<haku1>)v(<haku2>)\" (esim. \"(T:Scrum)v(T:XP)\"",
            " Leikkaus: \t\"(<haku1>)^(<haku2>)\" (esim. \"(T:Scrum)^(T:XP)\"\n"           
        }, 
                new String[]{
                    "Anna hakukriteeri (q peruuttaa):"
                });
        
        this.chooseMenu = choose;
        this.search = search;
        
        this.getCommands().put("q", new QuitCommand());
    }

    @Override
    public boolean actOnLine(String line){
        Command c = super.getCommands().get(line);
        
        if(c != null){
            return c.execute(super.referenceToGiveToCommands());
        }
        
        try{
            Map<Integer, Reference> searched = search.searchReferenceMap(chooseMenu.getReferences(), line);
            chooseMenu.setReferences(searched);
            return false;
        }catch(Exception e){
            io.print("Virhe hakutekstissä: "+e.getMessage());
            return true;
        }
    }
    
}
