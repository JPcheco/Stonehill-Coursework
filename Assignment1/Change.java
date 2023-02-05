//Jacob Pacheco
//CSC 104-A
//Assignment 1.2

public class Change
{   
   public static void main(String[] args)
   {
      StringBuilder sb = new StringBuilder("I won 1000 dollars");     
      System.out.println(sb);
      
      sb.delete(15,17);
      sb.deleteCharAt(9);
      System.out.println(sb);
      
      sb.replace(6,9,"60");
      sb.insert(13,"ie");
      System.out.println(sb);
   }
 }