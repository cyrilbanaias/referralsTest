# @author : Gaëtan RAGEUL
@leadEdition
Feature: Lead Edition

  Scenario: 01 - Consult a saved lead
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | edit_lead |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select a personnal offer for the lead for a professionnal client
    And I search for an existing professionnal client
    And I check the professionnal client informations
    And I check the contact informations
    And I fill the client need with:
    | d+20 | 14:28 | 5 | 1500 |
    Then I send the lead by email
    And I print the lead
    And I save the lead
    Then I can see the saved lead on the client page

  Scenario: 02 - Edit a saved lead
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | edit_lead |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select a personnal offer for the lead for a professionnal client
    And I search for an existing professionnal client
    And I check the professionnal client informations
    And I check the contact informations
    And I fill the client need with:
      | d+20 | 14:28 | 5 | 1500 |
    Then I send the lead by email
    And I print the lead
    And I save the lead
    Then I can see the lead on the client page
    When I modify the lead
    And I fill the client need with:
      | d+30 | 13:44 | 10 | 3000 |
    And I save the lead
    Then I can see the lead on the client page

  Scenario: 03 - Send a saved lead
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | edit_lead |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select a personnal offer for the lead for a professionnal client
    And I search for an existing professionnal client
    And I check the professionnal client informations
    And I check the contact informations
    And I fill the client need with:
      | d+20 | 14:28 | 5 | 1500 |
    Then I send the lead by email
    And I print the lead
    And I save the lead
    Then I can see the lead on the client page
    When I modify the lead
    And I fill the client need with:
      | d+30 | 13:44 | 10 | 3000 |
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page
