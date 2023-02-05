#Jacob Pacheco
#CSC 102-A Intro To Programming
#Final

# Sandpiper Air

planes = [
    [1, 310, True, 'Window'],
    [2, 315, True, 'Aisle'],
    [3, 315, True, 'Aisle'],
    [4, 305, True, 'Window'],
    [5, 310, True, 'window'],
    [6, 320, True, 'Aisle'],
    [7, 305, True, 'Aisle'],
    [8, 280, True, 'Window'],
    [9, 300, True, 'Window'],
    [10, 410, True, 'Aisle'],
    [11, 410, True, 'Aisle'],
    [12, 320, True, 'Window'],
    [13, 305, True, 'Window'],
    [14, 380, True, 'Aisle'],
    [15, 390, True, 'Aisle'],
    [16, 290, True, 'Window'],
    [17, 285, True, 'Window'],
    [18, 260, True, 'Aisle'],
    [19, 360, True, 'Aisle'],
    [20, 285, True, 'Window']]


def display_Menu():
    print("Enter 1 to print all seats (seat number, price, availability, aisle or window seat).")
    print("Enter 2 to print the available aisle seats (seat number, price).")
    print("Enter 3 to print the available window seats (seat number, price).")
    print("Enter 4 to print the available seats at or below a certain price as entered by the user  (seat number, price, aisle or window seat)")
    print("Enter 5 to print the information on a specific seat number as entered by the user (price, if it is or is not available, and if it is an aisle or window seat)")
    print("Enter 6 to purchase a specific seat number as entered by the user")
    print("Enter 7 to print the total price of all seats that have been sold")
    print("Enter 8 to see this menu again.")
    print("Enter 0 to quit")
    
totalPrice = 0
display_Menu()
choice = input("User Input: ")
print()

while(choice != 0):
    match int(choice):
        case 1:
            for x in planes:
                print("Seat Number:", x[0], "Price:", x[1], "Availabilty:", x[2], "Type of Seat:", x[3])
        case 2:
            for x in planes:
                if x[2] == True and x[3] == 'Aisle':
                    print("Seat Number:", x[0], "Price:", x[1])
        case 3:
            for x in planes:
                if x[2] == True and x[3] == 'Window':
                    print("Seat Number:", x[0], "Price:", x[1])
        case 4:
            limit = input("Enter desired seat price:")

            for x in planes:
                if x[1] <= int(limit):
                    print("Seat Number:", x[0], "Price:", x[1], "Type of Seat:", x[3])
        case 5:
            seatNum = input("Enter desired seat number to view:")
            
            if int(seatNum) > len(planes):
                print("Seat Number is not available")
            else:
                for x in planes:
                    if x[0] == int(seatNum):
                        print("Price:", x[1], "Availabilty:", x[2], "Type of Seat:", x[3])
        case 6:
            seatNum = input("Enter desired seat number to purchase:")

            if int(seatNum) > len(planes):
                print("Seat Number is not available")
            else:
                for x in planes:
                    if x[0] == int(seatNum) and x[2] == True:
                        print("Purchased seat number", seatNum, "Successfully")
                        totalPrice += x[1]
                        x[2] = False
                    elif x[0] == int(seatNum) and x[2] == False:
                        print("Purchased seat number", seatNum, "was Unsuccessfull")
        case 7:
            print("total price of all seats that have been sold:", totalPrice)
        case 8:
            display_Menu()
        case 0:
            print("Exiting Program...")
            exit()
    print()

    display_Menu()
    choice = input("User Input: ")
    print()