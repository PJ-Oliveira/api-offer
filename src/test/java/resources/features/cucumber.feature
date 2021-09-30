Feature: Cucumber testing step definitions
 Scenario Outline:  My mentor wants to create a new offer.
   Given I can list all offer
   And I am sending a offer to be created
   Then I should be able to see my newly offer

   Examples:
   | active | desconto | descricao | fim          | inicio       | products                                   |
   | true   | 10.00    | description | "2031-12-31" | "2021-12-31" | "products": "name": "teste", "type": "testes"|
