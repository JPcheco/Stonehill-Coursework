//Jacob Pacheco
//CSC 104-A
//Assignment 6.2.1

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Roster  
{
   Student[] studentArray = new Student[MAX_ARRAY_SIZE];;
   int students;     

   protected static final int MAX_ARRAY_SIZE = 35;

   public Roster()
   {
      for (int i = 0; i < MAX_ARRAY_SIZE; i++ )
         studentArray[i] = new Student();
    
      setNumberOfStudents(0);
   }

   public Roster( String f )
   {
      for (int i = 0; i < MAX_ARRAY_SIZE; i++ )
        studentArray[i] = new Student();
    
      setNumberOfStudents(0);
   
      // read file of students
      String s1 = "";
 
     File file = new File (new File(f).getAbsolutePath());
     //if ( file.exists() == true )
     //    System.out.println(f + " exists");
     //else     
     //    System.out.println(f + " does not exist");
     
     try 
     {
        Scanner sc = new Scanner(file);

         int index = 0;
         while (sc.hasNextLine()) 
         {
            s1 = sc.nextLine();
            //System.out.println(s1);
    
            String[] a = s1.split(" ", 5); 
        
            getStudent(index).setLastName( a[0] );
            getStudent(index).setFirstName ( a[1] );
            getStudent(index).setSSNumber ( a[2] );
            Double dd = new Double (a[3]);
            getStudent(index).setGPA (dd.doubleValue() );
            getStudent(index).setCampusAddress(a[4]);
        
            index++;
            incrementNumberOfStudents();
         }
         sc.close();
      }
   
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }        
   }

   public void setNumberOfStudents(int n)
   {
      students = n;
   }
   
   public int getNumberOfStudents()
   {
      return students;
   }
   
   public void incrementNumberOfStudents()
   {
      int x = getNumberOfStudents();
      x++;
      setNumberOfStudents(x);
   }

   public void setStudent(Student s, int i)
   {
      studentArray[i] = s;
   }
   
   public Student getStudent(int i)
   {
      return studentArray[i];
   }

   public void sort ()
   {
       // sorts by lastName + " "+ firstName, you can just call the generic/utility sort from class (SelectionSort.sort(...))
       SelectionSort.sort ( studentArray, getNumberOfStudents() );
   }

   public int search (String lastname, String firstname )
   {
      int result;
      Student s = new Student(lastname, firstname, "123", 2.0, "foo");

   
      result = BinarySearch.search ( studentArray, getNumberOfStudents(),  s);      

      return result;
   }

   public int addStudent(Student s)
   {
      String string;
      Double d;
      
      Scanner keyboard = new Scanner(System.in);
          
      System.out.print("Last name: ");
      string = keyboard.nextLine();
      
      s.setLastName(string.toUpperCase());

         
      System.out.print("First name: ");
      string = keyboard.nextLine();
      s.setFirstName(string.toUpperCase());
   
 
      System.out.print("SS Number: ");
      string = keyboard.nextLine();
      s.setSSNumber(string.toUpperCase());
      
      System.out.print("Campus Address: ");
      string = keyboard.nextLine();
      s.setCampusAddress(string.toUpperCase());
    
      System.out.print("GPA: ");
      d = keyboard.nextDouble();
      s.setGPA(d);
     
      System.out.println();  

      return 0;
   }

   public void searchStudent(Student s)
   {
   
      String string;
      Double d;
      
      Scanner keyboard = new Scanner(System.in);
          
      System.out.print("Last name: ");
      string = keyboard.nextLine();
      
      s.setLastName(string.toUpperCase());

         
      System.out.print("First name: ");
      string = keyboard.nextLine();
      s.setFirstName(string.toUpperCase());
   }   

   public void printClass()
   {
   
      for (int i = 0; i < getNumberOfStudents(); i++ )
         System.out.println(getStudent(i));

      System.out.println();
   }
 
   public String menu()
   {
      char ch;

      Scanner keyboard = new Scanner(System.in);

      do 
      {
         System.out.println("S: search for a student");
         System.out.println("A: add a student");
         System.out.println("P: print all students");
         System.out.println("E: exit");
         System.out.print("Choice: ");
   
         ch = keyboard.next().charAt(0);
         ch = Character.toUpperCase(ch);
    
         switch ( ch )
         {
         
            case 'S': case 'A': case 'P': case 'E':
               return String.valueOf(ch);

         }
      } while (true);        
   }

   public void performAction(String choice)
   {     
      if (choice.equals("A"))
      {
         // ask for the data for a student, last name, first name, gpa etc
         // create a student object s
         // call add(s);
         Student s = new Student();
         addStudent(s);
       
         setStudent( s, this.getNumberOfStudents() );
         incrementNumberOfStudents();
         this.sort();         
      }
      else if (choice.equals("S"))
      {
         // ask for the last name and first name
         // pass this to search and search() returns an int ( -1 if not found)
         // the int returned is the place in the array, so print the data or say "not found"
         String last, first;
         Scanner keyboard = new Scanner(System.in);
          
         System.out.print("Last name: ");
         last = keyboard.nextLine();
         last = last.toUpperCase();

         
         System.out.print("First name: ");
         first = keyboard.nextLine();
         first = first.toUpperCase();
      
         int x = search(last, first);
         if  ( x == -1 )
            System.out.print(last + " " + first  + " not found"); 
         else
            System.out.print(getStudent(x) );                   
      }
      else if (choice.equals("P"))
      {
         // print all the data in the array
         printClass();
      }
      else if (choice.equals("E"))
      {
         return;
      }
      else
      {
         return;
      }      
   }

   public static void main(String [] args)
   {
      Roster r = new Roster("students.txt");
      String choice;
      do
      {
         System.out.println();
         
         choice = r.menu();
         r.performAction(choice);
         System.out.println();
      } while (!choice.equals("E"));
      
      System.out.print("Bye");
   }
}