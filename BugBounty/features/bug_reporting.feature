#Author: joshwilding2011@gmail.com
#Keywords Summary :
#Feature: Bug reporting
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


Feature: General Reporting
  I want to report bugs

Background: Given <user> is signed in


  Scenario: I want to submit a Bug Report
	  Given the user is in the Bug Report Page
	  When <user> fills out the entire form
	  And <user> submits the form
	  Then the application saves the report
 

  
 
 
