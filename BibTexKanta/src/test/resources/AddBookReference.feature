Feature: As a user I want to be able to add new book reference

  Scenario: user can add book with valid information
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types valid title "Kaiken ABC"
    And User types valid authors name "Matti Meikäläinen"
    And User types valid publishing year "1995"
    And User types valid publisher name "Julkaisijat OY"
    And User types valid publisher address "Julkaisijakatu 666"
    And User confirms the information
    Then book will be added

  Scenario: user can add book without publisher address
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types valid title "Kaiken ABC"
    And User types valid authors name "Matti Meikäläinen"
    And User types valid publishing year "1995"
    And User types valid publisher name "Julkaisijat OY"
    And User confirms the information
    Then book will be added

  Scenario: user cannot add book without needed information
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User confirms the information
    Then book won't be added
