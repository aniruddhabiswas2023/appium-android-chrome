Feature: Search criteria

  Scenario Outline: Search using the unibet mobile web search
    Given I am in the unibet uk blog page
    And The search edit box is visible
    Then i should be able to search with a "<text>"
    Examples:
      | text |
      | fifa |
      | hockey|

