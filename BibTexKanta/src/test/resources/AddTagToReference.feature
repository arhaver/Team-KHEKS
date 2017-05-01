Feature: As a user I want able to add tag to references

  Scenario: search only prints references with correct tag
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When I add tag "testitag1" to book reference number "1"
    Then Tag "testitag1" is added