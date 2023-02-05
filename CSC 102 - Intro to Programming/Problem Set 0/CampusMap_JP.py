#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 0: Turtle Map

# In this assignment you are to write a program using the Turtle module of Python to print to the screen a map of the Stonehill College Campus.  
# You map is to include the following buildings:
#Each building may be represented by an outline (or with fill) in therelative shap and size of the actual building.

from re import T
import turtle

t = turtle.Turtle()
turtle.screensize(canvwidth=400, canvheight= 400, bg= None)

t.speed(10)
t.hideturtle() #hide Turtle

#Method to draw buildings as rectangles
def MakeRectangle(length, height):
    for i in range(0,4):
        if i % 2 == 0: #draws the length
            t.forward(length)
            t.right(90)
        else:   #draws the height
            t.forward(height)
            t.right(90)
            
#marking the center of the map
#MakeRectangle(10,10)

#Science Center
t.up()
t.goto(100,-175)
t.down()
t.write("Science")
MakeRectangle(30,15)
MakeRectangle(15,30)
MakeRectangle(20,20)

#O'Hara
t.up()
t.goto(150,-125)
t.down()
t.write("O'Hara")
MakeRectangle(30,10)
MakeRectangle(10,30)
t.goto(150,-155)
MakeRectangle(30,10)

#Martin
t.up()
t.goto(70,-130)
t.down()
t.write("Martin")
MakeRectangle(35,35)

#College Center
t.up()
t.goto(60,-65)
t.down()
t.write("College Center")
MakeRectangle(25,27)
MakeRectangle(25,35)
t.up()
t.goto(65,-95)
t.down()
MakeRectangle(25,15)

#Chapel
t.up()
t.goto(100,5)
t.down()
t.write("Chapel")
MakeRectangle(20,15)
MakeRectangle(7,30)

#Meehan
t.up()
t.goto(130,55)
t.down()
t.write("Meehan")
MakeRectangle(40,20)

#Senior Courts (treat as one building)
t.up()
t.goto(185,75)
t.down()
t.write("Senior Courts")
MakeRectangle(75,55)

#Duffy
t.up()
t.goto(190,135)
t.down()
t.write("Duffy")
MakeRectangle(45,18)
MakeRectangle(15,30)
t.goto(190,105)
MakeRectangle(45,18)

#Junior Courts (treat as one building)
t.up()
t.goto(185,205)
t.down()
t.write("Junior Courts")
MakeRectangle(75,55)

#Boland
t.up()
t.goto(285,200)
t.down()
t.write("Boland")
MakeRectangle(45,30)

#Art/Shovel Museum
t.up()
t.goto(55,60)
t.down()
t.write("Museum")
MakeRectangle(35,30)

#May
t.up()
t.goto(55,135)
t.down()
t.write("May")
MakeRectangle(35,38)

#Library
t.up()
t.goto(15,105)
t.down()
t.write("Library")
MakeRectangle(30,40)

#Donahue
t.up()
t.goto(87,235)
t.down()
t.write("Donahue")
MakeRectangle(40,18)

turtle.done()