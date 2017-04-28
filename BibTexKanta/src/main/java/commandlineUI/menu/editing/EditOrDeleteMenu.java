
package commandlineUI.menu.editing;

import commandlineUI.Command;
import commandlineUI.DeleteCommand;
import commandlineUI.EditorSelectCommand;
import commandlineUI.common.PredefinedPrintCommand;
import commandlineUI.common.QuitCommand;
import commandlineUI.menu.Menu;
import commandlineUI.menu.adder.ArticleEditor;
import commandlineUI.menu.adder.BookEditor;
import database.DAO;
import io.IO;
import java.util.Map;

public class EditOrDeleteMenu extends Menu{
    
    public EditOrDeleteMenu(IO io, ChooseMenu chooseMenu, BookEditor be, ArticleEditor ae, DAO... daos) {
        super(io, new String[0], new String[0]);
        
        Map<String, Command> commands = super.getCommands();
        
        commands.put("e", new EditorSelectCommand(be, ae));
        commands.put("d", new DeleteCommand(io,chooseMenu,  daos));
        commands.put("q", new QuitCommand());
        
        super.setDefaultCommand(new PredefinedPrintCommand("Virheellinen komento", io));
    }
}
