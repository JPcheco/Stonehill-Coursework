import java.util.*;
public class Index
{
   public static void main (String [] args)
   {
      
      String message="BOQWMNWOOQSSFHQKASRLAGOWRAADWRKAONCIOHKJKCYHVYWHLLC";
      ArrayList <Character> original= new ArrayList<Character>();
      ArrayList <Character> shifted= new ArrayList<Character>();
      
      //fill 2 arrays with message
      for(int i= 0; i<message.length(); i++)
      {
         original.add(message.charAt(i));
         shifted.add(message.charAt(i));

      }
      //shift message and compare
      int count=0;
      for(int i=0; i<message.length(); i++)  
      {
      
         shifted.add(0, shifted.remove(message.length()-1));
         
         for(int j=0; j<message.length(); j++)//to compare original and shifted
         {
            if(original.get(j) == shifted.get(j))
            {
               count++;
            }
         }
         
         double percent= (double)(count)/message.length()*100;
         
         System.out.println(i+1+"      "+ percent+"%");
         count=0;
      }
   }
   
   
} 