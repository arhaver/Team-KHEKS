Feature: As a user I want to be able to add new inproceedings reference

  Scenario: user can add inproceedings with valid information
    Given BibTextKanta is set up
    When User chooses to add inproceedings reference
    And User types valid title "Kaiken ABC"
    And User types valid authors name "Matti Meikäläinen"
    And User types valid publishing year "1995"
    And User types valid booktitle "Kaikki kaikesta -konferenssi"
    And User types valid publisher name "Tietojulkaisut"
    And User types valid publisher address "Helsinki, Finland"
    And User types valid inproceeding pages "84-92"
    And User confirms the inproceedings information
    Then inproceedings will be added

  Scenario: user can add inproceedings with only title, authors, year and booktitle
    Given BibTextKanta is set up
    When User chooses to add inproceedings reference
    And User types valid title "Kaiken ABC"
    And User types valid authors name "Matti Meikäläinen"
    And User types valid publishing year "1995"
    And User types valid booktitle "Kaikki kaikesta -konferenssi"
    And User confirms the inproceedings information
    Then inproceedings will be added

  Scenario: user cannot add inproceedings without needed information
    Given BibTextKanta is set up
    When User chooses to add inproceedings reference
    And User confirms the inproceedings information
    Then inproceedings won't be added
