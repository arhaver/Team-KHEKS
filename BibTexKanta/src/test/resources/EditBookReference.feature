Feature: As a user I want to be able to edit book reference

  Scenario: User can edit title with valid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit title
    And User types valid title "Alkeiden esimerkit"
    Then title is changed

  Scenario: User cannot edit title with invalid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit title
    And User types invalid title "Ae"
    Then title is not changed

  Scenario: User can edit author with valid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit author
    And User types valid author "Alkeiden esimerkit"
    Then author is changed

  Scenario: User cannot edit author with invalid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit author
    And User types invalid author "KK"
    Then author is not changed

  Scenario: User can edit year with valid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit year
    And User types valid year "1999"
    Then year is changed

  Scenario: User cannot edit year with invalid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit year
    And User types invalid year "2100"
    Then year is not changed

  Scenario: User can edit publisher name with valid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit publisher name
    And User types valid publisher name "Kustantajat Oy"
    Then publisher name is changed

  Scenario: User cannot edit publisher name with invalid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit publisher name
    And User types invalid publisher name "Oy"
    Then publisher name is not changed

  Scenario: User can edit publisher address with valid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit publisher address
    And User types valid publisher address "Osoite 1 B"
    Then publisher address is changed

  Scenario: User cannot edit publisher address with invalid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit publisher address
    And User types invalid publisher address "1b"
    Then publisher address is not changed

  Scenario: User can edit BibTeXId with valid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit BibTeXId
    And User types valid BibTeXId "KK00"
    Then BibTeXId is changed

  Scenario: User cannot edit BibTeXId with invalid information
    Given BibTextKanta is set up
    And User have added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY"
    Then User chooses to edit this book
    And User chooses to edit BibTeXId
    And User types invalid BibTeXId "KK"
    Then BibTeXId is not changed