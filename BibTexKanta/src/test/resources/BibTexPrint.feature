Feature: As an user I want to list all references I've added to BibTex file

  Scenario: program lists one valid book reference given to the system
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When I print references to BibTex file "CucumberTesti1"
    Then information of the book is printed to the file "CucumberTesti1"
