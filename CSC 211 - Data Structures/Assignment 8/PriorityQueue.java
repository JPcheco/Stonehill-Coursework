// Jacob Pacheco
// CSC 211
// Assingment 7.1.2

import java.util.*;
import java.io.*;

public class PriorityQueue<E extends Comparable>          
{
   E[] a;   // holds the data
   int size;  // number of items in the queue
   int max;   // maximum size of the queue


   public PriorityQueue()   
   // default constructor – this is tricky
   {
      a = (E[]) new Comparable[1000];
      size = 0;
      max = 1000;
   }

   public PriorityQueue(int maximum) 
   // one argument constructor
   {      
      a = (E[]) new Comparable[1000];
      size = 0;
      max = 1000;  
      
   }
   
   
   public void swap (int x, int y)
   {
      E temp;
      
      temp = a[x];
      a[x] = a[y];
      a[y] = temp;  
   }
   
   void printHeap()
   {
      System.out.println("Heap: ");
 
      for (int i = 0; i < size; ++i)
         System.out.print ( a[i] + " ");
      System.out.println();;
   }
   
   public void heapify(int i)
   {   
      // i is the index of into the array (root of the tree to build)
   
      int smallest = i; // Initialize smallest as root
      
      int left = 2 * i + 1;   // left =  2*i + 1
      int right = 2 * i + 2;   // right = 2*i + 2
 
      // If left child is smaller than root
      if ( (left < size) && (a[left].compareTo(a[smallest]) <= 0) )
         smallest = left;
 
      // If right child is smaller than the smallest so far
      if ( (right < size) && (a[right].compareTo(a[smallest]) <= 0) )
         smallest = right;
 
      // If smallest is not root, swap them
      if (smallest != i) 
         {
         swap( i, smallest );
 
         // Recursively heapify the affected sub-tree
         heapify(smallest);
         }
    }
   
   public void buildHeap()
   {
      if (size < 2)  
         return;
   
      // Index of last non-leaf node
      int index = (size / 2) - 1;
 
      // Perform reverse level order traversal
      // from last non-leaf node and heapify
      // each node
      for (int i = index; i >= 0; i--) 
         heapify(i);
      
   }
   
 
   public E remove1()
   {  
      if ( size == 0 )
         return null;
  
      E temp = a[0];
           
      size--;     
      for ( int i = 0; i < size; i++)
         a[i] = a[i+1];   
          
      a[size] = null;    
          
      return temp;
      
   }
   
   
   public E remove()
   {
      int sizee = getSize();
      
      //check for underflow
      if(sizee < 1)
         System.out.println("heap underflow");
      
      E top = a[0]; // top element
               
      a[0] = a[sizee - 1]; // move last element to top
      size--;
      
      heapify(0); //adjust heap
      return top;
   }


   public void insert( E key)
   {   
      
      if ( size == max )
         return;
   
      a[size] = key;
      size++;
      
      buildHeap();
   
   }
 
   public int getSize()
   { 
      return size;
   }

   public boolean empty()
   {
      if ( size == 0 )
         return true;
      else
         return false;                          
   }   
   /*
   public static void main(String[] args) throws IOException
   {
      Scanner input = new Scanner(System.in);
      
      PriorityQueue<Lottery> pq = new PriorityQueue<Lottery>(100);
      
      String name;
      int priority;
      
      System.out.println(" Enter name and priority XXX and 0 to end");
      System.out.print("Name :");
      name = input.next();
      System.out.print("Priority :");
      priority = input.nextInt();
      
      while (! name.equals("XXX"))
      {
         pq.insert(new Lottery(name, priority));
         //pq.printHeap();    // debug
         
         System.out.print("Name  :");
         name = input.next();
         System.out.print("Priority  :");
         priority = input.nextInt();
      }
      
      while (!pq.empty())
         System.out.println(pq.remove());
   } 
   */
   
   
}