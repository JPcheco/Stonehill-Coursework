#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 1: British Currency

# Problem Set 1: Part 3 (2 Points)

# Prior to 1971 the currency of Britain was as follows:
# 1 penny  (the basic unit)
# 1 Threepence = 3 pennies
# 1 Sixpence = 6 pennies
# 1 Shilling = 12 pennies
# 1 florin = 2 shillings
# 1 Half-crown = 2 shillings and 6 pennies
# 1 Crown = 5 shillings
# 1 Pound = 20 shillings

# In this assignment you are to write a program that will accept a number of British pennies 
# from the user and output the minimum number of British currency units in that number of pennies.

StartPennies = input("Please enter the number of pennies: ")

#Variables
Pounds = 0
Crowns = 0
Half_Crowns = 0
Florins = 0
Shillings = 0
Sixpences = 0
Threepences = 0
Pennies = 0

#Constants
Num_1 = 1
Num_3 = 3
Num_6 = 6
Num_12 = 12
Num_24 = 24
Num_30 = 30
Num_60 = 60
Num_240 = 240

#StartPennies -> Shillings conversion + remaining Pennies
Pounds = int(StartPennies) / Num_240 #get Pounds
StartPennies = int(StartPennies) % Num_240 #update StartPennies
Crowns = int(StartPennies) / Num_60 #get Crowns
StartPennies = int(StartPennies) % Num_60 #update StartPennies
Half_Crowns = int(StartPennies) / Num_30 #get Half-Crowns
StartPennies = int(StartPennies) % Num_30 #update StartPennies
Florins = int(StartPennies) / Num_24 #get Florins
StartPennies = int(StartPennies) % Num_24 #update StartPennies
Shillings = int(StartPennies) / Num_12 #get Shillings
StartPennies = int(StartPennies) % Num_12 #update StartPennies
Sixpences = int(StartPennies) / Num_6 #get Sixpences
StartPennies = int(StartPennies) % Num_6 #update StartPennies
Threepences = int(StartPennies) / Num_3 #get Threepences
StartPennies = int(StartPennies) % Num_3 #update StartPennies
Pennies = int(StartPennies) / Num_1 #get Pennies

print("Pounds: " , int(Pounds))
print("Crowns: " , int(Crowns))
print("Half-Corwns: " , int(Half_Crowns))
print("Florins: " , int(Florins))
print("Shillings: " , int(Shillings))
print("Sixpences: " , int(Sixpences))
print("Threepences: " , int(Threepences))
print("Pennies: " , int(Pennies))