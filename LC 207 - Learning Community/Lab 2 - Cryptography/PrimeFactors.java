import java.util.*;
import java.lang.*;
public class PrimeFactors
{
   public static void main(String [] args)
   {
      Scanner scan = new Scanner (System.in);
      
      System.out.println("Enter a number: ");
      int num1= scan.nextInt();
      System.out.println("Enter a smaller number: ");
      int num2= scan.nextInt();
      
      long start=System.nanoTime();
      ArrayList <Integer> n1factors= new ArrayList<Integer>();
      ArrayList <Integer> n2factors= new ArrayList<Integer>();
      ArrayList <Integer> numlist= new ArrayList<Integer>();

      
      //break num1 into prime factors
      for(int i=2; i<num1;i++)
      {
         while(num1%i==0)
         {
            n1factors.add(i);
            num1=num1/i;    
         }
      }    
      
      //break num2 into prime factors
      for(int i=2; i<num2;i++)
      {
         while(num2%i==0)
         {
            n2factors.add(i);
            num2=num2/i;
         }
      }    

      //see which factors are equal
      int a=0; //n1factors pointer
      int b=0; //n2factors pointer
         
      while(a<n1factors.size() && b<n2factors.size())     
      {
         if(n1factors.get(a)==n2factors.get(b))
         {
            numlist.add(n1factors.get(a));
            a++;b++;
         }
         else if(n1factors.get(a)>n2factors.get(b))
         {
            b++;
         }
         else
            a++;
      }
      
      int prod=1;
      for(int i=0; i<numlist.size();i++)
      {
         prod=prod*numlist.get(i);
      }
      System.out.println("Time:"+( System.nanoTime()-start));
      System.out.println("GCD: "+ prod);
   }
}