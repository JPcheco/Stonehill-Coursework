import java.util.Scanner;
import java.lang.*;
public class Decode
{
   public static void main (String [] args)
   {
      Scanner scan = new Scanner(System.in);
      
      System.out.println("Enter message to decrypt: ");
      String message= scan.nextLine();
     
      String codeword = "UE";
      message= message.toUpperCase();
     
       String ans="";
      
      int count=0;
      for(int i=0; i<message.length(); i++)
      {
         char m= message.charAt(i);//char from message 
         
         if(m!=32) 
         {
            char x= codeword.charAt(count%codeword.length());//char from codeword 
            int shift= x-'A';// calculating shift 
            
            int temp =m-shift; //adding shift to letter 
            if(temp<65)// account for wrap around 
               temp= temp+26;
           count++;
           System.out.print((char)temp);
         }  
         else 
            System.out.print(" ");

         
         
      }
  
   }
}