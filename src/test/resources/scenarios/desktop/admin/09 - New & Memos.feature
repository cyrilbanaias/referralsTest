# @author : Gaëtan RAGEUL
@admin @insert
Feature: 09 - News & Memos

  Scenario: 01 - Create an insert for homepage
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I create a new insertion:
      | Custom | No popup | Yes | Homepage |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then the insert is displayed on the homepage
    #And I disconnect from Referrals
    #When I am on "Admin Tool"
    #When I login with an account:
    #  | ADMIN | admin |
    #And I open the partners configuration page
    #And I edit the partner "BNPPFORTIS"
    #And I open the insertion page
    #And I delete the insertion
    #Then insertion is not displayed anymore in the list
    #And I disconnect from admin tool
    #When I am on "Referrals"
    #And I login with an account:
    #  | FORTIS | create_lead_as_agent |
    #Then the insert is not displayed anymore on the homepage

  Scenario: 02 - Create an insert for news and infos body page
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I activate the news and infos page
    And I create a new insertion:
      | Custom | No popup | Yes | Body |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the news and infos page
    Then the insert is displayed on the news and infos body page

  Scenario: 03 - Create an insert for news and infos column
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I activate the news and infos page
    And I create a new insertion:
      | Custom | No popup | Yes | Column |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the news and infos page
    Then the insert is displayed on the news and infos column

  Scenario: 04 - Create an insert for enter a lead page
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I activate the news and infos page
    And I create a new insertion:
      | Custom | No popup | Yes | Enter a lead |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the lead creation page
    Then the insert is displayed on the lead creation page

  Scenario: 05 - Create an insert for all the page
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I activate the news and infos page
    And I create a new insertion:
      | Custom | No popup | Yes | All pages |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then the insert is displayed on the homepage
    And I open the news and infos page
    Then the insert is displayed on the news and infos body page
    Then the insert is displayed on the news and infos column
    And I open the lead creation page
    Then the insert is displayed on the lead creation page

  Scenario: 06 - Create a popup
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I activate the news and infos page
    And I create a new insertion:
      | Custom | Popup | Yes | Homepage |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then the popup is displayed on the homepage

  Scenario: 07 - Delete an insert for enter a lead page
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I create a new insertion:
      | Custom | No popup | Yes | Enter a lead |
    Then insertion is displayed in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the lead creation page
    Then the insert is displayed on the lead creation page
    And I disconnect from Referrals
    When I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I delete the insertion
    Then insertion is not displayed anymore in the list
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    And I open the lead creation page
    Then the insert is not displayed anymore on the lead creation page

  Scenario: 08 - Sort the insert on the Homepage
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I remove all the existing inserts
    And I create a new insertion:
      | Custom | No popup | Yes | Homepage |
    And I create a new insertion:
      | Custom | No popup | Yes | Homepage |
    Then I save inserts order
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then inserts are in the good order
    And I disconnect from Referrals
    When I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I change the order of inserts
    Then I save inserts order
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then inserts are in the good order

  Scenario: 09 - Activate/Deactive News and Infos page
    Given I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I activate the news and infos page
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then news and infos page is available
    And I disconnect from Referrals
    When I am on "Admin Tool"
    When I login with an account:
      | ADMIN | admin |
    And I open the partners configuration page
    And I edit the partner "BNPPFORTIS"
    And I open the insertion page
    And I deactivate the news and infos page
    And I disconnect from admin tool
    When I am on "Referrals"
    And I login with an account:
      | FORTIS | create_lead_as_agent |
    Then news and infos page is not available
