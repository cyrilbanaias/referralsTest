# @author : Gaëtan RAGEUL
  @referrals
Feature: Referrals

  Scenario Outline: POC Referrals
    Given I am on "Referrals"
    When I login with an account:
      | <account_type> |<settings> |
    When I open the lead creation page
    Then I can fill the lead form with informations

    @BNPP
    Examples:
      | account_type | settings    |
      | BNPP         | create_lead |

    @FORTIS
    Examples:
      | account_type | settings    |
      | FORTIS       | create_lead |