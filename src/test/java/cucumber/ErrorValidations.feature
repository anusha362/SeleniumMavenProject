
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Negative Test of login to app
    Given User landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
    
    
     Examples: 
      | name                | password             |
      | ekanyes15@gmail.com | ekanyes15AY#20       |
