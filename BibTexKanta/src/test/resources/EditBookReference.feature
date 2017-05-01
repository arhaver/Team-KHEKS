Feature: As a user I want to be able to edit book reference

  Scenario: User can edit title with valid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types valid title "Alkeiden esimerkit"
    And User confirms the information
    Then title is changed

  Scenario: User cannot edit title with invalid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types invalid title "Ae"
    And User confirms the information
    Then title is not changed

  Scenario: User can edit author with valid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types valid authors name "Harri Hakkeri"
    And User confirms the information
    Then author is changed

  Scenario: User cannot edit author with invalid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types invalid authors name "KK"
    And User confirms the information
    Then author is not changed

  Scenario: User can edit year with valid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "1001" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types valid publishing year "1002"
    And User confirms the information
    Then year is changed

  Scenario: User cannot edit year with invalid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "1001" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types invalid publishing year "2100"
    And User confirms the information
    Then year is not changed

  Scenario: User can edit publisher name with valid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types valid publisher name "Kustantajat Oy"
    And User confirms the information
    Then publisher name is changed

  Scenario: User cannot edit publisher name with invalid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types invalid publisher name "Oy"
    And User confirms the information
    Then publisher name is not changed

  Scenario: User can edit publisher address with valid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types valid publisher address "Osoite 1 B"
    And User confirms the information
    Then publisher address is changed

  Scenario: User cannot edit publisher address with invalid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types invalid publisher address "1b"
    And User confirms the information
    Then publisher address is not changed

  Scenario: User can edit BibTeXId with valid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types valid BibTeXId "KK00"
    And User confirms the information
    Then BibTeXId is changed

  Scenario: User cannot edit BibTeXId with invalid information
    Given BibTextKanta is set up
    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    When User chooses to edit this book
    And User types invalid BibTeXId "KK"
    And User confirms the information
    Then BibTeXId is not changed