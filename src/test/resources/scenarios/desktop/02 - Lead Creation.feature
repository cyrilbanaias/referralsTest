# @author : Gaëtan RAGEUL
@leadCreation
Feature: Lead Creation

  Scenario: Create a lead as agent with personnal offer for professionnal (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select a personnal offer for the lead for a professionnal client
    And I search for an existing client
    And I check the client informations
    And I check the contact informations
    And I fill the client need with:
    | d+20 | 14:28 | 5 | 1500 |
    Then I send the lead by email
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page
@test
  Scenario: Create a lead as agent with existing offer for professionnal (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select an existing offer for the lead for a professionnal client
    And I search for an existing client
    And I check the client informations
    And I check the contact informations
    Then I send the lead by email
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page