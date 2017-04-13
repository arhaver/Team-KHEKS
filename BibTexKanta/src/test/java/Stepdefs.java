import commandlineUI.MainMenu;
import cucumber.api.PendingException;
import io.StubIO;
import java.util.ArrayList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.DAO;
import database.InMemoryDAO;
import java.util.List;
import static org.junit.Assert.*;
import reference.AbstractReference;
import reference.ArticleRef;
import reference.BookRef;

public class Stepdefs {
    private List<String> inputs;
    private String latestInput;
    
    private StubIO io;
    private DAO<BookRef> bdao;
    private DAO<ArticleRef> adao;
    private MainMenu menu;
  
    @Given("^BibTextKanta is set up$")
    public void bibtextkanta_is_set_up() throws Throwable {
        inputs = new ArrayList<>();
        
        bdao = new InMemoryDAO<>();
        io = new StubIO(inputs);
        menu = new MainMenu(adao, bdao, io);
    }

    @When("^User chooses to add book reference$")
    public void user_chooses_to_add_book_reference() throws Throwable {
        inputs.add("1");
    }

    @When("^User types valid title \"([^\"]*)\"$")
    public void user_types_valid_title(String title) throws Throwable {
        add_choice_input(1, title);
    }

    @When("^User types valid authors name \"([^\"]*)\"$")
    public void user_types_valid_authors_name(String authors) throws Throwable {
        add_choice_input(2, authors);
    }

    @When("^User types valid publisher name \"([^\"]*)\"$")
    public void user_types_valid_publisher_name(String publisherName) throws Throwable {
        add_choice_input(4, publisherName);
    }

    @When("^User types valid publisher address \"([^\"]*)\"$")
    public void user_types_valid_publisher_address(String publisherAddress) throws Throwable {
        add_choice_input(5, publisherAddress);
    }

    @When("^User confirms the information$")
    public void user_confirms_the_information() throws Throwable {
        inputs.add("7");
    }

    @Then("^book will be added$")
    public void book_will_be_added() throws Throwable {
        run();
        assertTrue(isOutput("Viite lisätty onnistuneesti\n"));
    }

    @Then("^book won't be added$")
    public void book_won_t_be_added() throws Throwable {
        run("9");
        assertTrue(isOutput("Tallennus epäonnistui\n"));
    }
    
    @Then("^Name is added$")
    public void name_is_added() throws Throwable {
        run("9");
        assertTrue(!isOutput("Lisäys '"+ latestInput +"' virheellinen\n"));
    }

    @When("^User types invalid title \"([^\"]*)\"$")
    public void user_types_invalid_title(String title) throws Throwable {
        user_types_valid_title(title);
    }
    
    @When("^User types valid publishing year \"([^\"]*)\"$")
    public void user_types_valid_publishing_year(String year) throws Throwable {
        add_choice_input(3, year);
    }

    @When("^User types invalid publishing year \"([^\"]*)\"$")
    public void user_types_invalid_publishing_year(String year) throws Throwable {
        user_types_valid_publishing_year(year);
    }



    @Then("^Name isn't added$")
    public void name_isn_t_added() throws Throwable {
        run("9");
        
        
        for(String line : io.getPrintedLines()){
            System.out.println(line);
        }
        
        assertTrue(isOutput("Lisäys '"+ latestInput +"' virheellinen\n"));
    }

    @Then("^Year is added$")
    public void year_is_added() throws Throwable {
        name_is_added();
    }

    @Then("^Year isn't added$")
    public void year_isn_t_added() throws Throwable {
        name_isn_t_added();
    }

    
    /**/
    
    //Käynnistää menun ja varmistaa että ohjelma sammuu eikä jää ikuiseen looppiin
    private void run(String... quitPromt){
        for(String quit : quitPromt){
            inputs.add(quit);
        }
        inputs.add("q");
        menu.openMainMenu();
    }
    
    private void add_choice_input(int choice, String input){
        inputs.add(""+choice);
        inputs.add(input);
        
        latestInput = input;
    }
    
    private boolean isOutput(String output){
        return io.getPrintedLines().contains(output);
    }
    
}
