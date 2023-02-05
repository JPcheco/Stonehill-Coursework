#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 5: Scores

# Problem Set 5: Part 3 (2 Points). 

# In this assignment you are to write code that will analyze scores.

# Generate a list of scores using the following code

import random
from statistics import mean

scores = []

for i in range(28):
    scores.append(random.randrange(50, 100))

print(scores)
print()

length = len(scores)
avg = int(mean(scores))
A = B = C = D = F = 0
above = below = at = 0

for i in range(length):
    temp = scores[i]

    if temp >= 90 and temp <= 100:
        A = A + 1
    elif temp >= 80 and temp < 90:
        B = B +1
    elif temp >= 70 and temp < 80:
        C = C + 1
    elif temp >= 60 and temp < 70:
        D = D + 1
    else:
        F = F + 1

    if temp > avg:
        above = above + 1
    elif temp < avg:
        below = below + 1
    elif temp == avg: 
        at = at + 1

grades = [A, B, C, D, F]
print("Number of grades in range of A:", grades[0])
print("Number of grades in range of B:", grades[1])
print("Number of grades in range of C:", grades[2])
print("Number of grades in range of D:", grades[3])
print("Number of grades in range of F:", grades[4])

print()
print("Average Score is:", avg)

print()
print("Number of scores above the average:", above)
print("Number of scores below the average:", below)
print("Number of scores at the average:", at)


# Your code is to 
# 1. calculate and print the number of grades entered in the ranges:
# Grade of A:
# >= 90 and <= 100
# >= 80 and < 90
# >= 70 and < 80
# >= 60 and < 70
# >= 0 and < 60
# 2. calculate and print the average score
# 3. calculate and print the number of score above, below and at the average
