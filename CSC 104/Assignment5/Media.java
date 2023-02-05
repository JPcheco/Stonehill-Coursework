//Jacob Pacheco
//CSC 104-A
//Assignment 5.2.1

public abstract class Media
{
   protected String title;
   
   public Media()
   {
      String t = new String();
      title = t;
   }
   
   public Media(String t)
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

class Book extends Media
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
}

class DVD extends Media
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
}

class Magazine extends Media
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
}