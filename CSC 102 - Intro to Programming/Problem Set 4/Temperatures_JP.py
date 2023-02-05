#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 4: Temperatures


# Problem Set 4: Part 3 (2 Points)

# You have a theory that certain days of the week are most likely to be the coolest. You are to write a program that will test that theory.

# Write a program that will generate a list of 52 lists, each of those 52 lists must be of length 7 (i.e. they will each accommodate 7 items).  
# Think of 52 weeks with 7 days in each week. Save a random value from 0 - 50 (inclusive of 0, exclusive of 50) in each of the days of each of the weeks.

# Your code should analyze the list above and print a list of the number of times each day of the week was the highest. 
# If more than one day is the hottest of the week I do not care which of those days is recorded as the hottest.

# Here is an example of the expected output:  note that in the example below I generated and analyzed 5 weeks, you are to generate and analyze 52 weeks:

# My code generated and output the following list of lists:
#  [[21, 5, 45, 5, 48, 18, 37], [32, 1, 36, 32, 47, 18, 26], [13, 22, 30, 9, 44, 44, 10], [26, 1, 47, 35, 24, 11, 49], [2, 13, 41, 19, 44, 20, 3]]

# And output the following list after analysis of the above list:
# [0, 0, 0, 0, 3, 1, 1]

# The actual output from running my code was:
#  [[21, 5, 45, 5, 48, 18, 37], [32, 1, 36, 32, 47, 18, 26], [13, 22, 30, 9, 44, 44, 10], [26, 1, 47, 35, 24, 11, 49], [2, 13, 41, 19, 44, 20, 3]]
# [0, 0, 0, 0, 3, 1, 1]

# Please have your output in the form above.

import random

weeks = []

for i in range(52):
    days = []
    for j in range(7):
        days.append(random.randint(0, 50))
    weeks.append(days)

hottestDays = [0] * 7 
temp = []
index = pos = 0

for x in range(len(weeks)):
    for y in range(len(days)):
        days = weeks[x]
        if(days[y] > index):
            index = days[y]
            pos = y
    hottestDays[pos] += 1
    index = pos = 0

print(weeks)
print(hottestDays)