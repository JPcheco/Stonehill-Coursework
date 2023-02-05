#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 1: Virus

# Problem Set 1: Part 2 (2 Points)

# In the first month 60% of the population is infected.
# In the second month 50% of the uninfected population is infected.
# In the third month 40% of the uninfected population is infected.
# In the fourth month 30% of the uninfected population is infected.
# In the fifth month 20% of the uninfected population is infected.
# In the sixth month 10% of the uninfected population is infected

# Write a program that will accept form the user the total population (number of people) and 
# print to the screen the number of people who are infected each month, the total number of people 
# infected at the end of 6 months and the total number of people uninfected at the end of 6 months. 
# Display all numbers as whole numbers.

TotalPopulation = input("Enter the total Population (number of people): \n")
infectedTotal = 0

infected:float =  float(TotalPopulation) * 0.60
surivors:float = float(TotalPopulation) - infected
print("Total number of people infected in month 1: " , int(infected))
infectedTotal += infected

infected:float =  float(surivors) * 0.50
surivors:float = float(surivors) - infected
print("Total number of people infected in month 2: " , int(infected))
infectedTotal += infected

infected:float =  float(surivors) * 0.40
surivors:float = float(surivors) - infected
print("Total number of people infected in month 3: " , int(infected))
infectedTotal += infected

infected:float =  float(surivors) * 0.30
surivors:float = float(surivors) - infected
print("Total number of people infected in month 4: " , int(infected))
infectedTotal += infected

infected:float =  float(surivors) * 0.20
surivors:float = float(surivors) - infected
print("Total number of people infected in month 5: " , int(infected))
infectedTotal += infected

infected:float =  float(surivors) * 0.10
surivors:float = float(surivors) - infected
print("Total number of people infected in month 6: " , int(infected))
infectedTotal += infected

print("Total number of people infected after 6 months is: " , int(TotalPopulation)-int(surivors))
print("Total number of people uninfected after 6 months is: " , int(surivors))

