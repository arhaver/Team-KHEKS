
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
        beginnings.addAll(Arrays.asList("Title:", "Author(s):", "Year:", "Publisher:", "Tex Id: "));

        int foundBooks = find_amount_of_given_start_combinations(outputs, beginnings);

        assertEquals(books, foundBooks);
    }

    @Then("^information of (\\d+) articles is printed$")
    public void information_of_articles_is_printed(int articles) throws Throwable {
        run();

        List<String> outputs = io.getPrintedLines();
        List<String> beginnings = new ArrayList<>();
        beginnings.addAll(Arrays.asList("Title:", "Author(s):", "Year:", "Volume:", "Journal:", "Number:", "Tex Id: "));

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
    
    @When("^User chooses to edit this book")
    public void user_chooses_to_edit_this_book(){
        inputs.add("6");
        inputs.add("1");
        inputs.add("e");
    }
    
    @Then("^title is changed$")
    public void title_is_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Title: Alkeiden esimerkit"));
    }

    @Then("^title is not changed$")
    public void title_is_not_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Title: Esimerkkikirjallisuuden alkeet"));
    }

    @Then("^author is changed$")
    public void author_is_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Author(s): Harri Hakkeri"));
    }

    @Then("^author is not changed$")
    public void author_is_not_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Author(s): Kalle Kirjailija"));
    }

    @Then("^year is changed$")
    public void year_is_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Year: 1002"));
    }

    @Then("^year is not changed$")
    public void year_is_not_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Year: 1001"));
    }

    @Then("^publisher name is changed$")
    public void publisher_name_is_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Publisher: Kustantajat Oy"));
    }

    @Then("^publisher name is not changed$")
    public void publisher_name_is_not_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Publisher: Julkaisijat OY"));
    }

    @Then("^publisher address is changed$")
    public void publisher_address_is_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Address: Osoite 1 B"));
    }

    @Then("^publisher address is not changed$")
    public void publisher_address_is_not_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertFalse(isOutput("Address: 1b"));
    }

    @Then("^BibTeXId is changed$")
    public void bibtexid_is_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertTrue(isOutput("Tex Id: KK00"));
    }

    @Then("^BibTeXId is not changed$")
    public void bibtexid_is_not_changed() throws Throwable {
        inputs.add("q");
        inputs.add("q");
        inputs.add("4");
        run();
        
        assertFalse(isOutput("Tex Id: KK"));
    }
    
    @When("^User types invalid authors name \"([^\"]*)\"$")
    public void user_types_invalid_authors_name(String authors) throws Throwable {
        user_types_valid_authors_name(authors);
    }

    @When("^User types invalid publisher name \"([^\"]*)\"$")
    public void user_types_invalid_publisher_name(String publisher) throws Throwable {
        user_types_valid_publisher_name(publisher);
    }

    @When("^User types invalid publisher address \"([^\"]*)\"$")
    public void user_types_invalid_publisher_address(String address) throws Throwable {
        user_types_valid_publisher_address(address);
    }

    @When("^User types valid BibTeXId \"([^\"]*)\"$")
    public void user_types_valid_BibTeXId(String bibtex) throws Throwable {
        add_choice_input(6, bibtex);
    }

    @When("^User types invalid BibTeXId \"([^\"]*)\"$")
    public void user_types_invalid_BibTeXId(String bibtex) throws Throwable {
        user_types_valid_BibTeXId(bibtex);
    }

    @When("^I add tag \"([^\"]*)\" to book reference number \"([^\"]*)\"$")
    public void i_add_tag_to_book_reference(String tag, String number) {
        inputs.add("6");
        inputs.add(number);
        inputs.add("e");
        inputs.add("10");
        inputs.add("1");
        inputs.add(tag);
        inputs.add("4");
        inputs.add("7");
        inputs.add("q");
        inputs.add("q");
    }
    
    @When("^I add tag \"([^\"]*)\" to article reference number \"([^\"]*)\"$")
    public void i_add_tag_to_article_reference(String tag, String number) {
        inputs.add("6");
        inputs.add(number);
        inputs.add("e");
        inputs.add("14");
        inputs.add("1");
        inputs.add(tag);
        inputs.add("4");
        inputs.add("7");
        inputs.add("q");
        inputs.add("q");
    }
    
    @When("^I add tag \"([^\"]*)\" to inproceedings reference number \"([^\"]*)\"$")
    public void i_add_tag_to_inproceedings_reference(String tag, String number) {
        inputs.add("6");
        inputs.add(number);
        inputs.add("e");
        inputs.add("12");
        inputs.add("1");
        inputs.add(tag);
        inputs.add("4");
        inputs.add("7");
        inputs.add("q");
        inputs.add("q");
    }
    
    @Then("^Tag \"([^\"]*)\" is added")
    public void tag_is_added(String tag) {
        inputs.add("6");
        inputs.add("1");
        inputs.add("e");
        inputs.add("10");
        inputs.add("3");
        run("4", "7", "q", "q");
        assertTrue(isOutput("\t" + tag));
    }
    
    @When("^I search references by field \"([^\"]*)\"$")
    public void i_search_references_published(String search) throws Throwable {
        inputs.add("6");
        inputs.add("s");
        inputs.add(search);
    }

    @Then("^references printed have line \"([^\"]*)\" and not \"([^\"]*)\"$")
    public void references_printed_are_published_and_not(String validline, String invalidline) throws Throwable {
        run("q");
        assertTrue(io.getPrintedLines().subList(io.getPrintedLines().lastIndexOf("\nListaus alkaa:\n"), io.getPrintedLines().size() - 1).contains(validline));
        assertFalse(io.getPrintedLines().subList(io.getPrintedLines().lastIndexOf("\nListaus alkaa:\n"), io.getPrintedLines().size() - 1).contains(invalidline));
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
