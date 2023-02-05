//Jacob Pacheco
//CSC 104-A
//Assignment 5.1.1

public class Point
{
   private int x, y;
   
   public Point() //default constructor
   {
      x = y = 0;
   } 
   
   public Point(int x, int y) //2 para constructor
   {
      this.x = x;
      this.y = y;
   }
   
   public int getX()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }
   
   public void setX(int x)
   {
      this.x = x;
   }
   
   public void setY(int y)
   {
      this.y = y;
   }
   
   public double distance(Point q) // finds the Hypot.
   {
      double x1 = (double) q.getX();
      double y1 = (double) q.getY();
      double x2 = (double) this.getX();
      double y2 = (double) this.getY();
      
      return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
   }
   
   public Point add(Point q) //adds to point together
   {
      int x1, x2, y1, y2;
      Point p = new Point();
      
      x1 = q.getX();
      y1 = q.getY();
      x2 = this.getX();
      y2 = this.getY();
      
      p.setX(x1 + x2);
      p.setY(y1 + y2);
      
      return p;
   }
   
   public boolean equals(Object o) //checks to see if they are the same as one another
   {
      return ( (getX() == ((Point)o).getX()) &&
               (getY() == ((Point)o).getY()) );
   }
   
   public String toString() // return the point in the form (x,y)
   {
      return "("+getX()+","+getY()+")";
   }
}