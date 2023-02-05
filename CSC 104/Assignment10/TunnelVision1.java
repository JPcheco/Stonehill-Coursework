//Jacob Pacheco
//CSC 104-A
//Assignment 10.4.2

import java.util.*;
import java.awt.*;
import javax.swing.*; 

public class TunnelVision1 extends JPanel
{
   Point a, b, c, d;    // corners of the square 
   int count;           // the number of squares to be drawn
   
   public TunnelVision1 (int cnt) 
   {
      a = new Point (200, 200);
      b = new Point (200, 600);
      c = new Point (600, 600);
      d = new Point (600, 200);
 
      count = cnt;
   } 
   
   public void paintComponent(Graphics g) 
   {
      super.paintComponent(g);
      draw (g); 
   }
 
   void draw(Graphics g)
   {
      g.setColor(Color.RED);
      drawSquare(g, a, b, c, d);
   }

   public void drawSquare (Graphics g, Point p1, Point p2, Point p3, Point p4)
   {  
      int x1 = p1.x;
      int y1 = p1.y;
   
      int x2 = p2.x;
      int y2 = p2.y;

      int x3 = p3.x;
      int y3 = p3.y;
   
      int x4 = p4.x;
      int y4 = p4.y;
   
      g.drawLine(x1, y1,   x2, y2);
      g.drawLine(x2, y2,   x3, y3);
      g.drawLine(x3, y3,   x4, y4);
      g.drawLine(x4, y4,   x1, y1);
      
      count--;
      if ( count > 0 )
      {
         a = getHalfwayPoint(p1, p2);
         b = getHalfwayPoint(p2, p3);
         c = getHalfwayPoint(p3, p4);
         d = getHalfwayPoint(p4, p1);
   
         drawSquare(g, a, b, c, d);
      }      
   }   

   public Point getHalfwayPoint(Point p1, Point p2)
   {   
      int x1 = p1.x;
      int y1 = p1.y;
   
      int x2 = p2.x;
      int y2 = p2.y;

      int a = (((x2 - x1) / 2) + x1);
      int b = (((y2 - y1) / 2) + y1);
      
      Point h = new Point (a, b);
 
      return h;
   }
}