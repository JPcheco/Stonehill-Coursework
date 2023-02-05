//Jacob Pacheco
//CSC 104-A
//Assignment 4.1.1

 /***************************************************************************
    Here are the 6 unique symbols and the values that represent them
    -------------------------------------------------------------------
    Value   Symbol
      0     777
      1     7
      2     $$$
      3     $$
      4     $
      5     @
          
      Algorithm to use for wheel spin distributions
      ---------------------------------------------  
      0 -- display 777
      1 or 2 -- display a 7
      3, 4, or 5 -- display $$$
      6, 7,8, or 9 -- $$
      10,11,12,13, or 14 -- $
      15, 16, or 17 -- @ (cherry)
        
      Payout Chart
      ---------------------------------------------
      0     777   777   777      200      three triple seven
      1     7     7     7        50       three single seven
      2     $$$   $$$   $$$      30       three triple dollar
      3     $$    $$    $$       20       three double dollar
      4     $     $     $        15       three single dollar
      5     @     @     @        10       three cherries
               @     @           5        any 2 cherries
                  @              2        any 1 cherries
    *******************************************************************************/ 

import java.util.*;

public class SlotMachine
{      
   private int[] wheel;
   
   private static final int NUM_OF_WHEELS  = 3;
   private static final int MAX_BET_SIZE   = 3;  
   
   private static final int TRIPLE_SEVENS  = 0;
   private static final int SINGLE_SEVEN   = 1;
   private static final int TRIPLE_DOLLAR  = 2;
   private static final int DOUBLE_DOLLAR  = 3;
   private static final int SINGLE_DOLLAR  = 4;
   private static final int CHERRIES       = 5;
  
   private static final int THREE_TRIPLE_SEVENS_PAYOFF  = 200;
   private static final int THREE_SINGLE_SEVEN_PAYOFF   = 50;
   private static final int THREE_TRIPLE_DOLLAR_PAYOFF  = 30;
   private static final int THREE_DOUBLE_DOLLAR_PAYOFF  = 20;
   private static final int THREE_SINGLE_DOLLAR_PAYOFF  = 15;
   private static final int THREE_CHERRIES_PAYOFF       = 10;
   private static final int TWO_CHERRIES_PAYOFF         = 5;
   private static final int ONE_CHERRIES_PAYOFF         = 2;
   private static final int NO_PAYOFF                   = 0;
   
   // some error checking values   
   public static final int NO_COINS_IN_MACHINE = 9000;
   public static final int NO_BET_MADE = 9001;
   public static final int BET_ALREADY_AT_MAX = 9002;
   public static final int NO_ERRORS = 9003;
      
   private int coins;      // number of coins available to bet
   private int bet;        // number of coins in the current bet
                           // Note: coins + bet = total number of coins in the machine
   
   private int previousBet;   // hold the bet teh player just one (for disply purposes)
     
   public SlotMachine()
   {
      wheel = new int[NUM_OF_WHEELS];
   
      setCoins(0);
      setBet(0);
   }
   
   public int getCoins()
   {
       return coins;
   }

   private void setCoins(int x)
   {
      if (x < 0) // range checking
         x = 0;
   
       coins = x;
   }

   public int getBet()
   {
       return bet;
   }

   private void incrementBet()
   {
      int x; 
      
      x = getBet();
      x++;
      setBet(x);
   }

   private void decrementCoins()
   {
      subtractCoins(1);
   }

   private void setBet(int x)
   {
      if (x < 0) // some basic range checking
         x = 0;
      if (x > MAX_BET_SIZE)
         x = MAX_BET_SIZE;   
   
       bet = x;
   }

   private void setPreviousBet(int x)
   {
      previousBet = x;
   }

   public int getPreviousBet()
   {
      return previousBet;
   }

   public void addCoins(int x) // adds new coins into the machine
   {
      int y;
      
      y = getCoins();
      y = y + x;
      setCoins(y);   
   }
   
   public void subtractCoins(int x) //subtracts coins from the machine
   {
      int y;
      
      y = getCoins();
      y = y - x;
      setCoins(y);   
   }   

   public int coinOutPressed() // get all the coins from the machine, including those currently bet
   {
      int b,c;
      int total;
      
      c = getCoins();
      b = getBet();
      
      total = b + c;
      
      setCoins(0);
      setBet(0);
            
      return total;
   }

   public int betOnePressed()
   {
      if (getCoins() == 0)
         return NO_COINS_IN_MACHINE;
    
      if (getBet() == MAX_BET_SIZE)
         return BET_ALREADY_AT_MAX;
         
      incrementBet();
      decrementCoins();   
      
      return NO_ERRORS;
   }

   public int betMaxPressed()
   {
      int max;
      int c, b;
   
      c = getCoins();
      b = getBet();
   
      if (c == 0)
         return NO_COINS_IN_MACHINE;

      max = MAX_BET_SIZE - b;
               
      for (int i = 0; i < max; i++)
      {
         if (getCoins() > 0) // player is trying to bet 1...check if the coins are there to do it with
          {   
            incrementBet();  // increase the bet
            decrementCoins(); // decrease the number of available coins to bet
         }   
      }      
      return NO_ERRORS;
   }

   private int displayMatchPayout(int x)
   {
      int y;
   
      switch (x)
      {
         case TRIPLE_SEVENS:  y = THREE_TRIPLE_SEVENS_PAYOFF; break;
         case SINGLE_SEVEN:   y = THREE_SINGLE_SEVEN_PAYOFF; break;
         case TRIPLE_DOLLAR:  y = THREE_TRIPLE_DOLLAR_PAYOFF; break;
         case DOUBLE_DOLLAR:  y = THREE_DOUBLE_DOLLAR_PAYOFF; break;
         case SINGLE_DOLLAR:  y = THREE_SINGLE_DOLLAR_PAYOFF; break;
         case CHERRIES:       y = THREE_CHERRIES_PAYOFF; break;
         default:             y = NO_PAYOFF; break;
      }   
      return y;   
   }   
   
   private int calculateSpinResults()
   {   
      int payoff = 0;
      int x = 0;     

      if ((wheel[0] == wheel[1]) && (wheel[1] == wheel[2])) // check for 3 matches
      {
         payoff = displayMatchPayout(wheel[0]); // all 3 wheels match
      }
      
      else // count the number of cherries
      {
         for (int i = 0; i < 3; i++)
         {
            if ( wheel[i] == 5 )
               x++;   
         }         
         if (x == 2)
            payoff = TWO_CHERRIES_PAYOFF;
         else if ( x == 1 )
            payoff = ONE_CHERRIES_PAYOFF;   
         else
            payoff = NO_PAYOFF;
      }     
      return payoff;
   }      

   private void displayWheelResults(int x)
   {   
      switch (x)  
      {
         case TRIPLE_SEVENS:  System.out.print("777 "); break;
         case SINGLE_SEVEN:   System.out.print("7 "); break;
         case TRIPLE_DOLLAR:  System.out.print("$$$ "); break;
         case DOUBLE_DOLLAR:  System.out.print("$$$ "); break;
         case SINGLE_DOLLAR:  System.out.print("$ "); break;
         case CHERRIES:       System.out.print("@ "); break;
         default:             System.out.print("UNKNOWN "); break;
      }
   }     
   
   private int spinWheel ()
   {
      int x;
      
      Random rand = new Random(); // create instance of Random class 
  
      int n = rand.nextInt(18); // Generate random integers in range 0 to 17
 
      switch(n) // distribute the random numbers as shown above
      {
         case 0: 
            x = 0; break;
         case 1:  case 2:    
            x = 1; break;
         case 3:  case 4: case 5:    
            x = 2; break;
         case 6:  case 7: case 8: case 9:    
            x = 3; break;
         case 10:  case 11: case 12: case 13: case 14:   
            x = 4; break;
         case 15:  case 16: case 17:   
            x = 5; break;
         default:
            x = 999; break;   
      }
      return x;
   }   
   
    public int spin()
    {
      int x, y;

      if ((getBet() == 0) && (getCoins() == 0)) // if no bet and no cash in machine, user is out of money
         return NO_COINS_IN_MACHINE;
      
      if (getBet() == 0) // there is at least SOME coins in the system. check if the has not made a bet
          return NO_BET_MADE;

      // a valid bet was made...do the spin
      System.out.println("S L O T M A C H I N E");      

      for (int i = 0; i < NUM_OF_WHEELS; i++)
      {
         wheel[i] = spinWheel();
         displayWheelResults(wheel[i]);
      }
      System.out.println();
      System.out.println();

      x = calculateSpinResults(); // calculate the base payoff level
      
      y = x * getBet(); // multiply by the bet amount to get the actual payoff amount
    
      addCoins(y); // add the payoff back into the user account
    
      setPreviousBet(getBet()); // save the current bet as previous bet (for display purposes) then clear the bet
      setBet(0);
    
      return y;   
    }  
}