//Jacob Pacheco
//CSC 104-A
//Assignment 13.2.2

public class Car
{
   private String license;
   private int count;   

   public Car ( String l )
   {
      setLicense(l);
      setCount(0);
   }

   public String getLicense()
   {
      return license;
   }
   
   public void setLicense(String l)
   {
      license = l;
   }

   public int getCount()
   {
      return count;
   }
   
   public void setCount(int c)
   {
      count = c;
   }
   
   public void incrementCount()
   {
      int c = getCount();
      c++;
      setCount(c);
   }
 
 //String toString()
   @Override
   public String toString() 
   { 
      return String.format( getLicense() ); 
   }

   public boolean equals(Object o)
   {  
      if ( compareTo(o) == 0 )
         return true;
      else
         return false;           
   }

   public int compareTo(Object o)
   {
      int result;
   
      String thisLicense = new String();
      thisLicense = getLicense();
    
      String passedLicense = new String();
      passedLicense = ((Car)o).getLicense();  
  
      result = thisLicense.compareTo(passedLicense);
    
      return result;
    }
}