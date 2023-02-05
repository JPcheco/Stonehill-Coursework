#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 5: Apartment

# Problem Set 5: Part 2 (2 Points). 

# You have been hired by a real estate agent to write a program that will advise them if they should or should not show an apartment to a client.

# Your program must ask the user to enter the following information and save the values entered into variables:

Able = ["1500", "2", "2", "0", "true", "true", "2000", "Able"]
Baker = ["500", "1", "1", "0", "false", "false", "1000", "Baker"]
Charlie = ["2000", "3", "2", "4", "true", "false", "3000", "Charlie"]
Delta = ["1500", "3", "2", "4", "true", "true", "3000", "Delta"]

clients = [Able, Baker, Charlie, Delta]

def get_int_input():
    number = 0
    valid = True

    while(valid): #checks for valid int input
        number = input()

        if number.isdigit():
            valid = False
        else:
            print("Invalid input, please enter a valid integer (x >= 0)")

    return number 

def get_string_input():
    string = ""
    valid = True

    while(valid): #checks for vail true false input
        string = input()

        if any(char.isdigit() for char in string):
            print("Please enter only 'true' or 'false'")
        else:
            valid = False

    return string 

print("Enter the apartment size in square feet:")
square_feet = get_int_input()
print("Enter the number of bedrooms:")
bedrooms = get_int_input()
print("Enter the number of bathrooms:")
bathrooms = get_int_input()
print("Enter the floor:")
floor = get_int_input()
print("Enter elevator (true or false):")
elevator = get_string_input()
print("Enter off street parking (true or false):")
off_street_parking = get_string_input()
print("Enter the monthly rent:")
monthly_rent = get_int_input()

current_appartment = [square_feet, bedrooms, bathrooms, floor, elevator, off_street_parking, monthly_rent]
score = 0

print()
print("Show the apartment to the following: ")

for client in clients:
    for i in range(len(client)):
        if(i < 4):
            if client[i] <= current_appartment[i]: #comparing the first 4 items
                score = score + 1
        elif(i > 3 and i < 6): #comparing the true false
            if client[i] == current_appartment[i]:
                score = score + 1
            elif(client[i] == "false" and current_appartment[i] == "true"):
                score = score + 1
        elif(i == 6):  #comparing the rent
            if client[i] >= current_appartment[i]:
                score = score + 1
    
    if score > 5: #weighted system, show apartment to person if the apartement meets a certain score
        print(client[7])

    score = 0