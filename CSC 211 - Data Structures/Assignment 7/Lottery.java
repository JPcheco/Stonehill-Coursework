// Jacob Pacheco
// CSC 211
// Assingment 7.1.1

public class Lottery implements Comparable
{
  private String  name; //Student's name
  private int priority; //lottery number
  
  public Lottery()
  {
    name = null;
    priority = 0;
  }
  
  public Lottery(String name, int priority)
  {
    this.name = name;
    this.priority = priority;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public int getPriority()
  {
    return priority;
  }
  
  public void setPriority(int priority)
  {
    this.priority = priority;
  }
  
  public String toString() 
  { 
     return String.format( getName() + "  " + getPriority() ); 
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
    int thisPriority;
    int passedPriority;
   
    thisPriority = getPriority();
    
    passedPriority = ((Lottery)o).getPriority();  
    
    if(thisPriority == passedPriority)
       return 0;     
    else if(thisPriority > passedPriority)
       return 1;
    else
       return -1;
  }
}