import java.util.Scanner;
import java.lang.*;
public class DownGCD
{
   public static void main(String [] args)
   {
      //long start=System.nanoTime();
      Scanner scan = new Scanner (System.in);
      
      System.out.println("Enter a number: ");
      int num1= scan.nextInt();
      System.out.println("Enter a smaller number: ");
      int num2= scan.nextInt();
      
      long start=System.nanoTime();
      int gcd=1;
      for(int i=num2; i>0; i--)
      {
         if((num1%i==0) && (num2%i==0))
         {
            System.out.println("Time:"+( System.nanoTime()-start));
            System.out.println("GCD of "+ num1 +" and "+ num2+" is: "+ i);
            break;
         }
      }
      
   }
   
}