#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 4: Superbowls

# Problem Set 4: Part 2 (2 Points).

# The following is a list (a list of lists) of the results of all of the Super Bowls played to date (copy and paste it into your code).

#the information in each list is
# year, number, winner, loser, final score, location

super_bowls = [
[2022,"LVI","LA Rams","Cincinnati","23-20","Inglewood"],
[2021,"LV","Tampa Bay Buccaneers","Kansas City Chiefs","31-9","Tampa"],
[2020,"LIV","Kansas City Chiefs","San Francisco 49ers","31-20","Miami"],
[2019,"LIII","New England Patriots","Los Angeles Rams","13-3","Atlanta"],
[2018,"LII","Philadelphia Eagles","New England Patriots","41-33","Minneapolis"],
[2017,"LI","New England Patriots","Atlanta Falcons","34-28","Houston"],
[2016,"L","Denver Broncos","Carolina Panthers","24-10","Santa Clara"],
[2015,"XLIX","New England Patriots","Seattle Seahawks","28-24","Phoenix"],
[2014,"XLVIII","Seattle Seahawks","Denver Broncos","43-8","New Jersey"],
[2013,"XLVII","Baltimore Ravens","San Francisco 49ers","34-31","New Orleans"],
[2012,"XLVI","New York Giants","New England Patriots","21-17","Indianapolis"],
[2011,"XLV","Green Bay Packers","Pittsburgh Steelers","31-25","Arlington"],
[2010,"XLIV","New Orleans Saints","Indianapolis Colts","31–17","Miami"],
[2009,"XLIII","Pittsburgh Steelers","Arizona Cardinals","27-23","Tampa"],
[2008,"XLII","New York Giants","New England Patriots","17-14","Phoenix"],
[2007,"XLI","Indianapolis Colts","Chicago Bears", "29-17","Miami"],
[2006,"XL","Pittsburgh","Seattle","21-10","Detroit"],
[2005,"XXXIX","New England Patriots","Philadelphia","24-21","Jacksonville"],
[2004,"XXXVIII","New England Patriots","Carolina","32-29","Houston"],
[2003,"XXXVII","Tampa Bay","Oakland","48-21 ","San Diego"],
[2002,"XXXVI","New England Patriots","St Louis","20-17","New Orleans"],
[2001,"XXXV","Baltimore","NY Giants","34-7","Tampa"],
[2000,"XXXIV","St Louis","Tennessee","23-16","Atlanta"],
[1999,"XXXIII","Denver","Atlanta","34-19","Miami"],
[1998,"XXXII","Denver","Green Bay","31-24","San Diego"],
[1997,"XXXI","Green Bay","New England Patriots","35-21","New Orleans"],
[1996,"XXX","Dallas","Pittsburgh","27-17","Tempe"],
[1995,"XXIX","San Francisco","San Diego","49-26","Miami"],
[1994,"XXVIII","Dallas","Buffalo","30-13","Atlanta"],
[1993,"XXVII","Dallas","Buffalo","52-17","Pasadena"],
[1992,"XXVI","Washington","Buffalo","37-24","Minneapolis"],
[1991,"XXV","NY Giants","Buffalo","20-19","Tampa"],
[1990,"XXIV","San Francisco","Denver","55-10","New Orleans"],
[1989,"XXIII","San Francisco","Cincinnati","20-16","Miami"],
[1988,"XXII","Washington","Denver","42-10","San Diego"],
[1987,"XXI","NY Giants","Denver","39-20","Pasadena"],
[1986,"XX","Chicago","New England Patriots","46-10","New Orleans"],
[1985,"XIX","San Francisco","Miami","38-16","Stanford"],
[1984,"XVIII","LA Raiders","Washington","38-9","Tampa"],
[1983,"XVII","Washington","Miami", "27-17","Pasadena"],
[1982,"XVI","San Francisco","Cincinnati","26-21","Pontiac"],
[1981,"XV","Oakland","Philadelphia","27-10","New Orleans"],
[1980,"XIV","Pittsburgh","LA Rams","31-19", "Pasadena"],
[1979,"XIII","Pittsburgh","Dallas","35-31","Miami"],
[1978,"XII","Dallas","Denver","27-10","New Orleans"],
[1977,"XI","Oakland","Minnesota","32-14","Pasadena"],
[1976,"X","Pittsburgh","Dallas","21-17","Miami"],
[1975,"IX","Pittsburgh","Minnesota","16-6","New Orleans"],
[1974,"VIII","Miami","Minnesota","24-7","Houston"],
[1973,"VII","Miami","Washington","14-7","Los Angeles"],
[1972,"VI","Dallas","Miami","24-3","New Orleans"],
[1971,"V","Baltimore","Dallas","16-13","Miami"],
[1970,"IV","Kansas City","Minnesota","5","23-7","New Orleans"],
[1969,"III","NY Jets","Baltimore","16-7","Miami"],
[1968,"II","Green Bay","Oakland","33-14","Miami"],
[1967,"I","Green Bay","Kansas City","35-10","Los Angeles"]
]#year, number, winner, loser, final score, location

# In the assignment you are to:
# find and print to the screen the number of times the New England Patriots have won the Super Bowl
# find and print to the screen the number of times the New England Patriots have lost the Super Bowl.
# find and print to the screen the first year the New England Patriots won the Super Bowl.
# find and print to the screen the first year the New England Patriots lost the Super Bowl

# Hint: To iterate through the list

year = 0
number = 1
winner = 2
loser = 3
final_score = 4
location = 5

win = loss = 0
firstWin = firstLoss = 0

for super_bowl in super_bowls:    
    if(super_bowl[2] == "New England Patriots"):
        win += 1
        firstWin = super_bowl[0]

    if(super_bowl[3] == "New England Patriots"):
        loss += 1
        firstLoss = super_bowl[0]

print("The number of times the New England Patriots have won the Super Bowl is: ", win)
print("The number of times the New England Patriots have lost the Super Bowl is: ", loss)
print("The first year the New England Patriots  won the Super Bowl is: ", firstWin)
print("The first year the New England Patriots lost the Super Bowl is: ", firstLoss)