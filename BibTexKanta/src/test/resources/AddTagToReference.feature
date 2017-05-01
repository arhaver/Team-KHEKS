Feature: As a user I want able to add tag to references

  Scenario: adding tag to book
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When I add tag "testitag1" to book reference number "1"
    Then Tag "testitag1" is added to book

  Scenario: adding tag to article
    Given BibTextKanta is set up
    And I've added an article with title "Artikkeli esimerkki", author "Tiina Toimittaja", publishing year "2001", journal name "Esimerkkaajalehti", volume "3" and number "21"
    When I add tag "testitag1" to article reference number "1"
    Then Tag "testitag1" is added to article

  Scenario: adding tag to inproceedings
    Given BibTextKanta is set up
    And I've added an inproceedings with title "Konferenssijulkaisu esimerkki", author "Tiina Toimittaja", publishing year "2000", booktitle name "Esimerkkaajakirja"
    When I add tag "testitag1" to inproceedings reference number "1"
    Then Tag "testitag1" is added to inproceedings