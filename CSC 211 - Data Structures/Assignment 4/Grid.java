// Jacob Pacheco
// CSC 211
// Assingment 4.1.1

import java.util.*;
import java.io.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class Grid
{
   public static final char BLACK_CELL = 'b';
   public static final char WHITE_CELL = 'w';

   int rows;
   int cols;   
   int cnt = 0;
   
   Cell cellArray[][];
   
   public Grid(String f)
   {
      boolean mazeInitialized = false;
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
               
            //reads in size of arra
            if (!mazeInitialized)
            {  
               String[] a = s1.split(" ", 2); 
               rows = Integer.parseInt(a[0]);
               cols = Integer.parseInt(a[1]);
                  
               cellArray = new Cell[rows][cols];
               mazeInitialized = true;
            }
                             
            else     
            {
               // process each line of the maze body
               // each line is a string of b or w
               for (int i = 0; i < cols; i++)
               {
                  cellArray[r][i] = new Cell(r, i, s1.charAt(i));
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
   
   public void processCell(int x, int y, int counter)
   {
      Cell c = cellArray[x][y];
      
      if(c.getValue() == BLACK_CELL)
         return;
      
      if(c.getValue() == WHITE_CELL)
      {
         c.setValue(convertToChar(counter));
         processCell(x, y, counter);
      }
         
      if(c.getUp() == false)
      {
         c.setUp(true);
         processCell(x-1, y, counter); // up
      }
      
      if(c.getDown() == false)
      {
         c.setDown(true);
         processCell(x+1, y, counter); // down
      }
      
      if(c.getLeft() == false)
      {
         c.setLeft(true);
         processCell(x, y-1, counter); // left
      }
      
      if(c.getRight() == false)
      {
         c.setRight(true);
         processCell(x, y+1, counter); // right
      }
   }
   
   public void Counter(int x, int y)
   {
      Cell c = cellArray[x][y];
      
      if(c.getValue() != WHITE_CELL)
         return;
         
      int counter = cnt++;
      
      processCell(x, y, counter);
   }

   public char convertToChar(int i)
   { 
      String str = String.valueOf(i);
 
      return str.charAt(0);
   }
   
   public int countNumberedCells(char cnt)
   {
      int count = 0;
   
      for(int i = 0; i < rows; i++)
      {
         for(int j = 0; j < cols; j++)
         {
            Cell c = cellArray[i][j];
            if (c.getValue() == cnt)
               count++;
         }
      }
      return count;   
   }
   
   public void displayResult()
   {
      int count = 0;
      int x;
      
      for(int i = 0; i < 9; i++)
      {
         if(countNumberedCells(convertToChar(i)) > 0)
            count++;
      }
      
      System.out.println("There are " + count + " groups of white cells");
      System.out.print("There sizes are: ");
      for(int i = 0; i < count; i++)
      {
         x = countNumberedCells(convertToChar(i));
         System.out.print(x + " ");
      }      

      System.out.println();
   }
   
   public static void main(String[] args)
   {      
      Grid g = new Grid("grid.txt");     
      
      for(int i = 0; i < g.rows; i++) //runs through rows
      {
         for(int j = 0; j < g.cols; j++) //runs through cols
         {
            g.Counter(i, j);
         }
      }
      
      g.displayResult();
   }
}