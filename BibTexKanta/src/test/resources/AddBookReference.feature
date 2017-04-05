Feature: As a user I want to be able to add new book reference

  Scenario: add book reference
    Given BibTextKanta is running
    When User adds valid book reference
    Then book will be added

