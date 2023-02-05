//Jacob Pacheco
//CSC 104-A
//Assignment 6.2.2


abstract class Person implements Comparable
{
   String lastName;
   String firstName;
   String SSNumber;

   public Person()
   {
      String l = new String();
      String f = new String();
      String s = new String();

      setLastName(l);
      setFirstName(f);
      setSSNumber(s);
   }   
   
   public Person (String l, String f, String s)
   {
      setLastName(l);
      setFirstName(f);
      setSSNumber(s);
   }  
   
   public void setLastName(String s)
   {
      lastName = s;
   }
   
   public String getLastName()
   {
      return lastName;
   }

   public void setFirstName(String s)
   {
      firstName = s;
   }
   
   public String getFirstName()
   {
      return firstName;
   }

   public String getFullName()
   {
      String n = new String ( getLastName() + " " + getFirstName() );
   
      return n;  
   }

   public void setSSNumber(String s)
   {
      SSNumber = s;
   }
   
   public String getSSNumber()
   {
      return SSNumber;
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
   
      String thisFullName = new String();
      thisFullName = getLastName() + " " + getFirstName();  
    
      String passedFullName = new String();
      passedFullName = ((Person)o).getLastName() + " " + ((Person)o).getFirstName();  
  
    
      result = thisFullName.compareTo(passedFullName);
    
      return result;         
    }
}