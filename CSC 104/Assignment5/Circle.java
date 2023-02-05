//Jacob Pacheco
//CSC 104-A
//Assignment 5.1.2

public class Circle
{
   private Point center; // Circle has-a Point
   private int radius;
   
   public Circle() //base constructor
   {
      center = new Point();
      radius = 0;
   }
   
   public Circle (int r, Point c) //2 param. constructor
   {
      radius = r;
      center = c;
   }
   
   public int getRadius()
   {
      return radius;
   }
   
   public Point getCenter()
   {
      return center;
   }
   
   public void setRadius(int r)
   {
      radius = r;
   }
   
   public void setCenter(Point c)
   {
      center = c;
   }
   
   public double getArea() // gets the area
   {
      return radius * radius * Math.PI;
   }
   
   public boolean equals(Object o) // compares 2 circcles area's to one another
   {
      return (getArea() == ((Circle)o).getArea());
   }
   
   public String toString() // return the radius, center, and the area with labels
   {
      return "The circle has a radius of "+getRadius()+", a center of "+getCenter()+", and an area of "+getArea();
   } 
}