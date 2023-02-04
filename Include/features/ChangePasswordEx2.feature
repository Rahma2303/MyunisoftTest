@tag
Feature: Change password

  @ChangePassword @tnr
  Scenario Outline: Change password using api
    Given the user launches the application
    When he change <oldPassword> with <newPassword>
    Then he logs in with <email> and <newPassword>
    And he replaces his <newPassword> with his <oldPassword>
    Then the change is verified

    Examples: 
      | email                                             | oldPassword | newPassword |
      | user.test.cabinet+myunisoft_candidat@myunisoft.fr | G00D_LuCk   | G00D_LuCk2  |
