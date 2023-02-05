//Jacob Pacheco
//CSC 104-A
//Assignment 4.2.1

public class ListTester
{
   public static void main(String[] args)
   {   
      LIFO s = new LIFO();
      System.out.println("LIFO: ");
      s.insert(2);
      s.insert(12);
      s.insert(71);
      s.insert(50);
      System.out.println(s.remove());
      System.out.println(s.remove());
      s.insert(3);
      s.insert(13);
      System.out.println(s.remove());
      System.out.println(s.remove());
      s.insert(11);
      System.out.println(s.remove());
      System.out.println(s.remove());
      System.out.println(s.remove());
      System.out.println(s.remove());
      
      FIFO q = new FIFO();
      System.out.println("FIFO: ");
      q.insert(2);
      q.insert(12);
      q.insert(71);
      q.insert(50);
      System.out.println(q.remove());
      System.out.println(q.remove());
      q.insert(3);
      q.insert(13);
      System.out.println(q.remove());
      System.out.println(q.remove());
      q.insert(11);
      System.out.println(q.remove());
      System.out.println(q.remove()); 
      System.out.println(q.remove());
      System.out.println(q.remove());
 
      PRIORITY pq = new PRIORITY();
      System.out.println("PRIORITY: ");
      pq.insert(2);
      pq.insert(12);
      pq.insert(71);
      pq.insert(50);
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      pq.insert(3);
      pq.insert(13);
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      pq.insert(11);
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      System.out.println(pq.remove());
   }  
}
 
abstract class Lists //abstract class
{	
   protected int[] data;
   protected int size;

   protected static final int EMPTY_LIST  = -999;

   public abstract void insert(int x);
   public abstract int remove();

   public Lists() // default constructor
   {
      data = new int[100];

      setSize(0);
   }
   
   public void setData(int value, int location) // set data
   {
      data[location] = value;
   }

   public int getData(int location) // return data
   {
      int x;
      x = data[location];
      return x;
   }  

   public void setSize(int x) // set size
   {
      size = x;
   }

   public void incrementSize() // increase size
   {
      int x;
      
      x = getSize();
      x++;
      setSize(x);
   }

   public void decrementSize() // decrease size
   {
      int x;
      
      x = getSize();
      if ( x == 0 )
         return;
      x = x - 1;
      setSize(x);
   }

   public int getSize() // return size
   {
	   return size;
   }
   
   public void displayList() //display lists
   {
      for ( int i = 0; i < getSize(); i++ )
         System.out.print(getData(i) + " ");
   
      System.out.println();
   }    
}

class FIFO extends Lists // FIFO = First In First Out
{
   public void insert(int x) // insert at the end; satisfying the abstract condition
   {
      setData(x, getSize());
      incrementSize();   
   }
   
   public int remove() // remove from the end; satisfying the abstract condition
   {
      int valueRemoved;      
      size = getSize();
      
      if (size == 0)
      {
         System.out.println("Empty List: Value = " + EMPTY_LIST);
         return EMPTY_LIST;
      }
   
      valueRemoved = getData(0); // get the data from the First spot
   
      for (int i = 0; i < size; i++) // shift everyone to teh left one space
         setData(getData(i+1), i);

      decrementSize(); // point to the final filled element in the data
      
	   return valueRemoved;
   }  
}

class LIFO extends Lists
{
   public void insert(int x) // insert at the end; satisfying the abstract condition
   {
      setData(x, getSize());
      incrementSize();   
   }
    
   public int remove()  // remove from the end of the list
   {
      int valueRemoved;      
      size = getSize();
      
      if (size == 0)
      {
         System.out.println("Empty List: Value = " + EMPTY_LIST);
         return EMPTY_LIST;
      }
   
      decrementSize();
      
      valueRemoved = getData(getSize()); // get the data from the last spot
   
      setData(0, getSize()); //erase the dta in the spot we just removed
      
	   return valueRemoved;
   }  
}

class PRIORITY extends Lists
{ 
   public void insert(int x)
   {
      setData(x, getSize());
      incrementSize();    
   }
   
   public int remove()
   {
      int value = 0;
      int index = 0;
     
      if (getSize() == 0)
      {
         System.out.println("Empty List: Value = " + EMPTY_LIST);
         return EMPTY_LIST;
      }
      
      for (int i = 0; i < getSize(); i++) // find the largest value and save its poisiotn in index
      {
         if (getData(i) > value)
         {
            value = getData(i);
            index = i;
         } 
      }

      for (int i = index; i < getSize(); i++) // index hold the position of the value to remove
         setData ( getData(i+1), i);

      decrementSize();      

	   return value;
   }     
} 