Feature: As a user I want to be able to add new article reference

  Scenario: user can add article with valid information
    Given BibTextKanta is set up
    When User chooses to add article reference
    And User types valid title "Kaiken ABC"
    And User types valid authors name "Matti Meikäläinen"
    And User types valid publishing year "1995"
    And User types valid journal name "Foo - tieteellistä tietoa ohjelmoinnista"
    And User types valid volume "15"
    And User types valid number "3"
    And User types valid publisher name "Tietojulkaisut"
    And User types valid publisher address "Helsinki, Finland"
    And User types valid pages "84-92"
    And User confirms the article information
    Then article will be added

   Scenario: user can add article with only title, authors, year, journal, volume and number
    Given BibTextKanta is set up
    When User chooses to add article reference
    And User types valid title "Kaiken ABC"
    And User types valid authors name "Matti Meikäläinen"
    And User types valid publishing year "1995"
    And User types valid journal name "Foo - tieteellistä tietoa ohjelmoinnista"
    And User types valid volume "15"
    And User types valid number "3"
    And User confirms the article information
    Then article will be added

   Scenario: user cannot add article without needed information
    Given BibTextKanta is set up
    When User chooses to add article reference
    And User confirms the article information
    Then article won't be added