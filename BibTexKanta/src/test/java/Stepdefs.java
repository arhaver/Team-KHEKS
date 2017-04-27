
import bibtex.Translator;
import commandlineUI.menu.MainMenu;
import io.StubIO;
import java.util.ArrayList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import database.DAO;
import database.InMemoryDAO;
import io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import reference.ArticleRef;
import reference.BookRef;
import reference.InproceedingsRef;
import service.BibTexIdService;
import service.DaoService;

public class Stepdefs {

    private List<String> inputs;
    private String latestInput;

    private StubIO io;
    private DAO<BookRef> bdao;
    private DAO<ArticleRef> adao;
    private DAO<InproceedingsRef> idao;
    private FileWriter writer;
    private Translator trans;
    private MainMenu menu;
    private Path testfile;

    @Given("^BibTextKanta is set up$")
    public void bibtextkanta_is_set_up() throws Throwable {
        inputs = new ArrayList<>();

        bdao = new InMemoryDAO<>();
        adao = new InMemoryDAO<>();
        idao = new InMemoryDAO<>();
        writer = new FileWriter();
        trans = new Translator();

        io = new StubIO(inputs);
        menu = new MainMenu(adao, bdao, idao, io, writer, trans, new BibTexIdService());
    }

    @When("^User chooses to add book reference$")
    public void user_chooses_to_add_book_reference() throws Throwable {
        inputs.add("1");
    }
    
    @When("^User chooses to add inproceedings reference$")
    public void user_chooses_to_add_inproceedings_reference() throws Throwable {
        inputs.add("3");
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
    
    @When("^User types valid booktitle \"([^\"]*)\"$")
    public void user_types_valid_booktitle(String booktitle) throws Throwable {
        add_choice_input(7, booktitle);
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
        assertTrue(!isOutput("Lisäys '" + latestInput + "' virheellinen\n"));
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

        assertTrue(isOutput("Lisäys '" + latestInput + "' virheellinen\n"));
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
    
    @When("^User types valid inproceeding pages \"([^\"]*)\"$")
    public void user_types_valid_inprociiding_pages(String pages) throws Throwable {
        add_choice_input(8, pages);
    }

    @When("^User confirms the article information$")
    public void user_confirms_the_article_information() throws Throwable {
        inputs.add("11");
    }
    
    @When("^User confirms the inproceedings information$")
    public void user_confirms_the_inproceedings_information() throws Throwable {
        inputs.add("9");
    }

    @Then("^article will be added$")
    public void article_will_be_added() throws Throwable {
        run();
        assertTrue(isOutput("Viite lisätty onnistuneesti\n"));
    }
    
    @Then("^inproceedings will be added$")
    public void inproceedings_will_be_added() throws Throwable {
        run();
        assertTrue(isOutput("Viite lisätty onnistuneesti\n"));
    }

    @Then("^article won't be added$")
    public void article_won_t_be_added() throws Throwable {
        run("13");
        assertTrue(isOutput("Tallennus epäonnistui\n"));
    }
    
    @Then("^inproceedings won't be added$")
    public void inproceedings_won_t_be_added() throws Throwable {
        run("11");
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
        inputs.add("4");
    }

    @Then("^information of (\\d+) books is printed$")
    public void information_of_books_is_printed(int books) throws Throwable {
        run();

        List<String> outputs = io.getPrintedLines();
        List<String> beginnings = new ArrayList<>();
        beginnings.addAll(Arrays.asList("Title:", "Author(s):", "Year:", "Publisher:", "Address:", "Tex Id: "));

        int foundBooks = find_amount_of_given_start_combinations(outputs, beginnings);

        assertEquals(books, foundBooks);
    }

    @Then("^information of (\\d+) articles is printed$")
    public void information_of_articles_is_printed(int articles) throws Throwable {
        run();

        List<String> outputs = io.getPrintedLines();
        List<String> beginnings = new ArrayList<>();
        beginnings.addAll(Arrays.asList("Title:", "Author(s):", "Year:", "Publisher:", "Address:", "Volume:", "Journal:", "Number:", "Pages:", "Tex Id: "));

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
    
    @Given("^I've added an inproceedings with title \"([^\"]*)\", author \"([^\"]*)\", publishing year \"([^\"]*)\", booktitle name \"([^\"]*)\"")
    public void i_ve_added_an_inproceedings_with_title_author_publishing_year_booktitle(String title, String author, String year, String booktitle) throws Throwable {
        inputs.add("3");
        add_choice_input(1, title);
        add_choice_input(2, author);
        add_choice_input(3, year);
        add_choice_input(7, booktitle);
        inputs.add("9");
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
    
    @When("^I print references to BibTex file \"([^\"]*)\"")
    public void i_print_references_to_bibtex_file(String filename) throws Throwable {
        inputs.add("5"); 
        add_choice_input(1, filename);
    }
    
    @Then("^information of the book is printed to the file \"([^\"]*)\"")
    public void book_is_printed_to_file(String filename) throws Throwable {
        run();
        testfile = Paths.get(filename);
        assertTrue(Files.exists(testfile));
        Files.delete(testfile);
    }

    /**/
    //Käynnistää menun ja varmistaa että ohjelma sammuu eikä jää ikuiseen looppiin
    private void run(String... quitPromt) {
        for (String quit : quitPromt) {
            inputs.add(quit);
        }
        inputs.add("q");
        menu.execute(null);
    }

    private void add_choice_input(int choice, String input) {
        inputs.add("" + choice);
        inputs.add(input);

        latestInput = input;
    }

    private boolean isOutput(String output) {
        return io.getPrintedLines().contains(output);
    }

    private int find_amount_of_given_start_combinations(List<String> lines, List<String> beginnings) {
        int found = 0;
        int current_beginning = 0;
        boolean almostReady = false;
        for (int i = 0; i < lines.size(); i++) {

            if (almostReady) {
                if (lines.get(i).equals("")) {
                    found++;
                }
                almostReady = false;
            }

            if (lines.get(i).startsWith(beginnings.get(current_beginning))) {
                current_beginning++;
                if (current_beginning == beginnings.size()) {
                    almostReady = true;
                    current_beginning = 0;
                }
            } else {
                current_beginning = 0;
            }
        }

        return found;
    }

}
