//Jacob Pacheco
//CSC 104-A
//Assignment 8.2.4

public class PRIORITY extends Lists
{
   public PRIORITY()
   {
      super();
   }
   
   public void insert(String x) // insert at the end
   {
      data.add(x);  
   }
   
   public String remove() // remove from the end
   {
      String valueRemoved;      
      int size = getSize();
      int index = 0;
      
      if (size == 0)
         return null;
      
      valueRemoved = getData(index);
      
      if(size > 1)
      {
         for(int i = 1; i<getSize(); i++)
         {
            if(getData(i).compareTo(valueRemoved) > 0)
            {
               index = i;
               valueRemoved = getData(i);
            }
         }
      }
      data.remove(index); //remove data 
   
	   return valueRemoved;
   }   
} 