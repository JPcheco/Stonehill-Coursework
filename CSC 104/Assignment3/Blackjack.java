//Jacob Pacheco
//CSC 103-A
//Assignment 3.1

import java.util.*;

public class Blackjack
{
    private Card[] dealerCards; // an array of Card objects
    private Card[] playerCards; // an array of Card objects
    Deck deck; //We did this in class

    private int dealerNumCards; // the number of cards the dealer has
    private int playerNumCards; //the number of cards the player has
   
    private Scanner input = new Scanner(System.in);

    public Blackjack() // constructor
    {
       dealerCards = new Card[15]; // 15 cards is much more than you will need
       playerCards = new Card[15];
       dealerNumCards = playerNumCards = 0;
       deck = new Deck();
    }

    public void play()
    {
      int choice;
      int playerScore;
      int dealerScore;
    
      for(int i = 0; i < 2; i++)
          {
          playerCards[i] = deck.dealCard();
          dealerCards[i] = deck.dealCard();

          playerNumCards++;
          dealerNumCards++;
          } 

      // show the player cards
      System.out.println();
      System.out.println("Your first cards are");       
      showHand (playerCards, playerNumCards);

      // show only one dealer card
      System.out.println("Dealer shows one card");
      showHand (dealerCards, 1);
      
      System.out.println("Your turn: Type 0 for a hit, any other number to stand: ");
      choice = input.nextInt();
      System.out.println();               

      do
      {      
         if(choice == 0)
         {
            System.out.println("Your card is ");
         
            playerCards[playerNumCards++] = deck.dealCard();
            System.out.println(playerCards[(playerNumCards-1)].getName());
            System.out.println();            
         }
         
         System.out.println("Your hand is now ");
         showHand (playerCards, playerNumCards);      
         
        playerScore = score(playerCards, playerNumCards);
         if ( playerScore > 21 )
            break;
         
         System.out.println("Another? Type 0 for a hit: ");
         choice = input.nextInt();
         
      } while(choice == 0);
      System.out.println();
      
       if ( playerScore > 21 )   
         dealerWins();

       else if ( playerScore == 21 )
         playerWins();  
 
       else
       {
         do // dealer turn; dealer needs to check
         {
            showHand (dealerCards, dealerNumCards);
            dealerScore = score(dealerCards, dealerNumCards);
                      
            if ( dealerScore > 21 ) // if the dealer busts, the player wins
               {
               playerWins();
               break;
               }
  
            else if ( dealerScore >= 17 ) // if the dealer is 17 or greater, take no more cards
               {   
               // card handling is done...compare scores and figure out a winner
               if ( playerScore > dealerScore )
                  playerWins();
               else if ( dealerScore > playerScore )
                  dealerWins();
               else
                  System.out.println("Tie");                 
               break;   
               }            
            else // Dealer takes another card if 16 or less
            {
               dealerCards[dealerNumCards++] = deck.dealCard();
            }             
         } while (true);  // dealer do loop turn       
       }  // else of dealer turn
                 
      System.out.println("Game Over");
    }  // end of play()
    
    
    public void playerWins ()
    {
      System.out.println("Player Wins");
    }
    
    public void dealerWins ()
    {
      System.out.println("Dealer Wins");
    }
    
    public void showHand ( Card[] c, int num ) // display each card in the hand
    {
      for(int i=0; i < num; i++)
         System.out.println(c[i].getName());     
          
       System.out.println();
    }        
    
    public int score (Card[] c, int num) // returns the score of a hand containing num cards
    {
       // c is a hand
       // num is the number of cards in the hand
         int score = 0;
         boolean gotAce = false;
       
         for(int i=0; i<num; i++)
         {
            int x = c[i].getRank();
            
            //need to separate out face cars values to be 10
            if  (x > 10)
               x = 10;    // Jack, Quune, and King are all valued at 10
            
            // special case for handling the first ace only.  
            if ( (gotAce == false) && (x == 1) )
               gotAce = true;
            else               
               score = score + x;   
         }

         // if we had an ace, check for it now.  Aces are special and can be either 1 or 11
         if ( gotAce == true) 
         {
            if ( (score + 11) <= 21 )
               score = score + 11;
            else 
               score = score + 1;    
         }

       System.out.println("Score = "+score);

       return score;
    }
   
    public static void main(String[] args)
    {
       Blackjack bj = new Blackjack();
       bj.play();
    }
}