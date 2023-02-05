//Jacob Pacheco
//CSC 104-A
//Assignment 14.2.1

public class Deque<E> 
{
   private E[] items;
   private int dequeSize;
   int front, rear;
   int max;
   
   public Deque() // default constructor, same as a queue
   {
      items =(E[]) new Object[10];
      dequeSize= 0;
      front = rear = -1;
      max = 10;
   }
 
   public Deque(int max) // one argument constructor, same as queue
   {
      this.max = max;
      items =(E[]) new Object[max]; 
      dequeSize = 0;
      front = rear = -1; 
   }
   
   public void insertRear(E x) // same as insert(..) for queue
   {
      if ( dequeSize == max) // queue is full
      {
         System.out.println(" Queue Overflow");
         System.exit(0); 
      }
      rear = (rear + 1) % max; 
      items[rear] = x;
      dequeSize++;
      if (dequeSize == 1) 
        front = rear;
      }
      
   public E removeFront() // same as remove() for queue
   {
      if (dequeSize == 0)
      {
      System.out.println(" Queue Underflow");
      System.exit(0);
      }
      E temp = items[front];
      dequeSize--;
      if ( dequeSize == 0)
         front = rear = -1;
      else
         front = (front + 1) % max; 
      return temp;
      }
 
   public E peekFront() // same as peek() for queue
   {
      if (dequeSize == 0)
      {
      System.out.println(" Queue Underflow");
      System.exit(0);
      }
      return items[front];
   }
 
   public boolean empty()
   {
      return dequeSize == 0;
   }
 
   public int size()
   { 
      return dequeSize;
   }
   
   public void insertFront(E x)
   {
     if (dequeSize == max)
      {
      System.out.println(" Queue Overflow");
      System.exit(0);
      }
      
      if ( front == -1 )
         front = (max - 1);
      else 
         front = (front - 1) % max; 

      items[front] = x;
      dequeSize++;
      if (dequeSize == 1) 
         rear = front;
   }

   public E removeRear()
   {
      if (dequeSize == 0)
      {
         System.out.println("Stack Underflow");
         System.exit(0);
      }

      E temp = items[rear];
      dequeSize--;
      if ( dequeSize == 0)
         front = rear = -1;
      else
         {
         rear = (rear - 1) % max; 
         if ( rear == -1)
            rear = max - 1; 
         }
      return temp;
   }
   
   public E peekRear()
   { 
      if (dequeSize == 0)
      {
         System.out.println("Stack Underflow");
         System.exit(0);
      }

      return items[rear];
   }
   
   public static void main(String[] args) 
   { 
      Deque<Integer> q = new Deque<Integer>(12); 
 
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