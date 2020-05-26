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

Feature: Resolutions of Problems
  I want to be able to resolve reported bugs

	Background: I am <admin> and want to resolve a bug  
		Given <admin> is logged in
	  And <admin> is on the bug resolution page
	  
	  Scenario: I want to approve a resolution to a bug
		  Given <bug_report> exists
		  And <bug_report> has a submitted resolution
		  When <admin> selects resolve
		  And <admin> selects <user> for user who resolved
		  Then  <bug_report> status is resolved
		  And <user> receives points
	  
  