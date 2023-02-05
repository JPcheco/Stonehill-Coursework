#Jacob Pacheco
#CSC 102-A Intro To Programming
#Problem Set 3: Increase Decrease

# Problem Set 3: Part 2 (2 Points). 

# In this assignment you are to calculate and print to the screen the increase or decrease (indicate a decrease with a negative number) 
# from one line to the next as you iterate through the list and the percentage of that increase/decrease.

# The following is a list of numbers, copy and past the list into your code.  
numbers = [151868, 153982, 156393, 158956, 161884, 165069, 168088, 171187, 174149, 177135, 179979, 182992, 185771, 188483,
           191141, 193526, 195576, 197457, 199399, 201385, 203984, 206827, 209284, 211357, 213342, 215465, 217563, 219760,
           222095, 224567, 227225, 229466, 231664, 233792, 235825, 237924, 240133, 242289, 244499, 246819, 249623]

OldNum = NewNum = 0 #set varibles to 0

for i in range(0, len(numbers) -1): #loop through lift
    if(i != len(numbers) -1): #catchs at the end so we dont fall off
        OldNum = numbers[i] #sets old num
        NewNum = numbers[i+1] #set new num
    
    percentChange = (NewNum/OldNum)-1 #formula for percent change
    change = NewNum - OldNum #formula for change

    print("Percent Change: ", percentChange) #print line percent change
    print("Change: ", change) #print line for change

    
# Given the list above the output would be:
# Percentage Change: 0.013919983143255986
# Change: 2114
# Percentage Change: 0.015657674273616397
# Change: 2411
# Percentage Change: 0.016388201517970755
# Change: 2563
# Percentage Change: 0.018420191751176426
# Change: 2928
# Percentage Change: 0.019674581799313088
# Change: 3185
# Percentage Change: 0.01828932143527858
# Change: 3019
# Percentage Change: 0.018436771215077818
# Change: 3099
# Percentage Change: 0.017302715743602027
# Change: 2962
# Percentage Change: 0.017146236843163038
# Change: 2986
# Percentage Change: 0.016055550851045812
# Change: 2844
# Percentage Change: 0.01674084198712072
# Change: 3013
# Percentage Change: 0.015186456238524089
# Change: 2779
# Percentage Change: 0.014598618729511065
# Change: 2712
# Percentage Change: 0.014102067560469644
# Change: 2658
# Percentage Change: 0.012477699708592086
# Change: 2385
# Percentage Change: 0.01059289191116439
# Change: 2050
# Percentage Change: 0.009617744508528654
# Change: 1881
# Percentage Change: 0.009835052695017143
# Change: 1942
# Percentage Change: 0.009959929588413182
# Change: 1986
# Percentage Change: 0.012905628522481814
# Change: 2599
# Percentage Change: 0.013937367636677387
# Change: 2843
# Percentage Change: 0.011879493489728131
# Change: 2457
# Percentage Change: 0.009905200588673764
# Change: 2073
# Percentage Change: 0.00939169272841685
# Change: 1985
# Percentage Change: 0.009951158234196737
# Change: 2123
# Percentage Change: 0.009737080268257026
# Change: 2098
# Percentage Change: 0.010098224422351226
# Change: 2197
# Percentage Change: 0.010625227520931925
# Change: 2335
# Percentage Change: 0.011130372138049033
# Change: 2472
# Percentage Change: 0.011836111271914395
# Change: 2658
# Percentage Change: 0.009862471118934976
# Change: 2241
# Percentage Change: 0.009578761123652305
# Change: 2198
# Percentage Change: 0.009185717245666138
# Change: 2128
# Percentage Change: 0.008695763755817137
# Change: 2033
# Percentage Change: 0.008900667868122549
# Change: 2099
# Percentage Change: 0.009284477396143307
# Change: 2209
# Percentage Change: 0.008978357826704367
# Change: 2156
# Percentage Change: 0.009121338566752927
# Change: 2210
# Percentage Change: 0.009488791365199858
# Change: 2320
# Percentage Change: 0.0113605516593131
# Change: 2804