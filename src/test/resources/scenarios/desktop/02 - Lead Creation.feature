# @author : Gaëtan RAGEUL
@leadCreation
Feature: Lead Creation

  Scenario: 01 - Create a lead as agent with personnal offer for professionnal (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
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
    And I send the lead to salesforce
    And I can see the lead on the client page

  Scenario: 02 - Create a lead as agent with existing offer for professionnal (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select an existing offer for the lead for a professionnal client
    And I search for an existing professionnal client
    And I check the professionnal client informations
    And I check the contact informations
    Then I send the lead by email
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page

  Scenario: 03 - Create a lead as agent with personnal offer for particular (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select a personnal offer for the lead for a particular client
    And I search for the existing particular client "test@test.com"
    And I check the particular client informations
    And I fill the client need with:
      | d+20 | 14:28 | 5 | 1500 |
    Then I send the lead by email
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page

  Scenario: 04 - Create a lead as agent with existing offer for particular (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select an existing offer for the lead for a particular client
    And I search for the existing particular client "test@test.com"
    And I check the particular client informations
    Then I send the lead by email
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page

  Scenario: 05 - Create a lead as agent with Credit Check
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select an existing offer for the lead for a professionnal client
    And I search for an existing professionnal client
    And I check the professionnal client informations
    And I check the contact informations
    Then I send the lead by email
    And I print the lead
    And I send the lead to salesforce
    And I can see the lead on the client page
    When I open a new tab
    And I am on "Salesforce"
    And I login on Salesforce
    Then I open the lead queue "BEL - SME - Bank Leads Queue"