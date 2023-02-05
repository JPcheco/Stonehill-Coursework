//Jacob Pacheco
//CSC 104-A
//Assignment 7.2.4

import java.util.Random;

public class Craps implements Playgame
{
   protected int bet;

   public Craps(int b)
   { 
      bet = b;
   }

   public void play()
   {
      System.out.println("Playing Craps");
   
      Random r = new Random();
      int n = r.nextInt(2); 
      
      if(n == 0) // 0 for lose
         System.out.println("You lose " + getBet());
      else // 1 for win
         System.out.println("You win " + getBet());
   }
   
   public int getBet()
   {   
      return bet;  
   }
}