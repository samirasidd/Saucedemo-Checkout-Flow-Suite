Feature: Login Functionality

  @login
  Scenario: Successful login
    Given I am on the login page
    When I login with "standard_user" and "secret_sauce"
    Then I should see the inventory page

  @login
  Scenario: Failed login
    Given I am on the login page
    When I login with "locked_out_user" and "secret_sauce"
    Then I should see error "Epic sadface: Sorry, this user has been locked out."