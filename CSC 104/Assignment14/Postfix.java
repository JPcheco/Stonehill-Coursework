//Jacob Pacheco
//CSC 104-A
//Assignment 14.1.2

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Postfix
{
   public static int evaluate(String postfix)
   {
      Stack<Integer> s = new Stack<Integer>();
      
      int x = 0, y = 0;

      Scanner token = new Scanner(postfix);
      
      while(token.hasNext())
      {         
         String c = token.next(); //postfix.next(i);
         
         if(c == " ")
            continue;
         
         //if operand push into stack
         else if(isNumeric(c) == true)
         {
            int i = Integer.parseInt(c);
            s.push(i);
         }
      
         //if operator
         else
         {
            x = s.pop();
            y = s.pop();
            
            switch(c)
            {
               case "+":
                  s.push(y+x); 
                  break;
               case "-":
                  s.push(y-x); 
                  break; 
               case "*":
                  s.push(y*x); 
                  break;
               case "/":
                  if(x != 0)
                     s.push(y/x); 
                  else
                  {
                     JOptionPane.showMessageDialog(null,"Division by 0","Illegal Expression", JOptionPane.ERROR_MESSAGE);
                     return 9999999;
                  }
                  break;      
            }
         }
      }
      return s.pop();
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
   
   public static void main(String[] args) // for testing
   {
      String s = "20 10 + 30 + 2 *";
      System.out.println(s + " = " + Postfix.evaluate(s));
      
      s = "30 40 + 50 - 10 +";
      System.out.println(s + " = " + Postfix.evaluate(s));
      
      s = "20 10 3 * + 50 + 2 *";
      System.out.println(s + " = " + Postfix.evaluate(s));
      
      s = "5 2 / 6 + 10 -";
      System.out.println(s + " = " + Postfix.evaluate(s));
      
      s = "50 10 * 4 / 2 * 7 3 - +";
      System.out.println(s + " = " + Postfix.evaluate(s));
   }
}