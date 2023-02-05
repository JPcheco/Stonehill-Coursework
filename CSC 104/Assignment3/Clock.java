public class Clock
{
  private int hours;  
  private int minutes;
    
  public Clock() // default constructor
  {
    SetTime(0,0); 
  }
  
  public Clock (int h)  // default constructor
  {
    SetTime(h,0); 
  }
  
  public Clock (int h, int m) // two argument constructor
  {
    SetTime(h,m); 
  }
   
  public int getHour()     //returns the hour
  {
      return hours;
  }     
          
  public int getMinute()     //returns the minute  
  {
      return minutes;
  }     
       
            
  public void SetTime(int h, int m)     // Sets the time.     
  {
    if ( (h < 0) || (h > 23) )
       h = 0;
    if ( (m < 0) || (m > 59) )
       m = 0;

    hours = h;  
    minutes = m; 
 
  }
         
              
  public void incrementTimer()     //basic incrementer adds 1 minute to the time  
  {
      int x;
      
      if ( (minutes + 1) < 60 )
         minutes++;
      else
      {
         minutes = 0;
         if (( hours + 1) < 24)
            hours++;
         else   
            hours = 0;
      }
      return;
   }   
          
  
  public void incrementTimer(int m)     //overloaded method ; adds x minutes to the time   
  {
      int x, y, z;
      
      x = minutes;
      x = x + m;
      y = x % 60;
      
      minutes = y;
      
      z = m / 60;
      
      hours = hours + z;
      hours = hours % 23;
  
      return;
  }      
    
    
         
  public String toString()     //returns a String in the form x:xx PM eg. "2:53 PM" 0r "12:15 AM"
  {
      StringBuilder sb = new StringBuilder(); 

      int x;
      boolean PM = false;
      
      x = hours;
      if (hours >= 12)
         PM = true;

      if (hours > 12)
         x = hours - 12;
 
      sb.append(x);
      sb.append(":");
      
      if ( minutes < 10 )
          sb.append("0");
      
      sb.append(minutes);
      sb.append(" ");
      
      if (PM == true)
        sb.append("PM");
      else
        sb.append("AM");
 
      return ( sb.toString() );
 
  }
      
   
 }
      