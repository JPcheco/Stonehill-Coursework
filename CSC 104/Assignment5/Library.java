//Jacob Pacheco
//CSC 104-A
//Assignment 5.2.2

import java.util.*;

public class Library
{   
   private Media [] media;
   
   public Library() // constructor
   {
      media = new Media[5]; // create a Media array of size 5     
      
      // 2 books , 2 DVDs and 1 magazine      
      Book b1 = new Book();  
      b1.setTitle("Book 1");
      b1.setAuthor("Author 1");
            
      setMedia(0, b1); 
      
      Book b2 = new Book();
      b2.setTitle("Book 2");
      b2.setAuthor("Author 2");
      
      setMedia(1, b2);
      
      DVD d1 = new DVD();
      d1.setTitle("DVD 1");
      d1.setRuntime(120);
      
      setMedia(2, d1);
      
      DVD d2 = new DVD();
      d2.setTitle("DVD 2");
      d2.setRuntime(180);
      
      setMedia(3, d2);
      
      Magazine m1 = new Magazine();
      m1.setTitle("Magazine 1");
      m1.setIssue(1);
      
      setMedia(4, m1);
   }
   
   public void setMedia(int i, Media m)
   {
      media[i] = m;
   }
   
   public Media getMedia(int i)
   {
      return media[i];
   }
    
   public static void information(Media m) // prints out the info for the media array
   {
      if(m instanceof Book)
      {
         System.out.println("Title is " + ((Book)m).getTitle());
         System.out.println("Author: " + ((Book)m).getAuthor());
      }
      else if(m instanceof DVD)
      {
         System.out.println("Title is " + ((DVD)m).getTitle());
         System.out.println("Runtime is "+((DVD)m).getRuntime()+" minutes long");
      }
      else if(m instanceof Magazine)
      {
         System.out.println("Title is " + ((Magazine)m).getTitle());
         System.out.println("Issue #" + ((Magazine)m).getIssue());
      }
      else
         System.out.println("No such Media found");
   }
   
   public static int getChoice() // process the choice of the user
   {
      Scanner input = new Scanner(System.in); // instantiate a Scanner
      
      int choice;
      
      while(true)
      {         
         choice = input.nextInt();
         if(choice == 999) // allows 999
            break;
         else if((choice <= 4) && (choice >= 0)) //allows 0-4
            break;
         System.out.println("Enter number 0-4 (Enter 999 to exit): ");
      }
      return choice;
   }
   
   public static void main(String[] args)
   {
      int choice, i = 0;
            
      Library L = new Library(); //instantiate a Library object
      
      // prompt the user for an array index 0-4, i
      System.out.println("Enter number 0-4 (Enter 999 to exit): "); 
      
      while(true) // loop until user enters 999 for the index
      {
         choice = getChoice();
         if(choice == 999) //break out of while loop if input is 999
            break;
         
         // pass the Media object at i to information
         information( L.getMedia(choice) );
         
         // prompt for another array index
         System.out.println("Enter another number 0-4 (Enter 999 to exit): ");
      }
   }
}