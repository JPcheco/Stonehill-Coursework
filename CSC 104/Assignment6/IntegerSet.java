//Jacob Pacheco
//CSC 104-A
//Assignment 6.1.2

public class IntegerSet implements SetOperations
{
   private boolean[] set;
   private int size;   
   
   public IntegerSet() // default constructor 
   {
      set = new boolean[100]; //creats an empty set
      setSize(0); //sets everything to false
      
      for(int i = 0; i < 100; i++)
         setElement(i, false);
   }
   
   public IntegerSet(int[] a, int s)
   {
      set = new boolean[100]; //creats an empty set
      setSize(0); 
      
      for(int i = 0; i < 100; i++)
         setElement(i, false); //sets everything to false
         
      setSize(0);            
         
      for(int i = 0; i < s; i++)
         setElement(a[i], true);
   }  
   
   public IntegerSet(boolean[] a, int s) // two arguement constructor
   {
      set = a;
      size = s; //creats an intgerSet with an array of int with the size
   }
   
   public void setElement(int i, boolean b)
   {
      if(getElement(i) == true)
            incSize();
            
      set[i] = b;      
   }
   
   public boolean getElement(int i)
   {
      return set[i];
   }
   
   public int getSize()
   {
      return size;
   }
   
   public void setSize(int s)
   {
      size = s;
   }
   
   public void incSize()
   {
      int x = getSize();
      x++;
      setSize(x);
   }
   
   public boolean elementOf(int i) // is x a member of the set? T or F
   {
      return getElement(i);
   }
   
   public void add(int i) //adds x to set
   {
      setElement(i, true);
   }
   
   public boolean equals(Object x) //equal if the have the smae elements
   {      
      for(int i = 0; i < 100; i++)
      {
         if( ((IntegerSet)x).getElement(i) != this.getElement(i) )
            return false;
      }
      
      return true;
   }
   
   public String toString() // returns a string w/a single space between two elements
   {
      String s = new String();
      
      for(int i = 0; i < 100; i++)
      {
         if(getElement(i) == true)
            s = s + i + " ";
      }
      
      return s; // returns a string with all the set elements such that there is a single space between two elements
   }
   
   public Object union(Object x)
   {
      IntegerSet r = new IntegerSet();
      
      for(int i = 0; i < 100; i++)
      {
         if( ( ((IntegerSet)x).getElement(i) == true) && (this.getElement(i) == true) )
            r.setElement(i, true);
      }
      
      return r;
   }
   
   public Object intersection(Object x)
   {
      IntegerSet r = new IntegerSet();
      
      for(int i = 0; i < 100; i++)
      {
         if( ( ((IntegerSet)x).getElement(i) == true) || (this.getElement(i) == true) )
            r.setElement(i, true);
      }
      
      return r;
   }
}