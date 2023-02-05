#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 9: Diamonds

# Part 1: Diamonds  (3 points)

# The file diamonds.csv lists 53,940 diamonds by the following attributes:

# [0] id
# [1] caret
# [2] cut
# [3] color
# [4] clarity
# [5] depth
# [6] table
# [7] price
# [8] x
# [9] y
# [10] z

# Diamonds are rated by a number of attributes.  One of those ratings is cut. 
# To determine the possible cut ratings noted in the file you must first find 
# the unique values in the index position 2 (i.e. [2]).

# In this assignment you are to determine and output to the screen the number of diamonds 
# in each cut rating.  Hint: use the set method. Hint: there are 5 ratings.
# You must use a dictionary with key values that are the unique cut ratings. 

import csv
diamonds = []

with open('diamonds.csv') as file_object:
    reader = csv.reader(file_object)
    attributes = next(reader)

    for row in reader:
        diamonds.append(row)
file_object.close()
# print(diamonds)

diamond_cuts = []
for row in diamonds:
    diamond_cuts.append(row[2]) #row[2] is where the cut of the diamonds are located
type_of_cuts = set(diamond_cuts)
# print(type_of_cuts)

count = {}
for diamond_cuts in type_of_cuts:
    count[diamond_cuts] = 0
for row in diamonds:
    count[row[2]] += 1
print(count)