// Jacob Pacheco
// CSC 211
// Assingment 6.1.1

import java.util.*;
import java.io.*;
import java.lang.*;

public class NameHandler
{  
   public static BinarySearchTree<Names> bt = new BinarySearchTree<Names>();

   public NameHandler(String f)
   {
      // read file of movements
      String s1 = "";
 
      File file = new File (new File(f).getAbsolutePath());
  
      try 
      {
         Scanner sc = new Scanner(file);

         while(sc.hasNextLine()) 
         {
            s1 = sc.nextLine();
            if (s1.equals(""))
               break;
            
            String[] a = s1.split(" ", 2); 
            Names n = new Names(a[0], a[1]); //creat name objects
            
            bt.insert(n);                              
          } // while loop
         sc.close();
      }  //try loop  
      catch(FileNotFoundException e) 
      {
         e.printStackTrace();
      }
   }
   
   public static void writeSortedFile() throws Exception
   {
     Scanner infile = null;
     PrintWriter pw = null; 

     String filename = "sortedNames.txt";
     
     File outfile = new File(filename);
     pw = new PrintWriter(outfile);
     
     bt.inorderREC(pw);
        
     pw.close();
   }
   
   public static void main(String[] args) throws Exception
   {       
      NameHandler n = new NameHandler("babynames.txt");
      
      Names nn;
      
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a Name end with XXX: "); 
      String s = input.nextLine();

      while(!s.equals("XXX"))
      {
         Names temp = new Names(s, "");
         nn = n.bt.search(temp);
         
         if(nn == null)
            System.out.print("Name not found");
         else
            System.out.println(nn);

         System.out.println();
         System.out.print("Enter a name: ");
         s = input.nextLine(); 
      }
      
      System.out.println();
      System.out.println("The hieght is " + n.bt.height() );
      System.out.println("The size is "   + n.bt.size()   );
      
      n.writeSortedFile();
   }
}