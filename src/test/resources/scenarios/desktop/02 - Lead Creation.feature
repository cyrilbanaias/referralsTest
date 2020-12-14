# @author : Gaëtan RAGEUL
@leadCreation
Feature: Lead Creation

  Scenario Outline: Create a lead as agent with personnal offer for professionnal (existing client)
    Given I am on "Referrals"
    When I login with an account:
      | <account_type> |<settings> |
    When I open the lead creation page
    And I choose to create the lead as myself
    And I select a personnal offer for the lead for a professionnal client
    And I search for the existing client "<SIREN>"
    And I check the client informations
    And I check the contact informations
    #And I fill the client need with:
    #Then I print and send the lead

    Examples:
      | account_type | settings             | SIREN     |
      | FORTIS       | create_lead_as_agent | 19640721  |
      | BGZ          | create_lead_as_agent | 19640721  |
      | GENERALI     | create_lead_as_agent | 824132799 |
      | BGL          | create_lead_as_agent | 19640721  |
      | BNPP         | create_lead_as_agent | 19640721  |