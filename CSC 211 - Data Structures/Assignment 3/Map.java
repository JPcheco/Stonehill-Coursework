//Jacob Pacheco
//CSC 211
//Assignment 2.2.2

public class Map
{
   int index;
   int color = 0;
   String name;   
   int[] array = new int[49];    

   public Map(int i, String n)
   {
      index = i;
      name = n;
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
   
   public void setColor(int c)
   {
      color = c;
   }
   
   public int getColor()
   {
      return color;
   }
   
   
   public int[] getArray()
   {
      return array;
   }
   
   public void setArray (int[] a)
   {
      array = a;
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