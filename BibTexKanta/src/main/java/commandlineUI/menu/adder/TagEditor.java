/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandlineUI.menu.adder;

import commandlineUI.AddTagCommand;
import commandlineUI.Command;
import commandlineUI.ListTagsCommand;
import commandlineUI.RemoveTagCommand;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import io.IO;
import java.util.Map;


public class TagEditor extends Menu {

    public TagEditor(IO io) {       
               super(io, new String[0],
                new String[]{
                    "TAGIEN LISÄÄMINEN / MUOKKAAMINEN:\n",
                    "1 Lisää tägi",
                    "2 Poista tägi",
                    "3 Listaa tägit",
                    "4 Lopeta tägien muokkaus"
                });

        Map<String, Command> commands = super.getCommands();
        
        commands.put("1", new AddTagCommand(io));
        commands.put("2", new RemoveTagCommand(io));
        commands.put("3", new ListTagsCommand(io));
        commands.put("4", new QuitCommand());
        
        super.setDefaultCommand(new PredefinedPrintCommand("Virheellinen komento", io));
    }    
}
