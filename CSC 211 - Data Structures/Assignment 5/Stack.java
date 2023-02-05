//Jacob Pacheco
//CSC 211
//Stack Class

class Stack<E> 
{
    private class Node
    {
      E data;
      Node next;
      public Node()
      {
        data = null;
        next = null;
      }
      public Node(E x)
      {
        data = x;
        next = null;
      }
    }
    
    Node top;
    int numItems;
    
    public Stack()
    // default constructor; creates an empty stack
    {
        top = null;
        numItems = 0;
    }
    
    public void push(E x)
    {
        Node p = new Node(x);
        p.next = top;
        top = p;
        numItems++;
    }

    public E pop()
    {
       if (numItems == 0)
       {
           System.out.println("Stack Underflow");
           System.exit(0);
        }
       E temp = top.data;
       top = top.next;
       numItems--;
       return temp;
    }

    public boolean empty()
    {
        return numItems == 0;
    }

    public int size()
    {
        return numItems;
    }

    public E peek()
    {
       if (numItems == 0)
       {
           System.out.println("Stack Underflow");
           System.exit(0);
        }
        E temp = top.data;
        return temp;
    }
}