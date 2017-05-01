Feature: As an user I want to be able to search reference with printable info

  Scenario: search only prints references with correct value
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    And I've added an article with title "Artikkeli esimerkki", author "Tiina Toimittaja", publishing year "2001", journal name "Esimerkkaajalehti", volume "3" and number "21"
    And I've added an inproceedings with title "Konferenssijulkaisu esimerkki", author "Tiina Toimittaja", publishing year "2000", booktitle name "Esimerkkaajakirja"
    When I search references published "2001"
    Then references printed are published "2001" and not "2000"