//Jacob Pacheco
//CSC 104-A
//Assignment 7.2.5

import java.util.Scanner;

public class TestGame
{
   int bet;
   
   public String menu()
   {
      char ch;

      Scanner keyboard = new Scanner(System.in);

      System.out.print("Enter P,B or C -- anything else to quit: ");
   
      ch = keyboard.next().charAt(0);
      ch = Character.toUpperCase(ch);
    
      return String.valueOf(ch);
    }

   public void performAction(String choice)
   {    
      if (choice.equals("P"))
      {        
         bet = getBetFromUser();
         Playgame game = new Poker(bet);
         game.play();
      }
      else if (choice.equals("B"))
      {
         bet = getBetFromUser();
         Playgame game = new Blackjack(bet);
         game.play();            
      }
      else if (choice.equals("C"))
      {
         bet = getBetFromUser();
         Playgame game = new Craps(bet);
         game.play();
      }
   }
   
   public int getBetFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter Bet: ");
      bet = keyboard.nextInt();
      
      if(bet < 0)
         System.out.println("Insufficient bet");
         
      return bet;
   }

   public static void main(String [] args)
   {
      TestGame t = new TestGame();
      String choice;
      do
      {        
         choice = t.menu();
         t.performAction(choice);
         System.out.println();
      } while (choice.equals("P") || choice.equals("B") || choice.equals("C"));
      
      System.out.print("Bye");
   }
}