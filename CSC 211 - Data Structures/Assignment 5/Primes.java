// Jacob Pacheco
// CSC 211
// Assingment 5.2.1

import java.util.*;
import java.io.*;
import java.lang.*;

public class Primes
{
   static LList<Integer> queue1 = new LList<Integer>(); //queue for #'s 2 to n
   static LList<Integer> queue2 = new LList<Integer>(); //primes queue
   
   public static void findPrimes(int n)
   {  
      int p, q;
      double sqrt = Math.sqrt(n);
           
      for(int i = 2; i <= n; i++)
         queue1.add(i);
         
      do
      {
         p = queue1.get(0);         
         queue2.add(queue1.remove(0));
         
         for(int x = 0; x < queue1.size(); x++)
         {
            if(queue1.get(x) % p == 0)
               queue1.remove(x);
         }
      }while( (double)p < sqrt);
      
      while(queue1.size() > 0 ) // look at the last element in the queue
      {
         q = queue1.remove(0); 
         queue2.add(q);
      } 
      
      for(int j = 0; j < queue2.size(); j++)
         System.out.print(queue2.get(j) + " ");
         
      while(queue2.size() > 0)
         queue2.clear();
   }
     
   public static void main(String[] args)
   {      
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a number --- end with -1: "); 
      int number = input.nextInt();

      while(number != -1)
      {
         findPrimes(number); 

         System.out.println();
         System.out.print("Enter a number -- end with -1: ");
         number = input.nextInt(); 
      }
   }
}