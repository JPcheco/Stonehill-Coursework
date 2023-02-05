//Jacob Pacheco
//CSC 104-A
//Assignment 14.1.1.1

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class TestConvert
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      System.out.println("Enter infix -- end with XXX");
      String infix = input.nextLine();
      
      while (!infix.equals("XXX"))
      {
         System.out.println("Postfix is "+ ConvertToPostfix.convert(infix));
         System.out.println("Enter infix -- end with XXX");
         infix = input.nextLine();
      }
   }
}