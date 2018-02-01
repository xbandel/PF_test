Feature: Agent search
Description: User is searching for Agents at Find Agents tab

Scenario: User is searching for Agents and comparing the number of Agents that are matching the search parameters

Given user is on Home Page
When user click Find Agent button
When user click Languages button
When user select language English
When user select language Arabic
When user select language Hindi
When user click Find button
Then user store number of Agents first search
When user click nationality
When user select Nationality
Then user store number of Agents second search
Then assert number of agents in first search is bigger then in second
