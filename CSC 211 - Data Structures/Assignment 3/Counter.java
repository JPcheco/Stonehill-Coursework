//Jacob Pacheco
//CSC 211
//Assignment 3.2.1

import java.util.*;
import java.io.*;
import java.lang.*;

public class Counter
{
   public static final int STACK_SIZE = 20;
   public static final String END_SEQUENCE = "xxx";

   Stack<Integer> stack = new Stack<>();

   public Counter ()
   {
      // init the stack
      for(int i = 0; i < STACK_SIZE; i++)
      {
         stack.push(-1);
      }     
   }
 
   public void initStack(int size)
   {   
      // empty out anything residual
      while(stack.empty() == false)
         stack.pop();

      // init the stack
      for(int i = 0; i < size; i++)
      {
         stack.push(-1);
      }  
   }

   public static String getStringFromUser()
   {
      String s = new String();
      Scanner keyboard = new Scanner(System.in);
          
      System.out.print("Enter string: ");
      s = keyboard.nextLine();

      return s;
   }

   public boolean validateString(String s)
   {
      boolean result = false;

      // check if it's anythign other than 0's and 1's
      return true;
   }

   public boolean gotTerminator(String s)
   {
      boolean result = false;
      
      if(s.equals (END_SEQUENCE) == true)    
            result = true; 

      return result;
   }

   public void compare(String s)
   {
      char c;
      
      if (s.length() == 0)
         return;
      
      c = s.charAt(0);
   
      if(c == '0')
         stack.push(0);
      if(c == '1')
         stack.pop();    
   
      compare(s.substring(1));      
   }

   public static void main(String [] args)
   {
      String s;
      int value;

      Counter ct = new Counter();

      while(true)
      {
         s = ct.getStringFromUser(); 
         if (ct.gotTerminator(s) == true)
         {
            System.out.println("ByeCall the class Counter.java");
            return;
         }  
          
         if(ct.validateString(s) == false)
              break;
               
         ct.initStack(STACK_SIZE);          
          
          // compare the 0s and 1's         
         ct.compare(s); 
   
         if(ct.stack.size() == STACK_SIZE)
            System.out.println("Equal number of 0s and 1s\n"); 
         else if(ct.stack.size() < STACK_SIZE)   
            System.out.println("1 occurs more frequently\n");
         else
            System.out.println("0 occurs more frequently\n");

      } // while        
   } // main
} // class