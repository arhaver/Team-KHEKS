#Feature: As a user I want to search references by tag
#
#  Scenario: User can find book with unique tag
#    Given BibTextKanta is set up
#    And I've added a book with title "Esimerkkikirjallisuuden alkeet", author "Kalle Kirjailija", publishing year "2000" and publisher name "Julkaisijat OY" and tag "unique"
#    Then Search with "t:unique" returns correct book
