//Jacob Pacheco
//CSC 104-A
//Assignment 7.1.4

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EddiesStore 
{
   Item [] itemArray = new Item[MAX_ARRAY_SIZE];
   int numOfItems;     

   protected static final int MAX_ARRAY_SIZE = 35;   
   protected static final int RENTED = 0;       // i.e. not available
   protected static final int AVAILABLE  = 1;   // i.e. in stock  

   public EddiesStore()
   {
      setNumberOfItems(0);
   }

   public EddiesStore( String f )
   {
      setNumberOfItems(0);
   
      // read file of inventory
      String s1 = "";
 
     File file = new File (new File(f).getAbsolutePath());
     
     try 
     {
        int index = 0; 
        Scanner sc = new Scanner(file);

         while (sc.hasNextLine()) 
         {
            s1 = sc.nextLine();

            // Games have 5 fields after the identifer; Movies have 7 fields
    
            String[] id = s1.split(" ", 2); 
         
            if ( id[0].compareTo("G") == 0 )
            {
               Game g = new Game();
               setItem( g, index ); 
            
               String[] a = id[1].split(" ", 5); 
        
               g.setTitle( a[0] );
               Double dd = new Double (a[1]);
               g.setPrice (dd.doubleValue() );              
               g.setStatus( Integer.parseInt(a[2]) );
               g.setAge( Integer.parseInt(a[3]) );
               g.setRenter(a[4]);
            }
            else
            {
               Movie m = new Movie();
               setItem( m, index );
                
               String[] a = id[1].split(" ", 7); 
        
               m.setTitle( a[0] );
               m.setTime( Integer.parseInt(a[1]) );
               Double dd = new Double (a[2]);
               m.setPrice (dd.doubleValue() ); 
               m.setRating(a[3]); 
               m.setFormat( a[4].charAt(0) );              
               m.setStatus( Integer.parseInt(a[5]) );
               m.setRenter(a[6]);
            }   
               
            index++;
            incrementNumberOfItems();
         }
         sc.close();         
      }
   
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }
  
   SelectionSort.sort ( itemArray, getNumberOfItems() );        
   }

   public void setNumberOfItems(int n)
   {
      numOfItems = n;
   }
   
   public int getNumberOfItems()
   {
      return numOfItems;
   }
   
   public void incrementNumberOfItems()
   {
      int x = getNumberOfItems();
      x++;
      setNumberOfItems(x);
   }

   public void setItem(Item s, int i)
   {
      itemArray[i] = s;
   }
   
   public Item getItem(int i)
   {
      return itemArray[i];
   }
   
   public void sort ()
   {
       // sorts by title
       SelectionSort.sort ( itemArray, getNumberOfItems() );
   }

   public int search (String t )
   {
      int result;
      Game g = new Game (t, 2.0, 0, "foo", 3);

      result = BinarySearch.search ( itemArray, getNumberOfItems(),  g);      

      return result;
   }

   public Item createItem()
   {
      char c = getGameOrMovieFromUser();
      
      if ( c == 'G' )
      {
         Game g = new Game();

         g.setTitle( getTitleFromUser() );
         g.setPrice( getPriceFromUser() );
         g.setStatus( getStatusFromUser() );
         g.setAge( getAgeLevelFromUser() );
         g.setRenter( getRenterFromUser() );
         System.out.println();
         
         return g;      
      }
      else
      {
         Movie m = new Movie();

         m.setTitle( getTitleFromUser() );
         m.setTime (getTimeFromUser() );
         m.setPrice( getPriceFromUser() );
         m.setRating( getRatingFromUser() );
         m.setFormat( getFormatFromUser() );
         m.setStatus( getStatusFromUser() );
         m.setRenter( getRenterFromUser() );
         System.out.println();

         return m;      
      }
   }

   public void printInventory()
   {
      System.out.println();
   
      for (int i = 0; i < getNumberOfItems(); i++ )
         System.out.println(getItem(i));

      System.out.println();
   }
 
   public String menu()
   {
      char ch;

      Scanner keyboard = new Scanner(System.in);

      do 
      {
         System.out.println("A: check out an item");
         System.out.println("B: check in an item");
         System.out.println("C: search for an item by title");
         System.out.println("D: display entire inventory");
         System.out.println("E: add new item to inventory");
         System.out.println("F: display the menu");
         System.out.println("G: exit");
         System.out.print("Choice: ");
   
         ch = keyboard.next().charAt(0);
         ch = Character.toUpperCase(ch);
    
         switch ( ch )
         {        
            case 'A': case 'B': case 'C': case 'D': case 'E': case 'F': case 'G':
               return String.valueOf(ch);
         }
      } while (true);        
   }

   public char getGameOrMovieFromUser()
   {
      char ch1, ch2;
      Scanner keyboard = new Scanner(System.in);
       
      while (true)
      { 
         System.out.println();
         System.out.print("Game or Movie (G or M only): ");
         ch1 = keyboard.next().charAt(0);
         ch2 = Character.toUpperCase(ch1);

         if ( (ch2 == 'G') || ( ch2 == 'M') ) 
            return ch2;
      }
   }
   
   public String getRatingFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
       
      System.out.print("Rating: ");
      String s = keyboard.nextLine();

      return s;
   }
   
   public char getFormatFromUser()
   {
      char ch1, ch2;
      Scanner keyboard = new Scanner(System.in);
      
      while (true)
      { 
         System.out.print("B or D: ");
         ch1 = keyboard.next().charAt(0);
         ch2 = Character.toUpperCase(ch1);

         if ( (ch2 == 'B') || ( ch2 == 'D') ) 
            return ch2;
      } 
   }
   
   public String getTitleFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
       
      System.out.print("Title: ");
      String s = keyboard.nextLine();

      return s;
   }
   
   public int getTimeFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
       
      System.out.print("Running-Time: ");
      int i = keyboard.nextInt();

      return i;
   }
   
   public String getRenterFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
       
      System.out.print("Renter (Last,First): ");
      String s = keyboard.nextLine();

      return s;
   }
   
   public int getAgeLevelFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
       
      System.out.print("Age-Level: ");
      int i = keyboard.nextInt();

      return i;
   }
   
   public int getStatusFromUser()
   {
       int i;
       Scanner keyboard = new Scanner(System.in);
       
       while(true)
       {
         System.out.print("Status (1 or 0): ");
         i = keyboard.nextInt();
         
         if(i == 1)
            return i;  
         else
            System.out.println("Pst... You are adding the item to the inventory so press 1!");       
       }       
   }
   
   public double getPriceFromUser()
   {
      Scanner keyboard = new Scanner(System.in);
       
      System.out.print("Price: ");
      String s = keyboard.nextLine();

      double d = Double.parseDouble(s);

      return d;
   }

   public void performAction(String choice)
   {    
      if (choice.equals("A"))
      {
         // check out an item  (i.e. mark as checked out abnd add the renter's name)
         
         String s;
         int index; 
         Item n;
         
         System.out.println();
         s = getTitleFromUser();
      
         // search gets the index of the item
         index = search ( s );
         if ( index == -1 )
         {
            System.out.println("Sorry, we don't seem to have a record of that one");
            return;
         }

         n = getItem(index);
               
         if ( n.getStatus() == AVAILABLE )
         {
            s = getRenterFromUser();
      
            n.setRenter(s);
            n.setStatus(0);  // 0 = rented
         }
         else         
         {
            System.out.println("Sorry, that one is already checked out");
         }   
         
         System.out.println();
      }
      else if (choice.equals("B"))
      {
         // check in an item (i.e. mark as available and clear teh renater's name)
         
         String s;
         int index; 
         Item n;
         
         System.out.println();
         s = getTitleFromUser();
      
         // search gets the index of the item
         index = search ( s );
         if ( index == -1 )
         {
            System.out.println("Sorry, we don't seem to have a record of that one");
            return;
         }

         n = getItem(index);
      
         if ( n.getStatus() == RENTED )
         {
            n.setRenter("Available");
            n.setStatus(1);  // 0 = rented
         }
         else
         {
            System.out.println("Sorry, that one is not checked out");
         }    
         System.out.println("Item has been sucessfully returned!");
         System.out.println();
                      
      }
      else if (choice.equals("C"))
      {
         // search item by title
         int result;
         String s;
         
         s = getTitleFromUser();
         
         Game temp = new Game( s, 0.0, 1, "foo", 3);
         result = BinarySearch.search ( itemArray, getNumberOfItems(),  temp);  

         if ( result == -1  )
            System.out.println("Sorry, we don't have that"); 
         else
            System.out.println("We have that!");
            
         System.out.println();
      }
      else if (choice.equals("D"))
      {
         // display inventory
         printInventory();
      }
      else if (choice.equals("E"))
      {
         // add new item to inventory
         Item n;
         
         n = createItem();
         setItem ( n, getNumberOfItems() );
         incrementNumberOfItems();
         
         SelectionSort.sort ( itemArray, getNumberOfItems() );
      }
      else if (choice.equals("F"))
      {
         System.out.println();
         // display the menu
         return;
      }
      else if (choice.equals("G"))
      {
         // exit
         return;
      }
      else
      {
         return;
      }   
   }

   public static void main(String [] args)
   {
      EddiesStore v = new EddiesStore("data.txt");
      String choice;
      do
      {        
         choice = v.menu();
         v.performAction(choice);
         
      } while (!choice.equals("G"));
      
      System.out.println();
      System.out.print("Bye");
   }
}