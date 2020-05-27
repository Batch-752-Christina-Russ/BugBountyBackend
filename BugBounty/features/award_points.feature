Feature: Awarding Points to Users

	Scenario: I want a user to recieve points
		Given <bug_report> is resolved
		And <user> is the one who resolved this <bug_report>
		Then <user> receives points