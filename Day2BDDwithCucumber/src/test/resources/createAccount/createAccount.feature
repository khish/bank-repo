#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Create New Account
				 Create New Account for the valid customer details

	Scenario: For valid customer create new account
    Given customer details
    When Valid customer
    And valid open balance
    Then create new account 
    
	Scenario: For Invalid customer
						For invalid customer details throw error message
		Given Customer details
		When Invalid Customer
		Then throw 'Invalid Customer' error message
		
	Scenario: For Invalid Opening Balance
		Given customer details and opening balance
		When Invalid opening balance
		Then throw 'Insufficient Balance' error message
		
	Scenario: Find account details
						Find account details for the given account number
		Given Account number
		When Valid Account number
		Then find account details

	Scenario: Withdraw Amount from account
						Find account details and withdraw amount
		Given Account number 1001 and amount 1000
		When Find account and do withdraw
		Then update the account details
		
	Scenario: Deposit Amount from account
						Find account details and Deposit amount
		Given Account number 1001 and amount 1000
		When Find account and do deposit
		Then update the account details