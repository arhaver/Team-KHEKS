import commandlineUI.MainMenu;
import cucumber.api.PendingException;
import io.StubIO;
import java.util.ArrayList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.DAO;
import database.InMemoryDAO;
import java.util.Arrays;
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
        adao = new InMemoryDAO<>();
        
        io = new StubIO(inputs);
        menu = new MainMenu(adao, bdao, io, null, null);
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
    
    @When("^User chooses to add article reference$")
    public void user_chooses_to_add_article_reference() throws Throwable {
        inputs.add("2");
    }
    
    @When("^User types valid volume \"([^\"]*)\"$")
    public void user_types_valid_volume(String volume) throws Throwable {
        add_choice_input(7, volume);
    }
    
    @When("^User types valid number \"([^\"]*)\"$")
    public void user_types_valid_number(String number) throws Throwable {
        add_choice_input(8, number);
    }
    
    @When("^User types valid journal name \"([^\"]*)\"$")
    public void user_types_valid_journal_name(String journalName) throws Throwable {
        add_choice_input(9, journalName);
    }
    
    @When("^User types valid pages \"([^\"]*)\"$")
    public void user_types_valid_pages(String pages) throws Throwable {
        add_choice_input(10, pages);
    }
    
    @When("^User confirms the article information$")
    public void user_confirms_the_article_information() throws Throwable {
        inputs.add("11");
    }
    
    @Then("^article will be added$")
    public void article_will_be_added() throws Throwable {
        run();
        assertTrue(isOutput("Viite lisätty onnistuneesti\n"));
    }
    
    @Then("^article won't be added$")
    public void article_won_t_be_added() throws Throwable {
        run("13");
        assertTrue(isOutput("Tallennus epäonnistui\n"));
    }
    
    @Given("^I've added a book with title \"([^\"]*)\", author \"([^\"]*)\", publishing year \"([^\"]*)\" and publisher name \"([^\"]*)\"$")
    public void i_ve_added_a_book_with_title_author_publishing_year_and_publisher_name(String title, String author, String year, String publisher_name) throws Throwable {
        inputs.add("1");
        add_choice_input(1, title);
        add_choice_input(2, author);
        add_choice_input(3, year);
        add_choice_input(4, publisher_name);
        inputs.add("7");
    }

    @When("^I list references$")
    public void i_list_references() throws Throwable {
        inputs.add("3");
    }

    @Then("^information of (\\d+) books is printed$")
    public void information_of_books_is_printed(int books) throws Throwable {
        run();
        
        List<String> outputs = io.getPrintedLines();
        List<String> beginnings = new ArrayList<>();
        beginnings.addAll(Arrays.asList("Title:", "Author(s):", "Year:", "Publisher:",
                "Address:"));
        
        int foundBooks = find_amount_of_given_start_combinations(outputs, beginnings);
        
        assertEquals(books, foundBooks);
    }
    
    @Then("^information of (\\d+) articles is printed$")
    public void information_of_articles_is_printed(int articles) throws Throwable {
        run();
        
        List<String> outputs = io.getPrintedLines();
        List<String> beginnings = new ArrayList<>();
        beginnings.addAll(Arrays.asList("Title:", "Author(s):", "Year:", "Publisher:",
                "Address:", "Volume:", "Journal:", "Number:", "Pages:"));
        
        int foundArticles = find_amount_of_given_start_combinations(outputs, beginnings);
        
        assertEquals(articles, foundArticles);
    }


    @Given("^I've added an article with title \"([^\"]*)\", author \"([^\"]*)\", publishing year \"([^\"]*)\", journal name \"([^\"]*)\", volume \"([^\"]*)\" and number \"([^\"]*)\"$")
    public void i_ve_added_an_article_with_title_author_publishing_year_journal_name_volume_and_number(String title, String author, String year, String journal, String volume, String number) throws Throwable {
        inputs.add("2");
        add_choice_input(1, title);
        add_choice_input(2, author);
        add_choice_input(3, year);
        add_choice_input(7, number);
        add_choice_input(8, volume);
        add_choice_input(9, journal);
        inputs.add("11");
    }

    @Given("^I've tried to add a book with title \"([^\"]*)\", author \"([^\"]*)\", publishing year \"([^\"]*)\" but without publisher name$")
    public void i_ve_tried_to_add_a_book_with_title_author_publishing_year_but_without_publisher_name(String title, String author, String year) throws Throwable {
        inputs.add("1");
        add_choice_input(1, title);
        add_choice_input(2, author);
        add_choice_input(3, year);
        inputs.add("7");
        inputs.add("9");
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
    
    private int find_amount_of_given_start_combinations(List<String> lines, List<String> beginnings){
        int found = 0;
        int current_beginning = 0;
        boolean almostReady = false;
        for(int i = 0 ; i < lines.size() ; i++){
            
            if(almostReady){
                if(lines.get(i).equals("")){
                    found++;
                }
                almostReady = false;
            }
            
            if(lines.get(i).startsWith(beginnings.get(current_beginning))){
                current_beginning ++;
                if(current_beginning == beginnings.size()){
                    almostReady = true;
                    current_beginning = 0;
                }
            }else{
                current_beginning = 0;
            }
        }
        
        return found;
    }
    
}
