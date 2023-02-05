//Jacob Pacheco
//CSC 104-A
//Assignment 4.2.1

 
abstract class Lists //abstract class
{	
   protected int[] data;
   protected int size;

   protected static final int EMPTY_LIST     = -999;
   protected static final int OUT_OF_BOUNDS  = -999;
      
   protected static final int MAX_LIST_SIZE = 100;


   public abstract void insert(int x);
   public abstract int remove();


   public Lists() // default constructor
   {
      data = new int[MAX_LIST_SIZE];

      setSize(0);
   }
   
   public void setData(int value, int location)  // set value into the list at location 
   {
      data[location] = value;
   }

   public int getData(int location) // return data at Location
   {
      int x;    
      
      if ( location > MAX_LIST_SIZE )
         x = OUT_OF_BOUNDS;
      else if ( location < 0 )
         x = OUT_OF_BOUNDS;
      else   
         x = data[location];
      
      return x;
   }  

   public void setSize(int s)  // set size to s
   {
      int x = s;      
   
      // limit the rangle to be betwen 0 and MAX_LIST_SIZE 
      if ( x < 0 )
         x = 0;
      else if ( x > MAX_LIST_SIZE )
         x = MAX_LIST_SIZE;   
   
      size = x;
   }

   public void incrementSize() // increase size
   {
      int x = getSize();
      
      // make sure we don't go over the edge of the list
      if ( x < MAX_LIST_SIZE )
      {
         x++;
         setSize(x);
      }
   }

   public void decrementSize() // decrease size
   {
      int x = getSize();
      
      // make sure we don't go negative
      if ( x > 0 )
      {
         x--;
         setSize(x);
      }   
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
   public FIFO() // default constructor from lists
   {
      super();
   }

   public void insert(int x) // insert at the end; satisfying the abstract condition
   {
      super.setData(x, super.getSize());
      super.incrementSize();   
   }
   
   public int remove() // remove from the end; satisfying the abstract condition
   {
      int valueRemoved;      
      size = super.getSize();
      
      if (size == 0)
      {
         System.out.println("Empty List: Value = " + EMPTY_LIST);
         return EMPTY_LIST;
      }
   
      valueRemoved = super.getData(0); // get the data from the First spot
   
      for (int i = 0; i < size; i++) // shift everyone to teh left one space
         super.setData(super.getData(i+1), i);

      super.decrementSize(); // point to the final filled element in the data
      
	   return valueRemoved;
   }  
}



class LIFO extends Lists
{
   public LIFO()
   {
      super();
   }
   
   public void insert(int x) // insert at the end; satisfying the abstract condition
   {
      super.setData(x, super.getSize());
      super.incrementSize();   
   }
    
   public int remove()  // remove from the end of the list
   {
      int valueRemoved;      
      size = super.getSize();
      
      if (size == 0)
      {
         System.out.println("Empty List: Value = " + EMPTY_LIST);
         return EMPTY_LIST;
      }
   
      super.decrementSize();
      
      valueRemoved = super.getData(super.getSize()); // get the data from the last spot
   
      super.setData(0, super.getSize()); //erase the dta in the spot we just removed
      
	   return valueRemoved;

   }  
}



class PRIORITY extends Lists
{
   public PRIORITY()
   {
      super();
   }
   
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
      
      // find the largest value and save its poisiotn in index
      for (int i = 0; i < getSize(); i++ )
      {
         if (getData(i) > value)
         {
            value = getData(i);
            index = i;
         } 
      }

      // index hold the position of the value to remove
      for ( int i = index; i < getSize(); i++ )
         setData ( getData(i+1), i);

      decrementSize();      

	   return value;
   }  
   
} 

 
 
 