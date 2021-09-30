Feature: Cucumber testing step definitions

  Scenario Outline: My Java mentor wants me to test with Cucumber, so: below I test each method of my Controller with a brief history.
    Given That, well, I can list all offer
    And I can create a newly offer too
    Then I can search for a another offer
    Then If I'm tired of an offer, I can delete it as well. But be aware! If offer does not exist, an exception will be thrown

    Examples:
      | active | desconto | descricao   | fim          | inicio       | products                                      |
      | true   | 10.00    | description | "2031-12-31" | "2021-12-31" | "products": "name": "teste", "type": "testes" |

