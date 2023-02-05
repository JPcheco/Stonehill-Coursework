//Jacob Pacheco
//CSC 104-A
//Assignment 12.2.1

import java.util.*;
import java.awt.*;
import javax.swing.*; 

public class Chaos extends JPanel
{   
   Point [] points = new Point[3]; 
 
   Point w = new Point();
   Point v = new Point();
   Point mid = new Point();
 
   Point a, b, c; 
 
   public Chaos ( Point [] p ) 
   {
      points = p;

      // get the point of the orginal triangle
      a = points[0];  
      b = points[1];  
      c = points[2];    
   }

   public void paintComponent(Graphics g) 
   {
      super.paintComponent(g);
            
      draw(g); 
    }
 
   private int randNum ()
   {
      // return a random number 0 -2
      Random r = new Random();
      int n = r.nextInt(3);

      return n;
   }
 
   private Point randomVertex ()
   {
       return points[randNum()];
   }
  
   void draw(Graphics g)
   {
    
      g.setColor(Color.RED);
      drawTriangle(g, a, b, c);

      w = randomVertex();
      for ( int i = 0; i < 10000; i++ )
      {
         v = randomVertex();
         mid = getMidPoint (w, v);
         drawPoint(g, mid);
         w = mid;
      }      
   }
   
   public void drawTriangle (Graphics g, Point p1, Point p2, Point p3)
   {
      int x1 = p1.x;
      int y1 = p1.y;
   
      int x2 = p2.x;
      int y2 = p2.y;

      int x3 = p3.x;
      int y3 = p3.y;
   
      g.drawLine(x1, y1,   x2, y2);
      g.drawLine(x2, y2,   x3, y3);
      g.drawLine(x3, y3,   x1, y1);
   }   

   public Point getMidPoint(Point p1, Point p2)
   {
      int x1 = p1.x;
      int y1 = p1.y;
   
      int x2 = p2.x;
      int y2 = p2.y;

      int a = ( (x1 + x2) / 2) ;
      int b = ( (y1 + y2) / 2) ;
      
      Point m = new Point (a, b);
 
      return m;
   }

   public void drawPoint ( Graphics g, Point p )
   {
      g.drawRect (p.x, p.y, 1, 1);
   }

   public static void main(String[] args)
   {
      JFrame f = new JFrame();
      f.setBounds(0,0,450,450); 
      
      Point [] points = new Point[3]; // make an array of three points

      points[0] = new Point ( 210,10 );
      points[1] = new Point ( 10,410 );
      points[2] = new Point ( 410,410 );
      
      JPanel c = new Chaos (points);
      f.add(c);                        // add panel to center of frame
      
      f.setVisible(true);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   } 
}