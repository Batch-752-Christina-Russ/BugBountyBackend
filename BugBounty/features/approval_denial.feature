Feature: Approve and Deny Bug Requests

 	Background: Given "admin" is logged in
	 	Given I navigate to the login page
		When I submit username and password
		Then I should be logged in
	
 	Scenario: I want to approve a bug
	  Given admin is logged in
	  And bug report exists
	  When admin selects "approve"
	  Then bug report status is "open"
  
 	Scenario: I want to deny a bug
	  Given admin is logged in
	  And bug report exists
	  When admin selects "deny"
	  Then bug report status is "deleted"