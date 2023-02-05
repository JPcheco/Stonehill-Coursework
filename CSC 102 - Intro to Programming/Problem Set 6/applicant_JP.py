#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 6: applicant

#Problem Set 6: Part 1 (2 Points).

##In this assignment you are to analyze your companies performance/success

## in talent acquisition, specifically where applicants are in the process and

##the yield of people who accept offers.

##The following code will generate a dictionary with keys 1 - 8 (inclusive)

##and values associated with those keys

##you are to conduct your analysis on the information in the dictionary

process_status = [1, 2, 3, 4, 5, 6, 7, 8]

# Process status is noted as:
#     1, applicant has submitted resume
#     2, applicant resume accepted
#     3, applicant interview scheduled
#     4, interview conducted
#     5, applicant rejected
#     6, applicant added to resume pool for future consideration
#     7, offer made to applicant
#     8, applicant accepts offer

#You are to use this code in your submission, cut and paste it into your code

import random

n = 1000

applicants = {}

for i in range(n):

    record = [number for number in range(1, random.randint(2, 5))]

    if 4 in record:
        r = random.randint(0, 100)

        if r % 5 == 0:
            record.append(5)
            applicants[i] = record

            continue

        if r % 6 == 0:
            record.append(6)
            applicants[i] = record

            continue

        if r % 7 == 0:
            record.append(7)
            r = random.randint(0, 100)

            if r % 8 == 0:
                record.append(8)   

    applicants[i] = record  

print(applicants)
i = 0 

# In this assignment you are to determine the following

# Part A: print the applicant ID for every applicant in each contact status 
contact_status_1 = []
contact_status_2 = []
contact_status_3 = []
contact_status_4 = []
contact_status_5 = []
contact_status_6 = []
contact_status_7 = []
contact_status_8 = []

for applicant in applicants.values():
    if 1 in applicant:
        contact_status_1.append(i)
    if 2 in applicant:
        contact_status_2.append(i)
    if 3 in applicant:
        contact_status_3.append(i)
    if 4 in applicant:
        contact_status_4.append(i)
    if 5 in applicant:
        contact_status_5.append(i)
    if 6 in applicant:
        contact_status_6.append(i)
    if 7 in applicant:
        contact_status_7.append(i)
    if 8 in applicant:
        contact_status_8.append(i)
    i = i + 1

print("Number of applicants that have submitted their resume:", contact_status_1)
print("Number of applicants that had their resume accepted:", contact_status_2)
print("Number of applicants that have their interview scheduled:", contact_status_3)
print("Number of applicants that had their interview conducted:", contact_status_4)
print("Number of applicants that got rejected:", contact_status_5)
print("Number of applicants that were added to resume pool for future consideration:", contact_status_6)
print("Number of applicants that had an offer made to them:", contact_status_7)
print("Number of applicants that accepted the offer:", contact_status_8)
print()

# Part B: print the number, number and percentage of applicants who:
#     submit a resume (number) and then get interviewed (number and percentage)
print("# who submitted a resume:", len(contact_status_1)," Those of whom got an interview:", len(contact_status_4)," The %:", len(contact_status_4)/len(contact_status_1)*100)
#     are interviewed (number) and are rejected (number and percentage)
print("# who are interviewed:", len(contact_status_4)," Those of whom get rejected:", len(contact_status_5)," The %:", len(contact_status_5)/len(contact_status_4)*100)
#     are interviewed (number) and are given an offer (number and percentage)
print("# who are interviewed:", len(contact_status_4)," Those of whom got an offer:", len(contact_status_7)," The %:", len(contact_status_7)/len(contact_status_4)*100)
#     are given an offer (number) and accept (number and percentage)
print("# who are given an offer:", len(contact_status_7)," Those of whom accepted:", len(contact_status_8)," The %:", len(contact_status_8)/len(contact_status_7)*100)