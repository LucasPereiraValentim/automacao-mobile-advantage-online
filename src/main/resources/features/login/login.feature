@login
Feature: login

  Background:
    Given clico menu lateral

  @ASO_0001
  Scenario Outline: Realizar login com sucesso
    When digito <usuario> em USER NAME
    And digito <senha> em PASSWORD
    And clico no botao de login
    Then verifico o nome do <usuario>
    Examples:
      |     usuario     |      senha        |
      |    lucas.tester |    Tester123      |
  