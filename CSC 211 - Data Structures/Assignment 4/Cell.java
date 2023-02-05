//Jacob Pacheco
//CSC 211
//Cell Class

public class Cell
{
   int X;
   int Y;    
   char value;


   boolean right;
   boolean left;
   boolean up;
   boolean down;
   
   
   
   public Cell (int x, int y)
   {
      X = x;
      Y = y;
      
      // mark each one as not having been checked
      up    = false;
      down  = false;
      left  = false;
      right = false;
   }

   public Cell (int x, int y, char v)
   {
      X = x;
      Y = y;
      value = v;
      
      // mark each one as already checked
      up    = false;
      down  = false;
      left  = false;
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

   public char getValue ()
   {
      return value;
   }
   
   public void setValue (char v)
   {
      value = v;
   }
}