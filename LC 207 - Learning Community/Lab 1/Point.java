//Point class from Tiziana Hernandez
import java.lang.*;
public class Point
{
   private int x;
   private int y;
   
   public Point()
   {
      x=0;
      y=0;
   }
   
   public Point(int x, int y)
   {
       this.x=x;
       this.y=y;
   }
   
   public int getX()
   {
      return x;
   }
   
    public int getY()
   {
      return y;
   }
   
    public void setX(int s)
   {
      x=s;
   }
   
    public void setY(int s)
   {
      y=s;
   }
   
   public void setXY(int s, int t)
   {
      x=s;
      y=t;
   }

   public double distance(Point p)
   {
      double dist= Math.sqrt( Math.pow((this.getX()-p.getX()), 2) +  Math.pow((this.getY()-p.getY()), 2)  );
      return dist;
   }
   
   public Point add(Point p)
   {
      Point w= new Point( (this.getX()+ p.getX()) , (this.getY()+p.getY()) );
      return w;
   }
   
   public boolean equals(Object o)
   {
      if (this.getX()==((Point)o).getX() && this.getY()==((Point)o).getY() )
         return true;
      else
         return false;
   }
   
   public String toString()
   {
      String s= "("+ this.getX() +","+ this.getY() + ")";
      return s;
   }
   
}