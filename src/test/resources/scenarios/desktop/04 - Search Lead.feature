# @author : Gaëtan RAGEUL
@searchLead
Feature: Search lead

  Scenario: 01 - Research a lead by mono criteria
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the search lead page
    And I search for an existing company number
    Then all the results are from this company

  Scenario Outline: 06 - Research a lead by multi criteria - Status
    Given I am on "Referrals"
    When I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the search lead page
    And I search for <status> status
    Then all the results have the searched status

    Examples:
    | status |
    | Demande créée |
    | Contrat en attente signature client |
