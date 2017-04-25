Feature: As an user I want to list all references I've added

  Scenario: program lists one valid reference given to the system
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When I list references
    Then information of 1 books is printed

  Scenario: program can list many valid references given to it
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    And I've added an article with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000", journal name "Esimerkkaajalehti", volume "3" and number "21"
    When I list references
    Then information of 1 books is printed
    And information of 1 articles is printed

  Scenario: program doesn't list invalid reference
    Given BibTextKanta is set up
    And I've tried to add a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" but without publisher name
    When I list references
    Then information of 0 books is printed
