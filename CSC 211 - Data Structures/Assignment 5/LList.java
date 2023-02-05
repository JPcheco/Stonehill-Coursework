public class LList <E> 
{
     private class Node   // an inner class
     {
          private E data;
          private Node next;

          public Node() // default constructor
          {
               data = null;
               next = null;
          }

          public Node(E x)  // two argument constructor
          {
               data = x;
               next = null;
          }
     }     // end Node


    private  Node front, rear;
    protected  int length;  // the size of the list

    public LList()  // default constructor
    {
        rear = front = null;
        length = 0;
    }

    public void clear()                  // makes the list empty
    {
        front = rear = null;
        length = 0;
    }
 
    public void add(E x)        // adds x to the end of the list
    {
        Node p = new Node(x);      // instantiate a new node referenced by p
        if (rear == null)                  // if list is initially empty
            front =rear = p;               // the list has just one node
        else
        {
            rear.next = p;                 // places the Node referenced by p at the end
            rear = p;
        }
        length++;
     }

    public void add(int index,  E x)    // adds x to list at position index
    {
          if (index > length )  // index out of range
          {
               System.out.println("Out of range in add(int index, E x)");
               System.exit(0);
          }
          Node p = new Node(x);    // instantiate a new node referenced by p

          // add to the front of the list
          if (index == 0)
          {
               p.next = front;      // place the address of the first nod into the new node

               front = p;             // front references the new node
               if (rear == null)     // if list was initially empty
                    rear = front;      // front and rear reference the single node of the list
               length++;
               return;
          }

          // add to the end of the list
          if (index == length)
          {
               add(x);
               return;
          }

          // addition is neither at front nor rear
          Node q = front;
          for (int i = 0; i < index-1; i++)      // point  q to the node at position index
               q = q.next;
          Node r = q.next;                        // r references the node following q (could be null)
          q.next = p;
          p.next = r;
          length++;
     }

    public boolean contains (E x)       // returns true if x is a member of the list
    {
        Node p = front;
        for (int i = 0; i < length; i++)      // could also use Ò(while p.next != null)Ó
        {
            if ( x.equals(p.data))
                return true;
            p = p.next;
        }
        return false;                              // search unsuccessful
    }

    public E  get (int index)          // returns data at position index
    {
        if (index >= length)           // if index is out of bounds
        {
            System.out.println("Error in get (int index)");
            System.exit(0);
        }
        Node p = front;
        for (int i = 0 ; i < index; i++)
            p = p.next;                             // move through the list, node by node
        return p.data;
    }

    public boolean isEmpty()           // returns true if list is empty
    {
          return length == 0;
     }
 
   public boolean remove (E x)    // removes first occurrence of x;
                                                         // returns true if successful
       {
           Node p = front;
           Node q = null;
           while (!(p == null) && !x.equals(p.data))    // look for x
           {
               q = p;
               p = p.next;
           }
           if  (p == null)         // not found
               return false;
           if (!(q == null))      // if x is in the first node q is null
                  q.next = p.next;
             if (p == front)
                  front= front.next;
             if ( p == rear)
                  rear = q;
             length--;
             return true;
     }

     public E remove (int index)  // removes and returns data at position index
     {
          if (index >= length)  // index out of bounds
          {
               System.out.println("Error in remove (int index)");
               System.exit(0);
          }
          Node p = front;
          Node q = null;
          for ( int i = 0; i < index; i++)    // q follows p down the list
          {
               q = p;
               p = p.next;
          }
           if (!(q == null))   // if not removing the first node
                                              // q  follows  p, so q is null when p is the first node.
               q.next = p.next;
          if (p == front)
               front= front.next;
          if ( p == rear)
               rear = q;
          length--;
          return p.data;
     }
 
     public E set (int index, E x)  //  sets data at position index to x
     {
          if (index >= length)   // index out of bounds
          {
               System.out.println("Error in get (int index)");
               System.exit(0);
          }
          Node p = front;
          for ( int i = 0; i < index; i++)
               p = p.next;
          E temp = p.data;
          p.data = x;
          return temp;
     }

     public int size()  // returns the number of data on the list
     {
          return length;
     }
     
     public void traverse()
     {
       if (length == 0)
       {
         System.out.println("Empty list");
         System.exit(0);
       }
       Node p = front;
       while (p != null)
       {
         System.out.println(p.data);
         p = p.next;
       }
     }
}