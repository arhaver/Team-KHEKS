Feature: As an user I can add information to the book

  Scenario: user can add valid name to the book
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types valid title "Kaiken ABC"
    Then Name is added

  Scenario: user can't too short name
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types invalid title "Ka"
    Then Name isn't added

  Scenario: user can add valid year
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types valid publishing year "1990"
    Then Year is added

  Scenario: user can't add future year
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types invalid publishing year "2100"
    Then Year isn't added

  Scenario: user can't add year too much in the past
    Given BibTextKanta is set up
    When User chooses to add book reference
    And User types invalid publishing year "-100"
    Then Year isn't added
