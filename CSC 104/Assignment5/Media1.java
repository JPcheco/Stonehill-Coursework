//Jacob Pacheco
//CSC 104-A
//Assignment 5.3.2

public abstract class Media1
{
   protected String title;
   
   public Media1()
   {
      String t = new String();
      title = t;
   }
   
   public Media1(String t)
   {
      title = t;
   }
   
   public String getTitle()
   {
      return title;
   }
   
   public void setTitle(String t)
   {
      title = t;
   }
}

class Book1 extends Media1
{
   protected String author;
   
   public String getAuthor()
   {
      return author;
   }
   
   public void setAuthor(String a)
   {
      author = a;
   }
   
   public String toString()
   {
      return "Title of "+getTitle()+", Author: " +getAuthor();
   }
}

class DVD1 extends Media1
{
   protected int runtime;
   
   public int getRuntime()
   {
      return runtime;
   }
   
   public void setRuntime(int r)
   {
      runtime = r;
   }
   
   public String toString()
   {
      return "Title of "+getTitle()+", Runtime is " +getRuntime()+ " minutes";
   }
}

class Magazine1 extends Media1
{
   protected int issue;
   
   public int getIssue()
   {
      return issue;
   }
   
   public void setIssue(int i)
   {
      issue = i;
   }
   
   public String toString()
   {
      return "Title of "+getTitle()+", Issue #" +getIssue();
   }
}