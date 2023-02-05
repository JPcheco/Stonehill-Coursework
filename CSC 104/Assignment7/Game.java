//Jacob Pacheco
//CSC 104-A
//Assignment 7.1.2

class Game extends Item 
{
   protected int age;
   
   public Game() 
   {
      super();
   
      setAge(3);
   }
   
   public Game(String t, double p, int s, String r, int a) 
   {
      super (t, p, s, r); 
   
      setAge( a );
   }

   public int getAge()    
   {
      return age;
   }  

   public void setAge(int a) 
   {
      if ( a > 16 )
         a = 16;
      if ( a < 3 )
         a = 3;      
   
      age = a;
   }  

   //String toString()
   @Override
    public String toString() 
    { 
        return String.format( getTitle() + " " +getPrice() + " " + getStatus() + " " + getRenter() + " " + getAge() + " " + "Game");
    }       
} // end of class Game