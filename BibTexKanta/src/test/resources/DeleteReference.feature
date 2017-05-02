Feature: As a user I want to be able to delete reference

  Scenario: deleting reference deletes only that reference
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    And I've added an article with title "Artikkeli esimerkki", author "Tiina Toimittaja", publishing year "2001", journal name "Esimerkkaajalehti", volume "3" and number "21"
    When I delete reference number "1"
    Then references printed have line "Title: Artikkeli esimerkki" and not "Title: Esimerkkikirjallisuuden alkeet"