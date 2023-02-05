public class Stack<E> 
{
     private E[] items;
     int top;
     int numItems;
     int maxStack;

    public Stack()
     // default constructor; creates an empty stack
    {
       
        items = (E[]) new Object [10]; // capacity is 10
        top = -1;
        numItems = 0;
        maxStack = 10;
    }

    public Stack(int max)
    //one argument constructor, creates a stack capacity max
    {
        items = (E[]) new Object [max];
        top = -1;
        numItems = 0;
        maxStack = max;
    }

    public void push(E x)
    {
     if (numItems == maxStack)
     {
      System.out.println("Stack Overflow");
      System.exit(0);
    }
    top++;
    items[top] = x;
    numItems++;
 }

 public E pop()
 {
  if (numItems == 0)
  {
   System.out.println("Stack Underflow");
   System.exit(0);
  }
  E temp = items[top];
  top--;
  numItems--;
  return temp;
 }

 public E peek()
 {
  if (numItems == 0)
  {
   System.out.println("Stack Underflow");
   System.exit(0);
  }
  return items[top];
 }

 public boolean empty()
 {
  return numItems == 0;
 }
 public int size()
 {
  return numItems;
 }

}