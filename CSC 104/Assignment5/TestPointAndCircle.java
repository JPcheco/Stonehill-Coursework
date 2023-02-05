//Jacob Pacheco
//CSC 104-A
//Assignment 5.1.3

public class TestPointAndCircle
{
   public static void main(String[] args)
   {
      Point a = new Point (3,4);
      Point b = new Point(3,4);
      Point c = new Point();
      Point d = new Point (0,5);
      
      System.out.println("a = " + a);
      System.out.println("b = " +b);
      System.out.println("c = " + c);
      System.out.println("d = " + d);
      
      System.out.println("Distance between "+a +" and "+c+ " is "+ a.distance(c));
      System.out.println("Sum of "+ a +" and " + d +" is "+ a.add(d));
      System.out.println("Point a equals Point b: "+ a.equals(b));
      System.out.println("Point a equals Point d: "+ a.equals(d));
      System.out.println();
      
      Circle x = new Circle(1,a);
      Circle y = new Circle( 1, new Point()); // notice the second argument
      Circle z = new Circle(2,a);
      
      System.out.println("Circle x: " + x);
      System.out.println("Circle y: "+ y); 
      System.out.println("Circle z: "+ z);
      
      System.out.println("Circle x equals Circle y --> "+ x.equals(y));
      System.out.println("Circle x equals Circle z --> "+ x.equals(z));
   } 
}