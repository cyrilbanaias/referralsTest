# @author : Gaëtan RAGEUL
@admin
Feature: Admin tool

  Scenario: 02 - Create and delete a profil
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the profiles page
    And I create a new profile
    Then the new profile is displayed in the list
    And the new profile is available in the user creation page
    When I open the profiles page
    And I delete this profile
    Then the new profile is not displayed anymore in the list
