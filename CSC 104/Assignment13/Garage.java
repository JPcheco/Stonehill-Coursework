//Jacob Pacheco
//CSC 104-A
//Assignment 13.2.1

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Garage
{
   protected static final int MAX_SIZE = 100;
   protected static final int FULL = 8;   
   
   Stack <Car> garageSpaces = new Stack <Car>(FULL);
   Stack <Car> tempSpaces = new Stack <Car>(FULL);

   Stack <Command> cmd = new Stack <Command>(100);
   Stack <Command> t = new Stack <Command>(100);

   public Garage( String f )
   {
      // read file of movements
      String s1 = "";
 
     File file = new File (new File(f).getAbsolutePath());
     //if ( file.exists() == true )
     //    System.out.println(file + " exists");
     //else     
     //    System.out.println(file + " does not exist");
  
     try 
     {
        Scanner sc = new Scanner(file);

         while (sc.hasNextLine()) 
         {
            s1 = sc.nextLine();
            if ( s1.equals("") )
               break;
            
            //System.out.println(s1);   // debug
    
            String[] a = s1.split(" ", 2); 
            Command c = new Command();
        
            c.setLicense( a[0] );
            c.setOrder ( a[1] );
         
            t.push(c);
         }
         sc.close();
      }
   
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }
     
      // got the whole  file...the stack is backwards, so reverse it
      while ( t.size() > 0 )
         cmd.push ( t.pop() );        
   }

   public void arrival( Command c )
   {
      System.out.print(c.getLicense() + " arrives");   
      
      if ( garageSpaces.size() == FULL )
         System.out.print(". Garage is full");
      else
      {
         Car car = new Car ( c.getLicense() );
         garageSpaces.push(car);
      }
      
      System.out.println();   
   }
  
   public void departure( Command c)
   {
      Car car;
      
      System.out.print(c.getLicense() + " exits and was moved ");        
      
      // find the car
      car = garageSpaces.pop();
      
      while( car.getLicense().equals(c.getLicense()) == false )
      {
         car.incrementCount();
         tempSpaces.push(car);
         car = garageSpaces.pop();
      }   
      
      // at this point I've got the license I was looking for
      while ( tempSpaces.size() > 0 )
      {
         garageSpaces.push( tempSpaces.pop() );
      }
      
      // now print the proper message
       if ( car.getCount() == 1 )
         System.out.println(car.getCount() + " time");
      else
         System.out.println(car.getCount() + " times");
   }

   public int getCmdStackSize()
   {
      return cmd.size();  
   }

    public Command popCmdStack()
   {
      return cmd.pop();  
   } 

  public static void main(String [] args)
   {
      // create a garage and read in teh file of commands to operate on
      Garage g = new Garage ("cars.txt");

      Command c = new Command();

      // process the array of commands
      while ( g.getCmdStackSize() > 0 )
      {
         c = g.popCmdStack();
         if ( c.getOrder().equals("A") )
            g.arrival (c);
         else if ( c.getOrder().equals("E") )
            g.departure(c);
      }
   }
} 