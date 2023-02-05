public class Queue<E> 
{
    private class Node
    {
        E data;
        Node next;
    }

    Node front, rear;
    int size;

    public Queue()
    {
        front = rear = null;
        size = 0;
    }

    public int size()
    {
        return size; 
    }


    public void insert(E x)
    {
        Node p = new Node();
        p.data = x;
        p.next = null;

        if (size == 0)
            front = rear = p;
        else
        {
            rear.next = p;
            rear = p;
        }
        size++;
    }

    public E remove()
    {
        if(size == 0)
            return null; // empty queue
        E temp = front.data;
        front = front.next;
        size--;
        if (size == 0)
            rear = null;
        return temp;
    }

    public boolean empty()
    {
        return size == 0;
    }

    public int getSize()
     {
          return size;
     }

     public E peek()
     {
            if(size == 0)
                   return null; // empty queue
            E temp = front.data;
            return temp;
        }
}