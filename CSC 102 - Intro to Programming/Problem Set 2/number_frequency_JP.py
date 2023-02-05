#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 2: Number Frequency


# Problem Set 2: Part 1 (2 Points)
# In this assignment you are to create a list of 100 random numbers 1 - 10 (I have supplied the code to do that below.

# You are to then determine and print to the screen the frequency of each number.

#code to create a list of random numbers

import random

numbers = []

for i in range(0, 100):

    numbers.append(random.randint(1, 10))

One = Two = Three = Four = Five = Six = Seven = Eight = Nine = Ten = 0

for i in range(0, 100): #make a for loop to run through array of random ints
    temp = numbers[i] #store the values in a varilble
    
    match temp: #bump the number to what varible it belongs to
        case 1:
            One += 1
        case 2:
            Two += 1
        case 3:
            Three += 1
        case 4:
            Four += 1
        case 5:
            Five += 1
        case 6:
            Six += 1
        case 7:
            Seven += 1
        case 8:
            Eight += 1
        case 9:
            Nine += 1
        case 10:
            Ten += 1

print("Frequency of 1 is: " , One)
print("Frequency of 2 is: " , Two)
print("Frequency of 3 is: " , Three)
print("Frequency of 4 is: " , Four)
print("Frequency of 5 is: " , Five)
print("Frequency of 6 is: " , Six)
print("Frequency of 7 is: " , Seven)
print("Frequency of 8 is: " , Eight)
print("Frequency of 9 is: " , Nine)
print("Frequency of 10 is: " , Ten)
