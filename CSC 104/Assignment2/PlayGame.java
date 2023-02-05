//Jacob Pacheco
//CSC 104-A
//Assignment 2.2.1

import java.util.*;

public class PlayGame
{
   private String player1, player2; //names of the players
   private int turn; // whose turn player1 or player2, initially 1
   private Game game; //a Game object(actually a reference to game object
   private Scanner input = new Scanner(System.in);
   
   private int numPiles;
   private int[] stickArray;  
 
   public PlayGame()
   {    
      System.out.print("Enter Player1 Name:");
      player1 = input.nextLine();
      System.out.print("Enter Player2 Name:");
      player2 = input.nextLine();
           
      System.out.print("How many piles in the game:");
      this.numPiles = input.nextInt();
      
      // make sure numPiles is not > 10
      
      this.stickArray = new int[numPiles];

      game = new Game(numPiles, this.stickArray);
   }
   
   public void play()
   {
      int pile;
      int numSticks;
      
      int player = 1;
      
      do 
      {
         if (player == 1)
            System.out.println(player1 + "'s turn");      
         else
            System.out.println(player2 + "'s turn"); 
  
         do        // keep asking until they choose a valid pile
         {
            System.out.print("Pile: ");
            pile = input.nextInt();
         } while ((pile > this.numPiles) || (pile < 0) || (game.getSticks(pile) == 0)  );
         
         do    // keep asking until they choose a valid number of sticks
         {
            System.out.print("Sticks: ");
            numSticks = input.nextInt();

         } while ((numSticks < 1) || (numSticks > game.getSticks(pile)));

         System.out.println("---------------");                                                           
         game.remove(pile, numSticks);
         game.printPiles();
         
         // if the game is over, exit the loop
         if ( game.gameOver() == true )
            break;
         
         // game is not over, swap players and loop again
         if  (player == 1)
            player = 2;
         else
            player = 1;   
      }  while (true);         
      
      if ( player == 1 )
         System.out.println(player1 + " won");      
      else
         System.out.println(player2 + " won");      
   }

   public boolean Again()
   {
      System.out.print("Play again? Y or y for Yes: ");
      char x = input.next().charAt(0);
      
      if ( (x == 'Y') || ( x == 'y') )
         return true;
      else
         return false;      
   }

   public static void main(String[] args)
   {     
      PlayGame playgame = new PlayGame();
            
      do //play the game on repeat until told otherwise
      {
         playgame.play();
      } while (playgame.Again());
   }  
}