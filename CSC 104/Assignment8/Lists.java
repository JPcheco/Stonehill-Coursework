//Jacob Pacheco
//CSC 104-A
//Assignment 8.2.1

import java.util.ArrayList;
 
public abstract class Lists //abstract class
{	
   protected ArrayList<String> data = new ArrayList<String>(25);

   public abstract void insert(String x);
   public abstract String remove();
   
   public void setData(String x, int index)  // set value into the list at location 
   {
      data.set(index, x);
   } 

   public String getData(int index) // return data at Location
   {
      return data.get(index);
   }  

   public int getSize() // return size
   {
	   return data.size();
   }   
} 