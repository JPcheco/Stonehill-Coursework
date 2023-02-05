//Jacob Pacheco
//CSC 104-A
//Assignment 8.1.4

public class IllegalCharacterException extends Exception
{
   public IllegalCharacterException(String errorMessage, String classname)
   {
      super(errorMessage);
      
      System.out.println("Class: " + classname);
   }
}
