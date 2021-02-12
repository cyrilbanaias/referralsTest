# @author : Georg FISCHER
@user
Feature: 01 User
  @test
  Scenario: 01 - Create and delete a user
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the user page
    And I create a new user

#    Then the new profile is displayed in the list
#    And the new profile is available in the user creation page
#    When I open the profiles page
#    And I delete this profile
#    Then the new profile is not displayed anymore in the list
