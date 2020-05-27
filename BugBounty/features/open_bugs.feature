Feature: Open Bugs on Homepage

Background:
	Given you are in the login page
  
  Scenario: I want to view open bugs as user
    Given <user> is logged in
    When <user> attempts to click the first more button
    Then <user> can see a more descriptive bug report
    
   Scenario: I want to view open bugs as admin
		Given <admin> is logged in
    When <admin> attempts to click the first more button
    Then <admin> can see a more descriptive bug report