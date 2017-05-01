Feature: As a user I want to search references by tag

  Scenario: search only prints references with correct tag
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    And I've added an article with title "Artikkeli esimerkki", author "Tiina Toimittaja", publishing year "2001", journal name "Esimerkkaajalehti", volume "3" and number "21"
    And I've added an inproceedings with title "Konferenssijulkaisu esimerkki", author "Tiina Toimittaja", publishing year "2000", booktitle name "Esimerkkaajakirja"
    When I add tag "testitag1" to article reference number "1"
    And I add tag "testitag2" to book reference number "2"
    And I add tag "testitag3" to inproceedings reference number "3"
    And I search references by field "t:testitag3"
    Then references printed have line "Title: Artikkeli esimerkki" and not "Title: Esimerkkikirjallisuuden alkeet"
