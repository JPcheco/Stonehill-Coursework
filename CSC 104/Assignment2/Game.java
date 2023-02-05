//Jacob Pacheco
//CSC 104-A
//Assignment 2.2.2

import java.util.*;

public class Game
{
   private int  numPiles;               // number of piles
   private int [] sticks = new int[10]; // an array holding the number of sticks in each pile   
   private Scanner input = new Scanner(System.in);       
      
   public Game()  // default constructor
   {
      numPiles = 3;
      
      for(int i=0; i<numPiles; i++)
      {
         sticks[i] = 10; 
      }    
   }
    
   public Game(int num, int[] array)  // two arg constructor
   {
      numPiles = num; 
      this.sticks = array;    
       
      for(int i=0; i<numPiles; i++)
      {
         System.out.print("Pile "+i+": ");
         sticks[i] = input.nextInt();
      }  
      System.out.println("---------------");     
   }

   public void remove(int pile, int numSticks) // pile is pile number, numSticks is how many to remove
   {
      int x;
      
      x = this.sticks[pile];

      if (x < numSticks)
         return;
        
      x = x - numSticks;   
      
      this.sticks[pile] = x;
   }   
   
   public boolean gameOver()
   {
      int x = 0;; 
      
      for ( int i = 0; i < numPiles; i++ )
      {        
         x = x + this.sticks[i]; 
      }
      
      if ( x == 0 )
         return true;
      else
         return false;
   }
   
   public void printPiles() //prints the pile number and the number of sticks currently in a pile
   {   
      for(int i=0; i<numPiles; i++)
      {
         System.out.println("Pile "+i+": "+ this.sticks[i] + " sticks");
      }  
      System.out.println("---------------");
   }    
   
   public int getSticks(int pile)  // retrun the number of sticks in the given pile
   {
      int x;
      
      x = this.sticks[pile];

       return x;      
   }   
}