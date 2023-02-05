#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 6: Election

# Problem Set 6: Part 3 (2 Points). 

# The Electoral College plays a pivotal role in the election of the President of the US.
# The following is a dictionary of the states, the number of electoral votes for each state and the winner of the state.  
# The key is the name of state, the value is a list containing the number of votes for that stare and the projected winner ('U' for Unknown). 

# Write a program that will present to the user the name of each state, gather from the user who you think will win the electoral votes for that state 
# and record the winner in the list ('B' for Biden,  'T' for Trump, ‘U’ for Undecided/Too Close to Call).
# Output the states won by each candidate and undecided (sorted alphabetically), 
# the total electoral votes for each candidate and the winner of the election (270 of the possible 538 votes are needed to win).

# Here is a dictionary of the states and their respective electoral votes, please copy and past this code to your file
from asyncio.windows_events import NULL


ec = {'Alabama': [9,'U'],'Kentucky' : [8,'U'],'North Dakota' : [3,'U'],'Alaska' : [3,'U'],'Louisiana' : [8,'U'],'Ohio' : [18,'U'],'Arizona' : [11,'U'],'Maine' : [4,'U'],
      'Oklahoma' : [7,'U'],'Arkansas' : [6,'U'],'Maryland' : [10,'U'],'Oregon' : [7,'U'],'California' : [55,'U'],'Massachusetts' : [11,'U'],'Pennsylvania' : [20,'U'],
      'Colorado': [9,'U'],'Michigan' : [16,'U'],'Rhode Island' : [4,'U'],'Connecticut' : [7,'U'],'Minnesota' : [10,'U'],'South Carolina' : [9,'U'],'Delaware' : [3,'U'],
      'Mississippi' : [6,'U'],'South Dakota' : [3,'U'],'District of Columbia' : [3,'U'],'Missouri' : [10,'U'],'Tennessee' : [11,'U'],'Florida' : [29,'U'],
      'Montana' : [3,'U'],'Texas' : [38,'U'],'Georgia' : [16,'U'],'Nebraska' : [5,'U'],'Utah' : [6,'U'],'Hawaii' : [4,'U'],'Nevada' : [6,'U'],'Vermont' : [3,'U'],
      'Idaho' : [4,'U'],'New Hampshire' : [4,'U'],'Virginia' : [13,'U'],'Illinois' : [20,'U'],'New Jersey' : [14,'U'],'Washington' : [12,'U'],'Indiana' : [11,'U'],
      'New Mexico' : [5,'U'],'West Virginia' : [5,'U'],'Iowa' : [6,'U'],'New York' : [29,'U'],'Wisconsin' : [10,'U'],'Kansas' : [6,'U'],'North Carolina' : [15,'U'],
      'Wyoming' : [3,'U'],}

prediction = ""

for states in ec:
    print("Please enter B for Biden or T for Trump")
    prediction = input(f'Who do you predict will win the state of {states}: ')
    ec[states].append(prediction)
    ec[states].pop(1)

Biden_votes = Trump_votes = Undecided_votes = 0
Winner = ""
values = 0

for states in ec.values():

        values = states[0]
        
        if "B" in states:
            Biden_votes = values + Biden_votes
        elif "T" in states:
            Trump_votes = values + Trump_votes
        elif "U" in states:
            Undecided_votes = values + Undecided_votes

        values = 0


if Biden_votes > Trump_votes:
    Winner = "BIDEN"
elif Biden_votes < Trump_votes:
    Winner = "TRUMP"
elif Undecided_votes > Biden_votes or Undecided_votes > Trump_votes:
    Winner = "UNDECIDED"

print("Biden:", Biden_votes)
print("Trump:", Trump_votes)
print("Undecided:", Undecided_votes)
print("Winner:", Winner)    

print()
print()

print("States won by Biden")
for states in ec:
    if "B" in ec[states]:
        print(states)

print()
print("States won by Trump")
for states in ec:
    if "T" in ec[states]:
        print(states)

print()
print("Undecided States")
for states in ec:
    if "U" in ec[states]:
        print(states)



