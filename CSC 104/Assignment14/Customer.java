//Jacob Pacheco
//CSC 104-A
//Assignment 14.4.2

import java.util.*;

public class Customer
{
     private int arrivalTime; 		// 0..60, the minute of the hour when a customer arrives
     private int serviceTime; 		// 1, 2, or 3 minutes
     private int IDNumber;

     public Customer()  		   // default constructor
     {
          arrivalTime = 0;
          serviceTime = 0;
          IDNumber = 0;
     }

     public Customer(int arrTime)  	// one argument constructor
     {
           arrivalTime = arrTime;
           Random rand = new Random();
           serviceTime = rand.nextInt(3)+1;  // 1, 2, or 3 minutes   
      }

      public Customer(int arrTime, int id)  	// otwo argument constructor
      {
           arrivalTime = arrTime;
           setID(id);
           Random rand = new Random();
           serviceTime = rand.nextInt(3)+1;  // 1, 2, or 3 minutes   
      }

      public void setArrivalTime(int arrTime)
      {
           arrivalTime = arrTime;
      }

     public int getArrivalTime()
     {
          return arrivalTime;
     }

     public void setServiceTime(int ser)
     {
          serviceTime = ser;
     }

     public int getServiceTime()
     {
          return serviceTime;
     }
     
     public int getID()
     {
          return IDNumber;
     }

     public void setID( int id )
     {
          IDNumber = id;
     }
}