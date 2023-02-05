#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 5: Rock-Paper-Sissors-Lizards-Spock

# Problem Set 5: Part 1 (2 Points).

# Your assignment is to program a game (player vs. computer) of Rock-Paper-Sissors-Lizards-Spock. 

# Your program will have one player playing against the computer
#  The characters the user may enter are:
# R (for rock)
# P (for paper)
# S (for scissors)
# L (for lizard)
# K (for spock)
# Your program will generate a random char to represent rock, paper, scissors, lizard, or spock. 
# Hint: you could generate a random number 0 - 4 and assign the char value based on the number generated.
# The player input will be compared to the randomly generated value of the computer

# Your program will output the appropriate message
# If you are unfamiliar with the game of Rock-Paper-Sissors-Lizards-Spock the rules are:

# Scissors cuts paper
# paper covers rock
# rock crushes lizard
# lizard poisons Spock
# Spock smashes scissors
# scissors decapitates lizard
# lizard eats paper
# paper disproves Spock
# Spock vaporizes rock
# and as it always has, rock crushes scissors

import random
import sys

def returnCPUMove():
    val = random.randint(0, 4)
    move = ""
    
    match(val):
        case 0: # R (for rock)
            move = "R"
            return move
        case 1: # P (for paper)
            move = "P" 
            return move
        case 2: # S (for scissors)
            move = "S"
            return move
        case 3: # L (for lizard)
            move = "L"
            return move
        case 4: # K (for spock)
            move = "K"
            return move
    
def determineResults(user_move, computer_move):
    if(user_move == computer_move): 
        return 2 # Both User and CPU play same move
    elif(user_move == "S" and computer_move == "P"):
        return 0 # Scissors cuts Paper
    elif(user_move == "P" and computer_move == "S"):
        return 1 # Scissors cuts Paper
    elif(user_move == "S" and computer_move == "R"):
        return 1 # Rock crushes Scissors
    elif(user_move == "R" and computer_move == "S"):
        return 0 # Rock crushes Scissors
    elif(user_move == "S" and computer_move == "K"):
        return 1 # Spock smashes scissors
    elif(user_move == "K" and computer_move == "S"):
        return 0 # Spock smashes scissors
    elif(user_move == "S" and computer_move == "L"):
        return 0 # Scissors decapitates lizard
    elif(user_move == "L" and computer_move == "S"):
        return 1 # Scissors decapitates lizard
    elif(user_move == "P" and computer_move == "R"):
        return 0 # Paper covers rock
    elif(user_move == "R" and computer_move == "P"):
        return 1 # Paper covers rock
    elif(user_move == "R" and computer_move == "L"):
        return 0 # Rock crushes lizard
    elif(user_move == "L" and computer_move == "R"):
        return 1 # Rock crushes lizard
    elif(user_move == "K" and computer_move == "R"):
        return 0 # Spock vaporizes rock
    elif(user_move == "R" and computer_move == "K"):
        return 1 # Spock vaporizes rock
    elif(user_move == "P" and computer_move == "K"):
        return 0 # Paper disproves Spock
    elif(user_move == "K" and computer_move == "P"):
        return 1 # Paper disproves Spock
    elif(user_move == "L" and computer_move == "K"):
        return 0 # lizard poisons Spock
    elif(user_move == "K" and computer_move == "L"):
        return 1 # lizard poisons Spock
    elif(user_move == "L" and computer_move == "P"):
        return 0 # lizard eats paper
    elif(user_move == "P" and computer_move == "L"):
        return 1 # lizard eats paper
    else: #something went wrong
        return -1

available_Characters = ["R (for rock)", "P (for paper)", "S (for scissors)", "L (for lizard)", "K (for spock)"]
instructions = "'-1' to quit, anything else to play: "
accepted_input = ["R", "P", "S", "L", "K"]

print("Welcome to Rock-Paper-Sissors-Lizards-Spock!")
print("Would you like to play a game? ")
play_again = input(instructions)

if(play_again == "-1"):
    print("Thanks for playing! Bye!")
    sys.exit(0)

valid = True
user_move = computer_move = ""
user_wins = user_loses = games_played = ties = 0

while(play_again != "-1"):
    #checking for valid input from user
    while(valid):
        print()
        print(available_Characters)
        user_move = input("User move... ")
        user_move = user_move.capitalize()

        if(user_move not in accepted_input):
            print("Invalid move, please try again...")
        else:
            valid = False
   
    # Does the computer move
    computer_move = returnCPUMove()
    print("Computer played", computer_move)
    print()

    result = determineResults(user_move, computer_move)
    if(result == 0): #User win
        print("User's", user_move, "beat the Computer's", computer_move)
        print("User Wins!")
        user_wins = user_wins + 1
    elif(result == 1): #CPU win
        print("User's", user_move, "lost to the Computer's", computer_move)
        print("User loses!")
        user_loses = user_loses + 1
    elif(result == 2): #Tie game
        print("Both the user and the Computer had the same move...")
        print("It's a Tie!")
        ties = ties + 1
    else: #debug purposes
        print("Something went wrong")

    games_played = games_played + 1
    valid = True

    print()
    print("Would you like to play again?")
    play_again = input(instructions)

print()
print("Total games played: ", games_played)
print("User wins:", user_wins)
print("User loses:", user_loses)
print("Ties:", ties)
print()
print("Thanks for playing! Bye!")