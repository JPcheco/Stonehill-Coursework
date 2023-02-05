// Jacob Pacheco
// CSC 211
// Assingment 2.1

import java.util.*;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Algernon
{
   public static final char START_CELL = 's';
   public static final char END_CELL = 'e';
   public static final char PATH_CELL = 'X';
  
   public static final char WALL = '1';
   public static final char OPEN = '0';

   int rows, cols;
   boolean mazeInitialized = false;

   char mazeArray[][];
   char cellArray[][];
   
   public Algernon (String f)
   {
      int r = 0;
     
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

            if (!mazeInitialized)
            {  
               String[] a = s1.split(" ", 2); 
               rows = Integer.parseInt(a[0]);
               cols = Integer.parseInt(a[1]);
                  
               mazeArray = new char[rows][cols];
               mazeInitialized = true;
            }                 
            else     
            {
               // process each line of the maze body
               // each line is a string of 1 or 0
               for (int i = 0; i < cols; i++)
               {
                  mazeArray[r][i] = s1.charAt(i);
               }
               r++;
            }
          
          } // while loop
         sc.close();
      }  //try loop   
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }
   }   
   
   //print maze
   public void printMaze()
   {
      System.out.println();
      
      for(int i = 0; i < rows; i++)
      {
         for(int j = 0; j < cols; j++)
         {
            if((mazeArray[i][j] == 's') || (mazeArray[i][j] == 'e'))
               System.out.print(mazeArray[i][j]);
            
            else if(mazeArray[i][j] == 'X')
               System.out.print(mazeArray[i][j]);
            
            else
               System.out.print(mazeArray[i][j]);
         }
         System.out.println();
      }
      System.out.println();
   }
   
   //method to mark cell
   public void markMazePath(Stack<Cell> stack)
   {
      Cell c;
      int x;
      int y;
      
      while(stack.numItems > 0)
      {
         c = stack.pop();
         x = c.getX();
         y = c.getY();
         
         if(mazeArray[x][y] == OPEN)
            mazeArray[x][y] = PATH_CELL;
      }
   }
   
   //method to find the starting cell   
   public Cell findStartingCell()
   {
      Cell C = new Cell(0, 0);
      
      for(int i = 0; i < rows; i++)
      {
         for(int j = 0; j < cols; j++)
         {
            if(mazeArray[i][j] == START_CELL)
            {
               Cell c = new Cell(i, j);
               return c;
            }
         }
      }
      
      return C;
   } 

   //method for finding end cell
   public boolean checkCellForEnd( Cell c )
   {
      boolean result = false;
      
      int x = c.getX();
      int y = c.getY();
      
      // check up
      if ( mazeArray[x-1][y] == END_CELL )
         result = true;
      // check down
      else if ( mazeArray[x+1][y] == END_CELL )
         result = true;
      // check left
      else if ( mazeArray[x][y-1] == END_CELL )
         result = true;
      // check right
      else if ( mazeArray[x][y+1] == END_CELL )
         result = true;
         
      return result;
   }

   //method to check right for open space
   public boolean checkRightForOpenSpace (Cell c, Stack<Cell> stack)
   {
      boolean result = false;
      
      int x = c.getX();
      int y = c.getY() + 1;
   
      // check the up position on the top
      if ( mazeArray[x][y] == OPEN )
      {
         //stack.push (new Cell(x,y));
         if ( pushOnlyUnique(new Cell(x,y), stack) )
         {
            stack.peek().setLeft(true);
            result = true;
         }
         else
         {
            stack.peek().setRight(true);
            result = false;
         }
      } 
        
      return result;
   }

   //method to check left for open space
   public boolean checkLeftForOpenSpace (Cell c, Stack<Cell> stack)
   {
      boolean result = false;
      
      int x = c.getX();
      int y = c.getY() - 1;
   
      // check the up position on the top
      if ( mazeArray[x][y] == OPEN )
      {
         //stack.push (new Cell(x,y));
         if ( pushOnlyUnique(new Cell(x,y), stack) )
         {
            stack.peek().setRight(true);
            result = true;
         }
         else
         {
            stack.peek().setLeft(true);
            result = false;
         }   
      } 
        
      return result;
   }

   //method to check up for space
   public boolean checkUpForOpenSpace (Cell c, Stack<Cell> stack)
   {
      boolean result = false;
      
      int x = c.getX() - 1;
      int y = c.getY();
   
      // check the up position on the top
      if ( mazeArray[x][y] == OPEN )
      {
         //stack.push (new Cell(x,y));
         if ( pushOnlyUnique(new Cell(x,y), stack) )
         {
            stack.peek().setDown(true);
            result = true;
         }
         else
         {
            stack.peek().setUp(true);
            result = false;
         }
      } 
        
      return result;
   }

   //method to check down for space
   public boolean checkDownForOpenSpace (Cell c, Stack<Cell> stack)
   {
      boolean result = false;
      
      int x = c.getX() + 1;
      int y = c.getY();
   
      // check the up position on the top
      if ( mazeArray[x][y] == OPEN )
      {
         //stack.push (new Cell(x,y));
         if ( pushOnlyUnique(new Cell(x,y), stack) )
         {
            stack.peek().setUp(true);
            result = true;
         }
         else
         {
            stack.peek().setDown(true);
            result = false;
         }   
      } 
        
      return result;
   }

   
   public boolean pushOnlyUnique (Cell c, Stack<Cell> stack)
   {
      Cell temp;
      boolean duplicate = false;
      boolean result = true;  // true = item added to stack; false = not added
   
      // only allow unique cells back on the stack
      Stack<Cell> s1 = new Stack<Cell>();
      
      while ( stack.size() > 0 )
      {
         temp = stack.pop();
         s1.push(temp);

         if ( (temp.getX() == c.getX()) && (temp.getY() == c.getY()) )
            duplicate = true;
      }
            
      // now copy back to the original stack
      while ( s1.size() > 0 )
      {
         temp = s1.pop();
         stack.push(temp);  
      }
      
      // if the item attempting to add is already on the stack, disallow...
      // that and return a false
      if (duplicate == true)
      {  
         result = false;
      }
      else
      {
         stack.push(c);
         result = true;
      }   

      return result;
   }   
   
   public static void main(String[] args)
   {
      Cell c;
      
      System.out.print("Algernon start");
      
      Algernon a = new Algernon("maze.txt");
      a.printMaze();
      
      Stack<Cell> stack = new Stack<Cell>();
      
      System.out.print("Looking for a way out");
      c = a.findStartingCell();
      
      //init the stack with the starting position
      stack.push(c);
      
      while (true)
      {
         // look at the item at the top of the stack
         c = stack.peek();
         
         // check to see if we're done
         if (a.checkCellForEnd(c) == true)
         {
            // check for end
            a.markMazePath(stack);
            a.printMaze();
            return;
         }
         else
         {
            // check the item at top of the stack...
            // if not checked this cell's UP position yet, do so now
            if(c.getUp() == false)
            {
               c.setUp(true);
               // if UP is open...create a cell and push onto stack
               if (a.checkUpForOpenSpace(c, stack) == true)
                  continue;
            }
            
            if(c.getRight() == false)
            {
               c.setRight(true);
               // if Right is open...create a cell and push onto stack
               if (a.checkRightForOpenSpace(c, stack) == true)
                  continue;
            }  
                     
            if(c.getDown() == false)
            {
               c.setDown(true);
               // if Down is open...create a cell and push onto stack
               if (a.checkDownForOpenSpace(c, stack) == true)
                  continue;
            }
            
            if(c.getLeft() == false)
            {
               c.setLeft(true);
               // if left is open...create a cell and push onto stack
               if (a.checkLeftForOpenSpace(c, stack) == true)
                  continue;
            }
            
            // If about to pop off the last item, we could not find 
            // a way out
            if  (stack.size() == 1)
            {
               // check if we used up all cells
               System.out.println("No way out");
               return;
            }            
            c = stack.pop();                   
         }             
      }
   }
}