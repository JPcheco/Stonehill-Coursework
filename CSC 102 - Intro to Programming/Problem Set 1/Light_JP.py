#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 1: Light Year

# Problem Set 1: Part 1 (2 Points)

# Light travels at 3 * 10^8 meters per second.
# A light-year is the distance a light beam travels in one year.  
# Write a program that will calculate and display the distance traveled by light in one light year in miles.

TimeInYear_InSeconds = 31557600
SpeedOfLight = 3*(10**8)
temp = 60*60*24*365* SpeedOfLight

def Meters_to_Miles(distance):
    #1 mile = 1609.344 meters
    MetersInMile = 1609.344

    x = distance / MetersInMile

    return x

print("Distance traveled by light in one light year in miles: " , Meters_to_Miles(temp) , " miles")