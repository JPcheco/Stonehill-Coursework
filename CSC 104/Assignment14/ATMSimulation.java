//Jacob Pacheco
//CSC 104-A
//Assignment 14.4.1

import java.util.*;

public class ATMSimulation
{
   Customer customer;
   int ATM1isAvailable;       // time ATM1 is next available
   int ATM2isAvailable;       // time ATM2 is next available
   int numArrivals;           // number of arrivals in any minute
   int arrivalNumber;
   Queue<Customer> queue1;
   Queue<Customer> queue2;

     // statistics
     int totalWaitingTime;      // for all customers
     int numCustomersServed;
     int numCustomerArrivals;

     public ATMSimulation()    // default constructor
     {
          numArrivals = 0;
          numCustomerArrivals = 0;
          totalWaitingTime = 0;
          numCustomersServed = 0;
          arrivalNumber = 0;
          
          
          queue1 = new Queue<Customer>();
          ATM1isAvailable = 0;  // assume ATM1 is  available at time 0
           
          queue2 = new Queue<Customer>();
          ATM2isAvailable = 0;  // assume ATM2 is  available at time 0         
     }

    int magic = 1;  

     private int getArrivals()
     // generate a random integer in the range 0..9
     //  if the random integer is 0,1,2,3,or 4, then no arrivals ( 50% chance)
     //  if the random integer is 5,6,7, or 8, then 1 arrival (40 % chance)
     //  if the random integer is 9,  then 2 arrivals (10% chance)
     {   
          Random rand = new Random();
          int randomInteger = rand.nextInt(10); // 0..9
          if ( randomInteger <= 4)  // 0..4
               return 0;            // 50% chance of no arrivals
          if ( randomInteger <= 8)  // 5..8
               return 1;            // 40% chance of a single arrival
          return 2;                 // 10% chance of 2 arrivals
     }

     private void displayStatistics()
     {
          System.out.println("Number of customers served "+ numCustomersServed);
          System.out.println("Average wait " + (float)((float)totalWaitingTime/(float)numCustomersServed) + " minutes");
          System.out.println("Customers left in queue: "+ ( queue1.size() + queue2.size() ) );
     }

     private void displayPerMinuteStatistics(int t, int a)
     {
         System.out.println("Time : " + t + "  Number of arrivals : " + a);
         System.out.println("  ATM-1 available at " + ATM1isAvailable +  " ATM-2 available at " + ATM2isAvailable ); 
     }

     public int getTimeToRun()
     {
      Scanner input = new Scanner(System.in);
      
      System.out.println("Enter time (minutes) for the simulation");
      System.out.print(" A low number like 8 is best: ");
      
      int TimeToRun = input.nextInt();
      
      return TimeToRun;
     }

     public void simulate()
     {
     
         // add a call to a method to get the number of minutes to run
         int timeToRun = getTimeToRun();
         
         System.out.println();
         System.out.println("       A T M   S I M U L A T I O N -- "+timeToRun+" M I N U T E S");
         System.out.println();
     
          for (int time = 0; time < timeToRun; time++) 		      // for each minute
          {
               numArrivals = getArrivals();                	// how many customers arrive?
               
               displayPerMinuteStatistics(time, numArrivals);
               
               for (int i = 1; i <= numArrivals; i++)  	   // place each arrival into the queue
               {
                  arrivalNumber++;
                  
                  // find the shortest line and go wait in it or choose 1 if they are equal
                  if ( queue1.size() <= queue2.size() )
                  {   
                      queue1.insert( new Customer(time, arrivalNumber ) );
                    
                      System.out.println("          " + queue1.peek().getID() + ". service time " + queue1.peek().getServiceTime() + " Enters Queue-1");  
                  }    
                  else
                  {                     
                      queue2.insert( new Customer(time, arrivalNumber) );
                       
                      System.out.println("          " + queue2.peek().getID() + ". service time " + queue2.peek().getServiceTime() + " Enters Queue-2"); 
                   }
               }     
                   
               if (!queue1.empty() && ATM1isAvailable <= time)
               {
                    customer = queue1.remove(); 	// remove the next customer from the waiting line
                    // Determine the next time that the ATM is available: current time+ service time
                    ATM1isAvailable = time + customer.getServiceTime();
                    
                    System.out.println("             " + "customer number " + customer.getID() + " goes to ATM-1");
                       
                    // how long did this customer wait?
                    int timeCustomerWaited = time - customer.getArrivalTime();
                    totalWaitingTime += timeCustomerWaited;   // add customer's wait to total wait
                    numCustomersServed++;
               }
              if (!queue2.empty() && ATM2isAvailable <= time)
              {
                  customer = queue2.remove(); 	// remove the next customer from the waiting line
                   // Determine the next time that the ATM is available: current time+ service time
                   ATM2isAvailable = time + customer.getServiceTime();
                   
                   System.out.println("             " + "customer number " + customer.getID() + " goes to ATM-2");
                    
                   // how long did this customer wait?
                   int timeCustomerWaited = time - customer.getArrivalTime();
                   totalWaitingTime += timeCustomerWaited;   // add customer's wait to total wait
                   numCustomersServed++;
               }             
             
               System.out.println();
                           
            }
          displayStatistics();
      }

     public static void main(String[] args)
     {
          ATMSimulation atmSim = new ATMSimulation();
          atmSim.simulate();
     }
}
