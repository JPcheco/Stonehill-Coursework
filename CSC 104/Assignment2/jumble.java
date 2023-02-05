//Jacob Pacheco
//CSC 104-A
//Assignment 2.1

import java.util.*;

public class jumble
{
  public static String jumble(String word)
  {
     int index = 0;
     char charIndex = ' ';
     char charBase = ' ';    
     
     StringBuilder sb = new StringBuilder(word);
     
     Random r = new Random();
     
     for (int i=0; i<10; i++)
     {
         index = r.nextInt(word.length()); //finds a random #from 0-the words length
         
         charIndex = sb.charAt(index);  //index becomes a character derived from the randomness
         charBase  = sb.charAt(0);       //sets the first letter to a chracter called base        
         
         sb.setCharAt(0, charIndex);     //swaps the first letter with the ran
         sb.setCharAt(index, charBase);  //swaps the random letter with the initial
        
         // System.out.println(sb.toString());  //debug purposes
     }
     return sb.toString();
  }

  public static void main(String[] args)
  {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a word use XXX to end the program: ");
      String word = input.nextLine();
      
      while (!word.equals("XXX"))
      {
         System.out.println("The jumbled word is "+jumble(word));
         System.out.print("Enter a word use XXX to end the program: ");
         word = input.nextLine();
      }
   }  
}