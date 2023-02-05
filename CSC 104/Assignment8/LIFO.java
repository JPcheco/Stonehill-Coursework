//Jacob Pacheco
//CSC 104-A
//Assignment 8.2.3

class LIFO extends Lists
{
   public LIFO()
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
   
      valueRemoved = getData(size-1); // get the data from the First spot
      data.remove(size-1); //remove data 
   
	   return valueRemoved;
   } 
}
