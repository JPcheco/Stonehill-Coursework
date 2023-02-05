// Jacob Pacheco
// CSC 211
// Assingment 5.1.1

import java.util.*;
import java.io.*;
import java.lang.*;

public class Palindrome
{
   static Stack<Character> stack = new Stack<Character>();
   static LList<Character> queue = new LList<Character>();
   
   public static boolean isPalindrome(String s)
   {  
      boolean result = false;
      char ch1, ch2;
      int count = 0;
      
      s = s.replaceAll("[^A-Za-z]+", "").toUpperCase(); //remove all punctuation & make uppercase
      int length = s.length();     
      
      for(int i = 0; i < length; i++) //read the original string into a queue and stack
      {
         count++;
         stack.push(s.charAt(i));
         queue.add(s.charAt(i));
      }
      
      for(int i = 0 ; i < count; i ++)
      {
         ch1 = stack.pop();
         ch2 = queue.remove(0);
         
         if(ch1 != ch2)
         {
            result = false;
            break;
         } 
         if(i == (count - 1))
            result = true;
      }
   
      while(stack.empty() == false) // clean out stack and queue
      {
         stack.pop();
         queue.remove(0);
      }     
      return result;
   }
     
   public static void main(String[] args)
   {      
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a string -- end with ABC: "); 
      String s = input.nextLine();

      while(!s.equals("ABC"))
      {
         boolean pali = isPalindrome(s); 

         if (pali)
            System.out.println(s + " is a palindrome"); 
         else
            System.out.println(s + " is not a palindrome");

         System.out.print("Enter a string -- end with ABC: ");
         s = input.nextLine(); 
      }
   }
}