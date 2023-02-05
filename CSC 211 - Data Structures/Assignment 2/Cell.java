//Jacob Pacheco
//CSC 211
//Assignment 2

public class Cell
{
   int X;
   int Y;    
   boolean up;
   boolean down;
   boolean left;
   boolean right;

   public Cell ( int x, int y)
   {
      X = x;
      Y = y;
      
      up = false;
      down = false;
      left = false;
      right = false;
   }

   public int getX ()
   {
      return X;
   }
   
   public int getY ()
   {
      return Y;
   }

   public void setX ( int x )
   {
      X = x;
   }

   public void setY ( int y )
   {
      Y = y;
   }

   public boolean getUp ()
   {
      return up;
   }

   public void setUp ( boolean x )
   {
      up = x;
   }
 
   public boolean getDown ()
   {
      return down;
   }

   public void setDown ( boolean x )
   {
      down = x;
   } 
 
   public boolean getLeft ()
   {
      return left;
   }

   public void setLeft ( boolean x )
   {
      left = x;
   }

   public boolean getRight ()
   {
      return right;
   }

   public void setRight ( boolean x )
   {
      right = x;
   }
}