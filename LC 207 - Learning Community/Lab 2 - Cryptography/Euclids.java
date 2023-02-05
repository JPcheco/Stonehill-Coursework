import java.util.Scanner;

public class Euclids
{
   public static GCD GCDiv(int a, int b)
   {
      if (b==0)
      {
         GCD ex= new GCD(a,1,0);
         return ex;
      }
      GCD new1= GCDiv(b,a%b);
      
      
      GCD e= new GCD(new1.gcd, new1.y, new1.x-(a/b)*new1.y);
      return e;
   }
   
   
   public static void main(String [] args)
   {
      Scanner scan= new Scanner(System.in);
      
       System.out.println("Enter a number: ");
      int num1= scan.nextInt();
      System.out.println("Enter a smaller number: ");
      int num2= scan.nextInt();
    
   
      //make sure num1 is larger
      if(num1<num2)
      {
         int temp= num1;
         num1=num2;
         num2=temp;
         
      }
      GCD ans= GCDiv(num1,num2);
      
      System.out.println("The GCD is: "+ ans.gcd);
      System.out.println("The X is: "+ ans.x);
      System.out.println("The Y is: "+ ans.y);
      
      int revX;
      int revY;
      if(ans.x>0)
      {
         revX= ans.x-num2;
         revY= ans.y+num1;
      }
      else 
      {
         revX= ans.x+num1;
         revY= ans.y-num2;
      }

      System.out.println("The reverse X is: "+ revX);
      System.out.println("The reverse Y is: "+ revY);
      
   }
   
   
   
   
   
}