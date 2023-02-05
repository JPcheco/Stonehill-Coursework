//Jacob Pacheco
//CSC 104-A
//Assignment 14.3.1

public class Deque1<E> 
{
  
   private class Node // has 2 reference fields , left and right
   {
      private E data;
      private Node left; 
      private Node right;
 
      public Node() // default constructor
      {
         data = (E) new Object();
         left = null;
         right = null;
      }
 
      public Node (E x) // one argument constructor for Node
      {
         data = x;
         left = null;
         right = null;

      }
   } 
   
   
   Node front, rear; // global variables
   int dequeSize;
   
   
   public Deque1() // default constructor, same as a queue
   {
      front = rear = null;
      dequeSize = 0;
   }
 
   public void insertFront(E x)
   {
      Node n = new Node(x);
      
      if (dequeSize == 0) // check special case
      {
         front = rear = n;
      }
      else // not initially empty
      {
         front.left = n;
         n.right = front;
         front = n;
      }
      dequeSize++;
   }
   
   public void insertRear(E x) // same as insert(..) for queue
   {
      Node n = new Node(x);

      if (dequeSize == 0) // check special case
      {
         front = rear = n;
      }
      else // not initially empty
      {
         rear.right = n;
         n.left = rear;
         rear = n;
      }
      dequeSize++;
      }
      
   public E removeFront() // same as remove() for queue
   {

      if (dequeSize == 0)
      {
         System.out.println(" Queue Underflow");
         System.exit(0);
      }
         
      E temp = (E) front.data;
       if ( dequeSize == 1 )
      {
         front = rear = null;
         dequeSize = 0;
      }   
      else
      {
         front = front.right;
         front.left = null;
         dequeSize--;
         if ( dequeSize == 0 )
            front = rear = null;
      }   
      return temp;   
   }
 
   public E removeRear() // same as remove() for queue
   {

      if (dequeSize == 0)
      {
         System.out.println(" Queue Underflow");
         System.exit(0);
      }
      E temp = (E) rear.data;
      if ( dequeSize == 1 )
      {
         front = rear = null;
         dequeSize = 0;
      }   
      else
      {            
         rear = rear.left;
         rear.right = null;
         dequeSize--;
         if ( dequeSize == 0 )
            front = rear = null;
      }      
      return temp;   
   }


   public boolean empty()
   {
      return dequeSize == 0;
   }
 
   public int size()
   { 
      return dequeSize;
   }
   

   public static void main(String[] args) 
   { 
      Deque1<Integer> q = new Deque1<Integer>(); 
 
      for(int i = 0; i <=5; i++) 
         q.insertFront(i); 
 
      for(int j = 10; j <= 15; j++) 
         q.insertRear(j); 
 
      for(int k = 1; k <= 5; k++) 
      { 
         System.out.println(q.removeFront()); 
         System.out.println(q.removeRear()); 
      } 
      System.out.println("size is now "+ q.size()); 
      while(!q.empty()) 
         System.out.println (q.removeRear()); 

 
      System.out.println("size is now "+ q.size()); 
   }
   
}