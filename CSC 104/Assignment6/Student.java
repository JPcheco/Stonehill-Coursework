//Jacob Pacheco
//CSC 104-A
//Assignment 6.2.4

class Student extends Person 
{
   double GPA;
   String campusAddress;

   public Student()
   {
      super();
   
      String a = new String();
   
      setGPA(0);
      setCampusAddress(a);
   }

   public Student( String l, String f, String s, Double g, String a )
   {
      super(l, f, s);
      
      setGPA(g);
      setCampusAddress(a);
   }

   public void setGPA(double g)
   {
      GPA = g;
   }
   
   public double getGPA()
   {
      return GPA;
   }

   public void setCampusAddress(String a)
   {
      campusAddress = a;
   }
   public String getCampusAddress()
   {
      return campusAddress;
   }

  //String toString()
   @Override
   public String toString() 
   { 
      return String.format( getLastName() + " " + getFirstName() + " " + getSSNumber() + " " + getGPA() + " " + getCampusAddress() ); 
   }
}