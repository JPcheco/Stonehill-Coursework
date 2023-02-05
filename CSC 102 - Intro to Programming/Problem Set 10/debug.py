#Jacob Pacheco & Lorelie Murphy
#CSC 102-A Intro To Programming
#Problem Set 10: Grades

# The attached file details students performance by number of courses, time spend studying and grades.

# In this assignment you are to: 

# Find:

# the average mark by number of courses (number)
# the highest amount of time spend studying by number of courses (number)
# the lowest amount of time spend studying by number of courses (number)
# the highest grade by number of courses (number)
# the lowest grade by number of courses (number)
# Produce at least 3 graphs that visually illustrate relationships in the data.  

# Write a 500 word essay detailing your analysis and conclusions re: the data.

import csv
student = []

# number_courses = 0
# time_study = 1
# Marks = 2

# read in CSV 
with open('Student_Marks.csv') as file_object:
    reader = csv.reader(file_object)
    header_row = next(reader)
    for row in reader:
        student.append(list(map(float, row)))
file_object.close()
print(student)

# the total number of hours spent studying (number)
totalHours = 0
for row in student:
 totalHours += float(row[1])
print("Total number of hours spent studying:",totalHours)

# the average amount of time spent studying by number of courses (number)
threeC, fourC, fiveC, sixC, sevenC, eightC = 0, 0, 0, 0, 0, 0
threeCAVG, fourCAVG, fiveCAVG, sixCAVG, sevenCAVG, eightCAVG = 0, 0, 0, 0, 0, 0
threeTIME, fourTIME, fiveTIME, sixTIME, sevenTIME, eightTIME = 0, 0, 0, 0, 0, 0

for s in student:
    if(s[0] == 3):
        threeCAVG += s[1]
        threeC += 1
    elif(s[0] == 4):
        fourCAVG += s[1]
        fourC += 1
    elif(s[0] == 5):
        fiveCAVG += s[1]
        fiveC += 1
    elif(s[0] == 6):
        sixCAVG += s[1]
        sixC += 1
    elif(s[0] == 7):
        sevenCAVG += s[1]
        sevenC += 1
    elif(s[0] == 8):
        eightCAVG += s[1]
        eightC += 1

print(threeCAVG)

threeTIME = threeCAVG / threeC
print("Avg time studying for 3 courses:", threeTIME)
fourTIME = fourCAVG / fourC
print("Avg time studying for 4 courses:", fourTIME)
fiveTIME = fiveCAVG / fiveC
print("Avg time studying for 5 courses:", fiveTIME)
sixTIME = sixCAVG / sixC
print("Avg time studying for 6 courses:", sixTIME)
sevenTIME = sevenCAVG / sevenC
print("Avg time studying for 7 courses:", sevenTIME)
eightTIME = eightCAVG / eightC
print("Avg time studying for 8 courses:", eightTIME)





