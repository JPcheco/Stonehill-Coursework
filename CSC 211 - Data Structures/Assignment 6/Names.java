public class Names implements Comparable
{
  private String name;
  private String count;
  
  public Names()
  {
    name = count = null;
  }
  
  public Names(String name, String count)
  {
    this.name = name;
    this.count = count;
  }
  
  public String getName()
  {
    return name;
  }
  public String getCount()
  {
      return count;
  }
  
  public String toString()
  {
    return "Name: "+ name+ " Count : "+ count; 
  }
  
  public int compareTo(Object o)
  {
    return name.compareTo(((Names)o).name);
  }
}