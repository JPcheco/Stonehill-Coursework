//Jacob Pacheco
//CSC 104-A
//Assignment 13.2.3

public class Command
{
   String license;
   String order;   

   public String getLicense()
   {
      return license;
   }
   
   public void setLicense(String l)
   {
      license = l;
   }

   public String getOrder()
   {
      return order;
   }
   
   public void setOrder(String o)
   {
      order = o;
   }

   public boolean isOrderValid()
   {
      if (  ( order.equals("A") == false ) &&
            ( order.equals("E") == false ) )
         return false;
      else
         return true;         
   }
}