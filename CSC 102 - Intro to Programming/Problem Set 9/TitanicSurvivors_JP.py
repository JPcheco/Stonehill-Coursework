#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 9: Titanic Survivors

# Part 2: Titanic Survivors (3 points)

# The file TitanicSurvival.csv lists the information about the passengers on the HMS Titanic.

# In this assignment you are to write a program that will determine and output the following:

# The number of male passengers
# The number of female passengers
# The number of male passengers who perished
# The percentage of male passengers who perished
# The number of female passengers who perished 
# The percentage of female passengers who perished 
# The number of 1st class passengers
# The number of 2nd class passengers
# The number of 3rd class passengers
# The number of 1st class passengers who perished
# The number of 2nd class passengers who perished
# The number of 3rd class passengers who perished
# The percentage of 1st class passengers who perished
# The percentage of 2nd class passengers who perished
# The percentage of 3rd class passengers who perished

import csv
titanic = []

with open('TitanicSurvival.csv') as file_object:
    reader = csv.reader(file_object)
    attributes = next(reader)

    for row in reader:
        titanic.append(row)
file_object.close()
# print(titanic)

males = 0
for row in titanic:
    if row[2] == 'male':
        males += 1
print("Number of Males:", males)

females = 0
for row in titanic:
    if row[2] == 'female':
        females += 1
print("Number of Females:", females)

dead_M = 0
for row in titanic:
    if row[2] == 'male' and row[1] == 'no':
        dead_M += 1
print("Number of Males that died:", dead_M)
print("Percentage of Males that died:", dead_M/males*100)

dead_F = 0
for row in titanic:
    if row[2] == 'female' and row[1] == 'no':
        dead_F += 1
print("Number of Females that died:", dead_F)
print("Percentage of Females that died:", dead_F/females*100)

FirstClass = 0
for row in titanic:
    if row[4] == '1st':
        FirstClass += 1
print("Number in First Class:", FirstClass)

SecondClass = 0
for row in titanic:
    if row[4] == '2nd':
        SecondClass += 1
print("Number in Second Class:", SecondClass)

ThirdClass = 0
for row in titanic:
    if row[4] == '3rd':
        ThirdClass += 1
print("Number in Third Class:", ThirdClass)

Dead1 = 0
for row in titanic:
    if row[4] == '1st' and row[1] == 'no':
        Dead1 += 1
print("Number Dead in First Class:", Dead1)
print("Percentage of 1st Class that died:", Dead1/FirstClass*100)

Dead2 = 0
for row in titanic:
    if row[4] == '2nd' and row[1] == 'no':
        Dead2 += 1
print("Number Dead in Second Class:", Dead2)
print("Percentage of 2nd Class that died:", Dead2/SecondClass*100)

dead3 = 0
for row in titanic:
    if row[4] == '3rd' and row[1] == 'no':
        dead3 += 1
print("Number Dead in Third Class:", dead3)
print("Percentage of 3rd Class that died:", dead3/ThirdClass*100)



