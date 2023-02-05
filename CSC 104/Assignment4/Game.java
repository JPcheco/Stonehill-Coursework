//Jacob Pacheco
//CSC 104-A
//Assignment 4.1.3

import java.util.*;

public class Game
{
   private Player player;
   
   public Scanner input = new Scanner(System.in);

   public int presentMenu()
   {
      int choice;
      
      System.out.println();
      System.out.println("Choose one of the following");
      System.out.println("1: Push BetOne");
      System.out.println("2: Push BetMax");
      System.out.println("3: Push Spin");
      System.out.println("4: Insert Coins");
      System.out.println("5: Push CoinOut");
      System.out.println("6: Exit");
      System.out.print("? ");
      
      choice = input.nextInt();
      System.out.println();
      
      return choice;
   }
   
   public boolean processChoice( int choice, Player p )
   {    
      boolean result = false; // true only if it's OK to exit
     
      switch(choice)
      {
         case 1:  p.pushBetOne(); break; 
         case 2:  p.pushBetMax(); break; 
         case 3:  p.pushSpin(); break; 
         case 4:  p.insertCoins(); break;
         case 5:  p.pushCoinsOut(); break;         
         case 6:  result = p.exitTheGame(); break;
         default: 
            System.out.println("Please choose a  valid menu option");
            result = false;
            break;
      }      
      return result;      
   } 
   
   public void play()
   {
      boolean done;
      int choice;
      
      Player p = new Player();
      
      do
      {
         choice = presentMenu();          // presents a menu to the player
         done = processChoice(choice, p); // carries out the choice of the player
      } while(!done);                     // checks if it's OK to exit
   }
}