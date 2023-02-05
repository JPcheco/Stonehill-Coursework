#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 2: Stock Sale

# Problem Set 2: Part 2 (2 Points)

# In this assignment you are to calculate the total gain/loss on the sale of stocks as detailed below.

# Assume that you have the following three lists (copy and paste them into your code) which represent the
# amount you paid per share for the stock, the number of shares you sold and the sale price per share of stock:

cost_of_stock = [ 21.5, 6.25, 10.75, 15.0]

number_of_shares_sold = [ 100, 200, 100, 300]

sale_price_of_stock = [ 45.5, 6.25, 12.75, 10.0]

# bought $2150 worth
# sold $4550 worth
# gain of $2400

buyValue = shares = sellVaule = difference = 0

for i in range(0, len(cost_of_stock)): #loops through the lists
    shares = number_of_shares_sold[i] #gets the number of shares sold of i
    buyValue = cost_of_stock[i] * shares #calculates the buy value
    sellVaule = sale_price_of_stock[i] * shares #calculates the sell value
    difference = sellVaule - buyValue #gets the differnece of the two

    if(difference >= 0): #checks to see if the difference is positive or negative
        print("Total gain for sale of stock of", i, "is: $",difference)
    else:    
        print("Total loss for sale of stock of", i, "is: $",difference)