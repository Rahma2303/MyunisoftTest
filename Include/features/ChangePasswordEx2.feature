@tag
Feature: Change password

  @ChangePassword @tnr
  Scenario Outline: Change password using api
    Given the user launches the application
    When changing <oldPassword> with <password> using api
    Then he enters his <email> and <password>
    And he accepts the conditions
    And he accepts the use of his data
    And he clicks on connect
    And he replaces his <password> with his <oldPassword>
    Then Password reset successfully

    Examples: 
      | email                                             | oldPassword | password   |
      | user.test.cabinet+myunisoft_candidat@myunisoft.fr | G00D_LuCk   | G00D_LuCk2 |
