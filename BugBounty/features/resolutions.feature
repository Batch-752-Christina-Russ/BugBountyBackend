Feature: Bug Report Resolutions
  I want to be able to resolve reported bugs.

	Background: Given I am logged in 
	  
	  Scenario: I want to resolve reported bugs.
		  Given I am on resolution page  
		  When I press resolve
		  Then  bug_report is resolved