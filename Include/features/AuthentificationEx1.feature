#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Authentication

  @authentication @tnr
  Scenario Outline: Authentication with correct email address and password
    Given the user launches the application
    When He enters his <email> and <password>

    #When he accepts the conditions
    #When he accepts the use of his data
    # Then he is logged into his account
    Examples: 
      | email                                             | password  |
      | user.test.cabinet+myunisoft_candidat@myunisoft.fr | G00D_LuCk |
