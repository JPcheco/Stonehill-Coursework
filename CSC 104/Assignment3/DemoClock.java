//Jacob Pacheco
//CSC 103-A
//Assignment 3.2.2

import java.util.*;

public class DemoClock
{   
   public static void test()
   {
      Clock c = new Clock(23, 54);
 
      showTime(c);
      
      c.incrementTimer() ;
      showTime(c);

      c.incrementTimer(190);
      showTime(c);
      
      Scanner input = new Scanner(System.in);
      
      System.out.print("Enter hour: ");
      int h = input.nextInt();
      
      System.out.print("Enter minute: ");
      int m = input.nextInt();
      
      c.SetTime(h, m);
      showTime(c);   
   }   

   public static void showTime (Clock c)
    {
      System.out.println("Time is: " + c.toString() );
    }     
   
    public static void main(String[] args)
    {
       test();       
    }
}