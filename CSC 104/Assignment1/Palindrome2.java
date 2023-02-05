//Jacob Pacheco
//CSC 104-A
//Assignment 1.1

import java.util.*;
import java.io.*;

public class Palindrome2
{
  public static boolean isPalindrome(String s)
  {
    String reverse = "";
   
    for(int i=0; i<s.length(); i++)
      reverse = s.charAt(i) + reverse;
   
    return s.equals(reverse);
  }
 
  public static String removeCharacters(String l)
  {    
    String orignal = "";
   
    for(int i=0; i<l.length(); i++)
      {
         if (Character.isLetter(l.charAt(i)))
         {
            orignal = orignal + l.charAt(i);
         }
      }
         
    orignal = orignal.toUpperCase();
   
    return orignal;
  }
   
  public static void main(String[] args) throws IOException
  {
    File inFile = new File("sentences.txt");
    
    if (!inFile.exists())
    {
      System.out.println("File does not exist");
      System.exit(0);
    }     
      
    Scanner input = new Scanner(inFile);
    
   // Scanner input = new Scanner(System.in);
    
    File outputFile = new File("palindromes.txt");
    PrintWriter pw = new PrintWriter(outputFile);
   
    while (input.hasNext())
    {
      String l = input.nextLine();
      String s = removeCharacters(l);
      if (isPalindrome(s))
         pw.println(l + "--> Palindrome");
      else
         pw.println(l + "--> Not a Palindrome");
    }    
    input.close();
    pw.close();
   
  }  
}