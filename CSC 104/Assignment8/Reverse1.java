//Jacob Pacheco
//CSC 104-A
//Assignment 8.1.1

import java.util.*;

public class Reverse1
{
   public static boolean checkString(String s)
   {
      char ch;
      int length;

      for(int i=0; i<s.length(); i++)
      {
         ch = s.charAt(i);
         // return false if illegal value is found         
         if(!Character.isLetterOrDigit(ch))
            return false;
      }
      return true;
   }
   
   public static String reverse(String s) throws  IllegalCharacterException
   {
      // String s is the originalString;
      checkString(s); // check for illegal input
      
      String r = ""; //creats empty string for the reverseString
      
      if((checkString(s)) == false)
         throw new IllegalCharacterException("Illegal Character in String", "Reverse1");
              
      for(int i=(s.length() -1); i>-1; i--) // walks through s backwords
      {
         char ch = s.charAt(i);
         String str = String.valueOf(ch);
         r = r.concat(str); //adds to the end of r
      }
      return r;
   }
   
   public static void main(String[] args) throws IllegalCharacterException
   {
      Reverse1 r1 = new Reverse1();
      
      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter string: ");
      String s = input.nextLine();
      
      System.out.println("Original String: " + s);
      
      String reversedString = r1.reverse(s);
      
      if ( reversedString != null ) 
         System.out.println("Reverse String: " + " " + reversedString);
   }
}