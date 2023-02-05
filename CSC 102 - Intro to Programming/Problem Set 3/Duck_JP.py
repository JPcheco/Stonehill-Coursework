#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 3: Ducks

# Problem Set 3: Part 1 (2 Points).

# Mrs Mallard has eight babies. You are to write a program that processes Mrs. Mallard’s day with her babies:

# #Mrs Mallard has 8 baby ducks

ducks = ["Pack", "Nack", "Lack", "Quack", "Kack", "Ouack", "Jack", "Mack"]
print(ducks)

#You are to add the code that completes the following actions

ducks.sort() #Mrs Mallard sorts her ducks in alphabetical order
print(ducks) #and then she prints the list of her babies

ducks.append("Dack") #a friend named Dack is added to the end of the line
print(ducks) #and then she prints the list of her babies

corner = ducks.pop(5) #the baby in position 5 acts up Mrs. Mallard tells him to leave the line and go to a variable space called corner
print(ducks) #and then she prints the list of her babies

ducks.insert(2, "Zack") #a friend named Zack joins the line at position 2
print(ducks) #and then she prints the list of her babies#

ducks.sort(reverse = True) #Mrs Mallard sorts her ducks in reverse alphabetical order
print(ducks) #and then she prints the list of her babies#

ducks.insert(2, corner) #the baby returns from the variable space called corner and joins the list at position 2
print(ducks) #and then she prints the list of her babies

ducks.append("Wack") #a friend named Wack joins the line at the end of the list
print(ducks) #and then she prints the list of her babies 

ducks.remove("Dack") #Dack acts up and Mrs Mallard makes him leave
print(ducks) #and then she prints the list of her babies

timeOut = ducks.pop() #the last duck in the line acts up Mrs. Mallard tells him to leave the line, he goes to a variable space called timeOut
print(ducks) #and then she prints the list of her babies

ducks.remove("Zack") #Zack is very loud and gives Mrs Mallard a headache she tells him to leave
print(ducks) #and then she prints the list of her babies

ducks.append(timeOut) #the duck in timeOut joins the line at the end
print(ducks) #and then she prints the list of her babies

ducks.remove("Wack") #Mrs Mallard notices that Wack is still in the line and tells him to leave
print(ducks) #and then she prints the list of her babies

value = len(ducks) #Mrs Mallard counts how many ducks she has in the line and
print(value) #then she prints the value to the screen
print(ducks) #and then she prints the list of her babies

ducks.sort() #Mrs Mallard sorts her duck in alphabetical order
print(ducks) #and then she prints the list of her babies


# Your output should look like this:

# IMPORTANT:  MY ORIGINAL OUTPUT HAD ERRORS -THE FOLLOWING IS THE CORRECT OUTPUT:

# ['Pack', 'Nack', 'Lack', 'Quack', 'Kack', 'Ouack', 'Jack', 'Mack']

# ['Jack', 'Kack', 'Lack', 'Mack', 'Nack', 'Ouack', 'Pack', 'Quack']

# ['Jack', 'Kack', 'Lack', 'Mack', 'Nack', 'Ouack', 'Pack', 'Quack', 'Dack']

# ['Jack', 'Kack', 'Lack', 'Mack', 'Nack', 'Pack', 'Quack', 'Dack']

# ['Jack', 'Kack', 'Zack', 'Lack', 'Mack', 'Nack', 'Pack', 'Quack', 'Dack']

# ['Zack', 'Quack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack', 'Dack']

# ['Zack', 'Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack', 'Dack']

# ['Zack', 'Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack', 'Dack', 'Wack']

# ['Zack', 'Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack', 'Wack']

# ['Zack', 'Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack']

# ['Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack']

# ['Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack', 'Wack']

# ['Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack']

# 8

# ['Quack', 'Ouack', 'Pack', 'Nack', 'Mack', 'Lack', 'Kack', 'Jack']

# ['Jack', 'Kack', 'Lack', 'Mack', 'Nack', 'Ouack', 'Pack', 'Quack']