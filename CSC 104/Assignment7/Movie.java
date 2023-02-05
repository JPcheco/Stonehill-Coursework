//Jacob Pacheco
//CSC 104-A
//Assignment 7.1.3

class Movie extends Item
{
   protected int time;
   protected String rating;
   protected char format;

   public Movie() 
   {
      super();
   
      String r = new String("");
      rating = r;
  
      setTime(0);
      setRating("G");
      setFormat('B');
   }
   
   public Movie (String t, double p, int s, String r, int mt, String mr, char mf) 
   {   
      super (t, p, s, r ); 
   
      setTime(mt);
      setRating(mr);
      setFormat(mf);
   }

   public int getTime()    
   {
      return time;
   }  
   
   public void setTime(int t) 
   {
      if ( t < 0 ) 
         t = 0;
      time = t;
   }  

   public String getRating()    
   {
      return rating;
   }  
   
   public void setRating( String r ) 
   {
      if ( r.compareTo("G") == 0 )
         rating = r;         
      else if (r.compareTo("PG") == 0 )
         rating = r;         
      else if (r.compareTo("PG13") == 0 )
         rating = r;         
      else if (r.compareTo("R") == 0 )
         rating = r;         
      else
          rating = "G";  
          
   }  

   public int getFormat()    
   {
      return format;
   } 
    
   public void setFormat(char f) 
   {
      if ( Character.compare(f, 'D') == 0 )
         format = f;
 
      format = 'B';
    }  

   //String toString()
   @Override
    public String toString() 
    { 
        return String.format(getTitle() + " " + getPrice() + " " + getStatus() + " " + getRenter() + " " 
                                        + getTime() + " " + getRating() + " " + getFormat() + " " + "Movie");
    }    
} 