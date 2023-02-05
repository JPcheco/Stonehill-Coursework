//Jacob Pacheco
//CSC 104-A
//Assignment 8.2.5

public class TestLists
{
   public static void main(String[] args)
   {
      LIFO s = new LIFO();
      System.out.println("LIFO: ");
      s.insert("B");
      s.insert("L"); 
      s.insert("M");
      s.insert("C");
      System.out.println(s.remove());
      System.out.println(s.remove());
      s.insert("P");
      s.insert("N");
      System.out.println(s.remove());
      System.out.println(s.remove());
      s.insert("D");
      System.out.println(s.remove());
      System.out.println(s.remove());
      System.out.println(s.remove());
      System.out.println(s.remove());
      
      System.out.println();
      
      FIFO q = new FIFO();
      System.out.println("FIFO: ");
      q.insert("B");
      q.insert("L");
      q.insert("M");
      q.insert("C");
      System.out.println(q.remove());
      System.out.println(q.remove());
      q.insert("P");
      q.insert("N");
      System.out.println(q.remove());
      System.out.println(q.remove());
      q.insert("D");
      System.out.println(q.remove());
      System.out.println(q.remove());
      System.out.println(q.remove());
      System.out.println(q.remove());
      
      System.out.println();
      
      PRIORITY pq = new PRIORITY();
      pq.insert("B");
      pq.insert("L");
      pq.insert("M");
      pq.insert("C");
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      pq.insert("P");
      pq.insert("N");
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      pq.insert("D");
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      System.out.println(pq.remove());
      System.out.println(pq.remove()); 
   }
} 