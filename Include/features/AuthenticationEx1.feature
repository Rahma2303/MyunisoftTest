@tag
Feature: Authentication

 @authentication @tnr
  Scenario Outline: Authentication with correct email and password
    Given the user launches the application
    When he enters his <email> and <password>
    When he accepts the conditions
    When he accepts the use of his data
    When he clicks on connect
    Then he is logged into his account

    Examples: 
      | email                                             | password  |
      | user.test.cabinet+myunisoft_candidat@myunisoft.fr | G00D_LuCk |
