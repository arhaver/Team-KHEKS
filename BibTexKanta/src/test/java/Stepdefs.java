import commandlineUI.MainMenu;
import database.Database;
import io.IO;
import io.StubIO;
import java.util.ArrayList;
import java.util.List;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.ConsoleIO;
import static org.junit.Assert.*;

public class Stepdefs {
    
  
@Given("^BibTextKanta is running$")
public void bibtextkanta_is_running() throws Throwable {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("1");
        lines.add("1");
        lines.add("Kirja1");
        lines.add("2");
        lines.add("Kirjailija Yksi");
        lines.add("3");
        lines.add("1970");
        lines.add("4");
        lines.add("Otava");
        lines.add("7");
        StubIO io = new StubIO(lines);
        io.setTestString("Viite lisätty onnistuneesti\n");
        Database db = new Database("SD");
        MainMenu mm = new MainMenu(db, io);
        mm.openMainMenu();    
}

@When("^User adds valid book reference$")
public void user_adds_valid_book_reference() throws Throwable {
    
}

@Then("^book will be added$")
public void book_will_be_added() throws Throwable {
    
}

  
}
