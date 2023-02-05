//Jacob Pacheco
//CSC 104-A
//Assignment 14.1.1

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class ConvertToPostfix
{
   public static String convert(String infix)
   {
      //returns the postfix version of an infix string
      Stack<String> s = new Stack<String>(); 
           
      StringTokenizer st = new StringTokenizer(infix, ")(+-*/", true);
      String postfix = "";
      
      while(st.hasMoreTokens())
      {
         String token = st.nextToken();
         token = token.trim();
         
         if(isNumeric(token) == true) //s.isLetterOrDigit(token)
         {
            postfix = postfix + token + " ";
         }
         
         else if( token.equals("("))
         {
            s.push(token);
         }
         
         else if(token.equals(")"))  
         {
            while(!s.empty() && !s.peek().equals("(")) //the top of the stack is not a matching "("
            {
               //pop the stack
               //add the popped operator to the postfix expression
               //with a space at the end
               postfix = postfix + s.pop() + " ";
            }
            s.pop(); //pop the (off the stack and discard it, Do not add it to postfix
         }
         
         else//((token == "+") || (token == "-") || (token == "*") || (token == "/")
         {
             while(!s.empty() && Precedence(token) <= Precedence(s.peek())) //an operator of greater or equal priority is on the stack
             {
                //pop the stack and
                //append the popped operator (with a space at the end)
                //to the postfix String  
                postfix = postfix + s.pop() + " ";          
             }
             s.push(token);
         }
      }
      //pop any remaining item on the stack and add them to postix
      while(!s.empty())
      {
         postfix = postfix + s.pop() + " ";
      }
            
      return postfix;
   }
   
   public static int Precedence(String str)
   {
      switch (str)
      {
         case "+":
         case "-":
            return 1;
         case "*":
         case "/":
            return 2;         
      }
      return -1;
   }
   
   public static boolean isNumeric(String str)
   {
      try
      {
         Integer.parseInt(str);
         return true;
      }
      catch(NumberFormatException e)
      {
         return false;
      }
   }   
}