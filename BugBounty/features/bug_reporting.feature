Feature: Reporting a Bug
  Scenario: I want to submit a Bug Report
	  Given the user is in the Bug Report Page
	  When user fills out the entire form
	  And user submits the form
	  Then the application saves the report