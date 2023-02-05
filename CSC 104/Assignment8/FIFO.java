//Jacob Pacheco
//CSC 104-A
//Assignment 8.2.2

public class FIFO extends Lists // FIFO = First In First Out
{
   public FIFO()
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
      
      if (size == 0)
         return null;
   
      valueRemoved = getData(0); // get the data from the First spot
      data.remove(0); //remove data 
   
	   return valueRemoved;
   }  
}