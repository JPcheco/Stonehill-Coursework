import java.util.Scanner;
import java.lang.*;
public class GCDIterative
{

   public static int GCD(int a, int b)
   {
      while(b>0)
      {
         int temp=b;
         b=a%b;
         a=temp;
      }  
      return a;
   }
   
   public static void main(String [] args)
   {
      Scanner scan= new Scanner(System.in);
      
       System.out.println("Enter a number: ");
      int num1= scan.nextInt();
      System.out.println("Enter a smaller number: ");
      int num2= scan.nextInt();
    
      long start=System.nanoTime();
      //make sure num1 is larger
      if(num1<num2)
      {
         int temp= num1;
         num1=num2;
         num2=temp;
         
      }
      System.out.println("Time:"+( System.nanoTime()-start));
      System.out.println("The GCD is: "+ GCD(num1,num2));


   }
}