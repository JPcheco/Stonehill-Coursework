// Jacob Pacheco
// CSC 211
// Assingment 7.1.2

import java.util.*;
import java.io.*;

public class PriorityQueue<E extends Comparable>
{
   E[] A; //holds the data
   int size; // number of items in the queue
   int max; //maximum size of the queue
   
   public PriorityQueue() //default constructor
   {
      A = (E[]) new Comparable[100];
      size = 0;
      max = 100;
   }
   
   public PriorityQueue(int Maximum)
   {
      A = (E[]) new Comparable[Maximum];
      size = 0;
      max = Maximum;
   }
   
   public void heapify(int i)
   {   
      // i is the index of into the array (root of the tree to build)
   
      int smallest = i; // Initialize smallest as root
      
      int left = 2 * i + 1;   // left =  2*i + 1
      int right = 2 * i + 2;   // right = 2*i + 2
 
      // If left child is smaller than root
      if ( (left < size) && (A[left].compareTo(A[smallest]) < 0) )
         smallest = left;
 
      // If right child is smaller than the smallest so far
      if ( (right < size) && (A[right].compareTo(A[smallest]) < 0) )
         smallest = right;
 
      // If smallest is not root, swap smallest and root
      if (smallest != i) 
         {
         swap( i, smallest );
 
         // now recursively heapify the affected sub-tree
         heapify(smallest);
         }
    }
   
   public void swap (int x, int y)
   {
      E temp;
      
      temp = A[x];
      A[x] = A[y];
      A[y] = temp; 
   }
   
   public void insert(E key)
   {
      //check for overflow
      if(getSize() == max)
         System.out.println("overflow");
    
      // sort the element into the array
      int index = getSize();
      
      // putthe new element in the end of the array
      A[index++] = key;
      setSize(index);
        
      for (int i = index; i >= 0; i--) 
         heapify(i);
   }     
   
   public int parent(int i)
   {
      return (i - 1) / 2;
   }
   
   public E remove()
   {
      int sizee = getSize();
      
      //check for underflow
      if(sizee < 1)
         System.out.println("heap underflow");
      
      E top = A[0]; // top element
               
      A[0] = A[sizee - 1]; // move last element to top
      size--;
      
      heapify(0); //adjust heap
      return top;
   }
   
   public boolean empty()
   {
      if(getSize() == 0)
         return true;
      else
         return false;
   }
   
   public int getSize()
   {
      return size;
   }
   
   public void setSize(int x)
   {
      size = x;
   }
   
   void printHeap()
   {
      System.out.println("Heap: ");
 
      for (int i = 0; i < getSize(); ++i)
         System.out.print ( A[i] + " ");
      System.out.println();;
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