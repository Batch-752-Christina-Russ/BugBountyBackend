Feature: I want to see the leaderboard of users

   Scenario: Leaderboard
   	Given <user> or <admin> is logged in
   	Then <leaderboard> of users sorted by <user> total points
   	And <user> is listed at their rank