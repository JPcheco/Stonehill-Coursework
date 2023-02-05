//Jacob Pacheco
//CSC 104-A
//Assignment 8.3.1

import java.util.*;
import java.util.ArrayList;

public class Shuffle
{
   protected ArrayList<Integer> list = new ArrayList<Integer>();
   
   //Make a class Shuffle.java with instance variable, list, an ArrayList of Integer.
   
   public Shuffle() //default constructor
   {
      //The default constructor should initialize list with the numbers 1- 10, inclusive.
      for(int i = 1; i<11; i++)
         list.add(i);
   }
   
   public Shuffle(int n) //one arguement constructor
   {
      //should initialize list with the numbers 1 – n, inclusive
      for(int i = 1; i<(n+1); i++)
         list.add(i);
   }
   
   public void displayList()
   {
      //that prints the contents of the list
      for ( int i = 0; i < list.size(); i++ )
         System.out.print(list.get(i) + " ");
   
      System.out.println();
   }
   
   public void shuffleList()
   {
      Random r = new Random();
      int size = list.size();
      
      for (int i = 0; i < size; i++)
      {
         int randomPlace = r.nextInt(size);
         
         int temp = list.get(i);
         list.set(i, list.get(randomPlace) );
         list.set(randomPlace, temp);
       }
   }
   
   public static void main(String[] args)
   {
      Shuffle list1 = new Shuffle(); // Creates a list list1 using the default constructor
      System.out.print("Original list: ");
      list1.displayList();
      System.out.print("Randomized list: ");
      list1.shuffleList();
      list1.displayList(); // Shuffles the list & Displays the shuffled list
      
      Shuffle list2 = new Shuffle(15); // Creates a list list2 of size 15
      System.out.print("Original list: ");
      list1.displayList();
      System.out.print("Randomized list: ");
      list2.shuffleList();
      list2.displayList(); // Shuffles the list & Displays the shuffled list
   }
}