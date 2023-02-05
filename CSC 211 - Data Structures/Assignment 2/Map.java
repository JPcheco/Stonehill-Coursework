//Jacob Pacheco
//CSC 211
//Assignment 2.2.2

public class Map
{
   int index;
   String name;   
   boolean visited;
   int[] array = new int[49];    

   public Map(int i, String n)
   {
      index = i;
      name = n;
      visited = false;
   }

   public int getIndex()
   {
      return index;
   }
   
   public void setIndex(int i)
   {
      index = i;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String n)
   {
      name = n;
   }
   
   public int[] getArray()
   {
      return array;
   }
   
   public void setArray (int[] a)
   {
      array = a;
   }

   public boolean getVisited()
   {
      return visited;
   }
   
   public void setVisited (boolean v)
   {
      visited = v;
   }

   public int getArrayValue(int i)
   {
      return array[i];
   }
   
   public void setArrayValue (int i, int v)
   {
      array[i] = v;
   }
}