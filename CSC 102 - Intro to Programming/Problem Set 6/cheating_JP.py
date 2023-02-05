#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 6: Cheating

# Problem Set 6: Part 2 (2 Points).

# You are a college professor who suspects that students have copied
# each others answers on a multiple choice (a,b,c,d) exam.

# Building on the code I have developed   CheatingCodeForCompletion.py   , you are to
# develop a program that will tell you which students have identical answers. 

# I have developed the code that will generate answers for each of the students.
# You are to complete the code to analyze the answers and print to the screen
# your analysis as demonstrated in the two example below
# (please note that the output of the names of those who copied prints the names twice).                                                                                                                                                                                                            

# CheatingCodeForCompletion.py, (below and loaded to eLearn)

import random

#the students in the class
#the most popular names in 2020
students = ["Oliver","Liam","Ethan","Aiden","Gabriel","Caleb","Theodore","Declan","Owen","Elijah","Charlotte","Ava","Amelia","Olivia","Aurora","Violet","Luna","Hazel","Cloe","Aria"]

#needed to build the test results
test_results = [] 
answers = ['a', 'b', 'c', 'd'] 
num_questions = 3  #Smaller number yields more instances of cheating
                  
# ##this code will build a dictionary of length 20 of 
# ##key - student name
# ##value - a list of num_questions length of randomly selected
# ##values from the list answers above
for student in students:
  result = []
  for i in range(num_questions):
    result.append(random.choice(answers))
  test = {}
  test[student] = result
  test_results.append(test)

print("Raw Data:")
print(test_results)
print("Names and Answers of Each Student")
for student in test_results:
  print(list(student.keys()), list(student.values()))

print("Names and Answers of Students Who Copied")

grades1 = []
grades2 = []

for student1 in test_results:
  for student2 in test_results:
    grades1.append(list(student1.values()))
    grades2.append(list(student2.values()))
    if grades1 == grades2:
      if student1.keys() != student2.keys():
        print(list(student1.keys()), list(student1.values()), "copied", list(student2.keys()), list(student2.values()))

    grades1.clear()
    grades2.clear()