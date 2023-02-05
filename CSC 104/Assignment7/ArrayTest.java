//Jacob Pacheco
//CSC 104-A
//Assignment 7.3.1

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Scanner;

public class ArrayTest 
{
   protected static final int MAX_ARRAY_SIZE = 10;

   private static String [] st = new String[MAX_ARRAY_SIZE];
   private static int numOfItems = 0;

   public ArrayTest()
   {
     
   }

   public ArrayTest( String f )
   {
      readFile(f);
   }

   public String getItem(int i)
   {
      int size = st.length;
      if ( (size < 0) || (size > MAX_ARRAY_SIZE) )
         return null; 
       
      return st[i];   
   }

   public static void setItem ( String s, int i)
   {
      int size = st.length;
      if ( (size < 0) || (size > MAX_ARRAY_SIZE) )
         return; 

      st[i] = s;
   }

   public static void setNumberOfItems(int n)
   {
      int size = st.length;
      if ( (size < 0) || (size > MAX_ARRAY_SIZE) )
         return; 

      numOfItems = n;
   }
   
   public static int getNumberOfItems()
   {
      return numOfItems;
   }
   
   public static void incrementNumberOfItems()
   {
      int x = getNumberOfItems();
      x++;
      setNumberOfItems(x);
   }
 
   public static void readFile(String f)
   {
     setNumberOfItems(0);
   
     String s = "";
 
     File file = new File(f);
     
     try 
     {
        int index = 0; 
        Scanner sc = new Scanner(file);

         while (sc.hasNextLine()) 
         {
            s = sc.nextLine();
            setItem(s, index++);
            incrementNumberOfItems();
         }   
             
         sc.close();
      }
   
      catch(FileNotFoundException exception)
      {
         System.out.println("File " + file.getPath() + " was not found.");
         System.exit(0);
      }
   }

   public static String getFilenameFromUser()
   {
      String s;
      Scanner keyboard = new Scanner(System.in);

      System.out.print("Enter file name: ");
      s = keyboard.nextLine();     

      return s;
   }

   public static int getIndexFromUser()
   {
      Scanner keyboard = new Scanner(System.in);

      System.out.println();
      System.out.print("Enter index: ");
      int i = keyboard.nextInt();     

      return i;
   }

   public static String find (int i)
   {
      String s;
   
      try 
      {
         s = st[i];     
      } 
      catch (ArrayIndexOutOfBoundsException exception) 
      {
          System.out.println("Index out of bounds: " + i);
          return null;
      } 

      return s;
   }

   public static void main(String [] args)
   {
      int x;
      String fname;

      ArrayTest a = new ArrayTest();
      
      fname = getFilenameFromUser();
      readFile(fname);
      
      x = getIndexFromUser();
      
      while ( x != 999 ) 
      {
         String s = find (x);
         if ( s == null )
            System.out.println(x + " bad input");
         else    
            System.out.println(x + " " + s);
 
         x = getIndexFromUser();
      } 
      System.out.print("Bye");
   }
}