//Jacob Pacheco
//CSC 104-A
//Assignment 7.1.1
 
public abstract class Item implements Comparable
{	
   protected String title;
   protected double price;
   protected int status;         // 1 = in stock; 0= currently rented
   protected String renter;      // lastname, firstname   

   public Item() // default constructor
   {
      String t = new String("");
      setTitle(t);
      
      setPrice(0);
      
      setStatus(0);
      
      String r = new String("");
      setRenter(r);   
   }
   
   public Item ( String t, double p, int s, String r )    
   {
      setTitle(t);
      setPrice(p);
      setStatus(s);
      setRenter(r);
   }

   public String getTitle()    
   {
      return title;
   } 
    
   public void setTitle( String t) 
   {
      title = t;
   } 
   
   public void setPrice( double p ) 
   {
      if ( p < 0 )
         p = 0;
   
      price = p;
   } 
    
   public double getPrice()    
   {
      return price;
   }  

   public void setStatus( int s) 
   {
      if ( s > 1)
         s = 1;
      if ( s < 0 )
         s = 0;   
   
      status = s;
   }  
   
   public int getStatus()    
   {
      return status;
   }  

   public void setRenter( String r ) 
   {
      renter = r;
   }  
   
   public String getRenter()    
   {
      return renter;
   }  
    
   //String toString()
   @Override
   public String toString() 
   { 
      return String.format( getTitle() + " " + getPrice() + " " + getStatus() + " " + getRenter() );
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
   
      String thisTitle = new String();
      thisTitle = getTitle();
    
      String passedTitle = new String();
      passedTitle = ((Item)o).getTitle();  
  
      result = thisTitle.compareTo(passedTitle);
    
      return result;
    }
}  