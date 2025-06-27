
@tag
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file
  
  Background: 
  Given User landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When User add product <productName> to the Cart
    And Checkout <productName> and submit the Order
    Then "Thankyou for the order." message is displayed on Confirmation Page

    Examples: 
      | name                | password             | productName  |
      | ekanyes15@gmail.com | ekanyes15AY#2025     | ZARA COAT 3  |
    
