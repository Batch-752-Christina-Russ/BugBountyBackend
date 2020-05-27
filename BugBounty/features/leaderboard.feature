Feature: Leaderboard

   Scenario: I want to see the leaderboard of users
   	Given <user> or <admin> is logged in
   	Then <leaderboard> of users sorted by <user> total points
   	And <user> is listed at their rank