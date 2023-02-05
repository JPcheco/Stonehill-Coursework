//Jacob Pacheco
//CSC 104-A
//Assignment 4.1.2

import java.util.*;

public class Player
{
   private int coinsInHand;
   
   private SlotMachine s;
   public Scanner input = new Scanner(System.in);

   public Player() 
   {
      s = new SlotMachine();
   }

   public Player(int x)     
   {
      s = new SlotMachine();     
   }

   public void pushBetOne() // "bets 1 coin"
   {
      int x;
      
      x = s.betOnePressed();
      
      if (x == s.NO_COINS_IN_MACHINE)
      {
         System.out.println("Not enough cash. Deposit coins first");
      }
      else if (x == s.BET_ALREADY_AT_MAX)
      {
         System.out.println("Cannot bet more than $3");
      }
      else
         System.out.println("Bet is now $" + s.getBet() );
   }  
   
   public void pushBetMax() // "bets the max of 3 coins"
   {
      int x;
      
      x = s.betMaxPressed();
   
      if (x == s.NO_COINS_IN_MACHINE)
         System.out.println("Not enough cash. Deposit coins first");
       else  
         System.out.println("Bet is now $" + s.getBet());
   }  
   
   public void pushCoinsOut () // return cash to player
   {
      System.out.println("Returning $" + s.coinOutPressed());
   }
   
   public void pushSpin() // "plays the game"
   {
      int payout;            
      
      payout = s.spin(); // spin the wheel and calculate the payout from the results
 
      if (payout == s.NO_COINS_IN_MACHINE) // get the results back from the machine
      {
        System.out.println("Not enough cash. Deposit coins first");
      }   
      else if (payout == s.NO_BET_MADE) 
      {
         System.out.println("Must make bet before spinning");
      }
      else
      {
         System.out.println("BET: " + s.getPreviousBet());
         System.out.println("PAYOFF: " + payout );
         
         System.out.println("CASH REMAINING IN MACHINE: " + s.getCoins());
      }        
   }
   
   public void insertCoins() // feed money into the slot machine to play with
   {
      int x;
   
      System.out.print("How many coins? ");
      x = input.nextInt();
      System.out.println();
     
      s.addCoins(x); // put these new coins into the machine
      
      System.out.print("$"+ x + " inserted. Cash available to bet with is now $" + s.getCoins() );
      System.out.print(";  Current bet is $" + s.getBet());
      System.out.println(";  Total coins in machine is $" + (s.getBet() + s.getCoins()));
   }

   public boolean exitTheGame() // "exits the game"
   { // return true if OK to exit; false otherwise
      boolean result = true;  
 
      if ( s.getBet() != 0 )
      { 
         System.out.println("There is an active bet; Please spin or take your coins you leave");
         result = false;
      }
      else if ( s.getCoins() != 0 )
      { 
         System.out.println("Please take your coins before you leave");
         result = false;
      }       
      return result;    
     }

   public static void main(String[] args)
   {     
      Game g = new Game();
      g.play();
      
      System.out.println("That's all, folks");
   }  
}