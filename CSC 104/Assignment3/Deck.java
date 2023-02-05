import java.util.*;
public class Deck
{
  private Card[] deck;
  private int cardsRemaining; // afte a card is dealt
  private int nextCard; // to be dealt
  
  public Deck()
  {
    deck = new Card[52];
    
    // i/13  is a number from 0 to 3--> the suit
    // i%13 + 1 is a number from 1 to 13 --> the rank
    for (int i = 0; i < 52; i++)
       deck[i] = new Card(i/13, i%13+1);
    
    cardsRemaining = 52;
    nextCard = 0;   
    shuffle();
  }
  
   public void shuffle()
  {
    Random r = new Random ();
    for (int i = 0; i < 52; i++)
    {
      int randomPlace = r.nextInt(52);
      //swap deck[i] with deck[randomPlace]
      Card temp = deck[i];
      deck[i] = deck[randomPlace];
      deck[randomPlace] = temp;
    }
    cardsRemaining = 52;
    nextCard = 0;
  }
  
  public Card dealCard()
   {
     // returns the card at the top of the deck (nextCard)
     if (cardsRemaining == 0)
     {
          System.out.println("Deck was re-shuffled");
          shuffle();
      }
      
      Card c = deck[nextCard];
      nextCard++;
      cardsRemaining--;
      return c;    
   }
}         