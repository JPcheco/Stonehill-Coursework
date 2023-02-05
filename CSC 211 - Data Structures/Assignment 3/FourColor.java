//Jacob Pacheco
//CSC 211
//Assignment 3.1.1

import java.util.*;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class FourColor
{
   int totalVisited = 0;
  
   public static final int STATES = 49;
   public static final int ADJACENT = 1;   
   public static final int BLANK  = 0;

   Map[] map = new Map[STATES];
   
   Stack<Map> stack = new Stack<Map>();
   
   boolean mapBaseInitialized = false;
   int counter = 0;

   public FourColor(String f)
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
   
   public void examineState(int state )
   {
      int i = state; 
      
      if(state == STATES) //map[i].getIndex()
      {
         printSolution();
         System.exit(0);
      }      
      else
      {
         Map m = map[i];
         
         for(int color = 1; color < 5; color++)
         {
            if( isThereAdjacentState(m, color) == true ) 
            {                                  
               colorState(i, color);
               examineState(state + 1); //recursive call with the next state in the index
               
               uncolorState(i, color); //backtrack
            }
         } 
      }         
   }
   
   public void colorState(int i, int color)
   {
      Map m;
      m = map[i];
      m.setColor(color); //colors the state
      
      stack.push(m);
   }
   
   public void uncolorState(int i, int color)
   {
      Map m;
      m = map[i];
      m.setColor(BLANK);
      
      stack.pop();
   }
   
   public boolean isThereAdjacentState(Map m,  int color)
   {
      int i = 0;
      boolean result = false;
      int value = 0;
      
                  // one map for the current state
      Map nm = m; // one for the adjecent
   
      // walk the array looking for adjacencies
      for(i = 0; i < STATES; i++)
      {
         value = m.getArrayValue(i);
         if(value == ADJACENT)
         {
            // found an adjacent state;
            nm = map[i];  // get the state indicated to be adjacent     
           
            if(nm.getColor() == 0)  //if the state blank 
               result = true;
            else if(nm.getColor() == color)
               return result = false;
            else 
               result = true;
                              
         }  // value == ADJACENT         
       } // for loop       
                  
      return result;   
   }
   
   public void printSolution()
   {
      for (int i = 0; i < STATES; i++)
         System.out.println(map[i].getIndex() + "  " + map[i].getName() + "  " + convertToColor(i) );  
   }
   
   public String convertToColor(int i)
   {
      String name = "";
      
      int number;      
      number = map[i].getColor();
      
      switch(number)
      {
         case 1:
            name = "BLUE";
            break;
         case 2:
            name = "RED";
            break;
         case 3:
            name = "GREEN";
            break;
         case 4:
            name = "YELLOW";
            break; 
      }
      return name;
   } 
   
   public static void main(String [] args)
   {
      Map m;
 
      FourColor j = new FourColor("usmap.txt"); 
            
      // Stack<Map> stack = new Stack<Map>();
      
      System.out.println("The map is colored as follows: ");      
      
      m = j.map[0];
      j.stack.push(m); // initialize stack with minnesota
      
      j.examineState(0); //start coloring with index 0(minnesota)
      j.printSolution();      
   }
}