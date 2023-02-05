//Jacob Pacheco
//CSC 211
//Assignment 2.2.1

import java.util.*;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Jack
{
   int totalVisited = 0;
  
   public static final int STATES = 49;
   public static final int MAINE = 47;   // 0 based and in the order in usmap.txt

   public static final int ADJACENT = 1;
   public static final int NOT_ADJACENT = 0;
   public static final int VISITED = 4;
  
   Map[] map = new Map[STATES];
   
   boolean mapBaseInitialized = false;
   int counter = 0;

   public Jack(String f)
   {
      int index = 0;
     
      // read file of movements
      String s1 = "";
 
      File file = new File (new File(f).getAbsolutePath());
  
      try 
      {
         Scanner sc = new Scanner(file);

         while(sc.hasNextLine()) 
         {
            s1 = sc.nextLine();
            if (s1.equals(""))
               break;

            if (!mapBaseInitialized)
            {             
               String[] a = s1.split(" ", 2); 
 
               int i = Integer.parseInt(a[0]);
               String string = a[1];
               Map m = new Map (i, string);

               map[i] = m;
               
               if (i == STATES - 1)
               {
                  mapBaseInitialized = true;
                  s1 = sc.nextLine(); //read the blank line between sections
               }
            }                 
            else     
            {
               // process each line of the Adjacent matrix body
               // each line is a string of 1 or 0
               for (int i = 0; i < STATES; i++)
               {
                  char c = s1.charAt(i);                  
                  map[index].setArrayValue(i, Integer.parseInt(String.valueOf(c)));
               }
               index++;                 
            }         
          } // while loop
         sc.close();
      }  //try loop  
      catch(FileNotFoundException e) 
      {
         e.printStackTrace();
      }
   }
   
   public void printMap()
   {
      for (int i = 0; i < STATES; i++)
      {
         System.out.print(map[i].getIndex() + "  " + map[i].getName() + "  ");
         for(int j = 0; j < STATES; j++)
           System.out.print(map[i].getArrayValue(j)); 
         System.out.println(); 
      }  
   }
   
   public int isThereAdjacentState( Map m, Stack<Map> stack)
   {
      int i = 0;
      int result = -1;
      int value = 0;
      
      Map nm;
   
      // walk the array looking for adjacencies
      for (i = 0; i < STATES; i++)
      {
         value = m.getArrayValue (i);
         if (value == ADJACENT)
         {
            // found an adjacent state; see if we've already visited it
            nm = map[i];  // get the state indicated to be adjacent     
            if (nm.getVisited() == false)  // did we visit that state
            {     
               // not visited yet...so go there  
               nm.setVisited(true);
                 
               System.out.println(counter++ + " " + nm.getName());         
 
               stack.push(nm); 
               result = i; 
               break;        
            }         
            else
            {
               // the adjacent state was already visited
               // mark the adacent in this table
               m.setArrayValue(i, VISITED);
            }
         }  // value == ADJACENT         
       } // for loop    
      return result;   
   }
 
   // Visit state statenumber
   // stateNumber is 0 based and in the order in map[]
   public void visitTheState(int stateNumber, Stack<Map> stack)
   {
      Map m;
      
      m = map[stateNumber];  // get m for THIS state
      
      if(m.getVisited() == false)
      {
         m.setVisited (true);
                 
         System.out.println(counter++ + " " + m.getName());         
      }    
      stack.push(m);        
   }
   
   public static void main(String [] args)
   {
      Map m;
      int adjacent;
 
      Jack j = new Jack ("usmap.txt"); 
            
      Stack<Map> stack = new Stack<Map>();
      
      System.out.println("The trip is as follows: ");

      //init the stack with the starting point(Maine)
      j.visitTheState(MAINE, stack);      

      while(true)
      {
         if (stack.size() == 0)
            return;
         
         m = stack.peek();
         
         if(((adjacent = j.isThereAdjacentState(m, stack)) == -1)) 
            stack.pop();
      }
   }
}