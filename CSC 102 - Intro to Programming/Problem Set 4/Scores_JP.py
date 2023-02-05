#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 4: Scores

# Problem Set 4: Part 1 (2 Points). 

# In this assignment you are to analyze the score of 28 students in a class.  Each student has taken 5 exams.  You are to populate and print the following lists:
#     The highest score of each student
#     The lowest score of each student
#     The average score of each student

# The nested list of students scores is (you are to copy and paste this list into your code):

scores = [[73, 97, 81, 96, 84], [73, 62, 75, 86, 90], [87, 73, 80, 64, 87], [89, 92, 61, 85, 76], [86, 70, 61, 67, 73], [81, 95, 92, 61, 92],
          [61, 72, 95, 89, 82], [76, 64, 83, 95, 94], [83, 99, 94, 93, 100], [63, 84, 92, 91, 68],[66, 91, 78, 92, 90], [70, 66, 87, 85, 99],
          [93, 95, 90, 88, 98], [96, 83, 86, 71, 65], [96, 96, 65, 100, 93],[82, 91, 87, 100, 81],[72, 75, 73, 82, 84], [96, 88, 81, 61, 92],
          [63, 83, 62, 68, 95], [86, 82, 81, 91, 90], [61, 67, 94, 92, 94], [79, 97, 77, 97, 83], [60, 82, 75, 66, 67], [93, 66, 89, 80, 79],
          [62, 98, 94, 77, 85], [85, 85, 62, 95, 66], [69, 65, 92, 93, 92], [70, 90, 64, 97, 93]]

StudentScores = [] #array to store student scores

for i in range(0, len(scores)): #loop through scores array
    StudentScores = scores[i] #set studentscores to the section of scores
    StudentScores.sort() #sort from low to high
    print("The highest score of each student: ", StudentScores[len(StudentScores)-1]) #print the highest
    StudentScores.sort(reverse=True) #sort form high to low
    print("The lowest score of each student: ", StudentScores[len(StudentScores)-1]) #print the lowest
    print("The average score of each student: ", sum(StudentScores)/len(StudentScores)) #print the average
    print()