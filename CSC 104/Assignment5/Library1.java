//Jacob Pacheco
//CSC 104-A
//Assignment 5.3.1

import java.util.*;

public class Library1
{   
   private Media1 [] media;
   
   public Library1() // constructor
   {
      media = new Media1[5]; // create a Media array of size 5     
      
      // 2 books , 2 DVDs and 1 magazine      
      Book1 b1 = new Book1();  
      b1.setTitle("Book 1");
      b1.setAuthor("Author 1");
            
      setMedia(0, b1); 
      
      Book1 b2 = new Book1();
      b2.setTitle("Book 2");
      b2.setAuthor("Author 2");
      
      setMedia(1, b2);
      
      DVD1 d1 = new DVD1();
      d1.setTitle("DVD 1");
      d1.setRuntime(120);
      
      setMedia(2, d1);
      
      DVD1 d2 = new DVD1();
      d2.setTitle("DVD 2");
      d2.setRuntime(180);
      
      setMedia(3, d2);
      
      Magazine1 m1 = new Magazine1();
      m1.setTitle("Magazine 1");
      m1.setIssue(1);
      
      setMedia(4, m1);
   }
   
   public void setMedia(int i, Media1 m)
   {
      media[i] = m;
   }
   
   public Media1 getMedia(int i)
   {
      return media[i];
   }
   
   public static int getChoice() //process the choice of the user
   {
      Scanner input = new Scanner(System.in); // instantiate a Scanner
      
      int choice;
      
      while(true)
      {         
         choice = input.nextInt();
         if(choice == 999) //only other choice allowed
            break;
         else if((choice <= 4) && (choice >= 0)) // only allows 0-4
            break;
         System.out.println("Enter number 0-4 (Enter 999 to exit): ");
      }
      return choice;
   }
   
   public static void main(String[] args)
   {
      int choice, i = 0;
            
      Library1 L = new Library1(); //instantiate a Library object
      
      // prompt the user for an array index 0-4, i
      System.out.println("Enter number 0-4 (Enter 999 to exit): "); 
      
      while(true) // loop until user enters 999 for the index
      {
         choice = getChoice();
         if(choice == 999) // if choice is 999 then break out of while loop
            break;
         
         // pass the Media object at i to information
        System.out.println( L.getMedia(choice) );

         // prompt for another array index
         System.out.println("Enter another number 0-4 (Enter 999 to exit): ");
      }
   }
}