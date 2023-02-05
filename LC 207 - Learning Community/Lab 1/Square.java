import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.lang.Math;

public class Square extends JPanel
{
   
   //four global coordinates of a square: 320 unit length of sides 
      static Point a= new Point(0,0);
      static Point b= new Point(320,0);
      static Point c= new Point(320,320); 
      static Point d= new Point(0,320);
      static Point e,f,gg,h;//midpoints
      static Point v; // selected point
      static Point p;
      static double two,xval,yval;
   
   //constructor
   public Square()
   {
      setBackground(Color.WHITE);
   } 
   
   
   public void paintComponent(Graphics g)
   {
      //call paintComponent of the parent
      super.paintComponent(g); 
      
      
      //find midpoints of these coordinates
       e = getMidpoint(a,b);
       f = getMidpoint(b,c);
       gg = getMidpoint(c,d);
       h = getMidpoint(d,a);
      
      Point sel= FindSel();
     
      for(int i=0; i<990000;i++ )
      {
         
          FindVert();//one of 4 corners 
             
         // 1/3 the distance between sel and v(the two random points selected)
         sel= getNewPoint(sel,v);
         
         draw(sel,g);
         //setVisible(true);
         
      }

   }

   public void draw(Point z, Graphics g)
   {
      g.setColor(Color.RED);
      
      g.drawRect(z.getX(),z.getY(), 1, 1);         
   }
   
   
   public static Point getMidpoint(Point a, Point b)
   {
      Point p = new Point ((a.getX()+b.getX())/2, (a.getY()+b.getY())/2);
      return p;
   }
   
   public static Point getNewPoint(Point a, Point b)
   {
       two= (2/3.0);
       xval= (b.getX()-a.getX());
       yval= (b.getY()-a.getY());
       p = new Point((int)(a.getX()+(two*xval)), (int)(a.getY()+(two*yval)));////
       return p;
   }

   public static Point FindSel()
   {
      //get one of random points between the 8 points
      int r= (int)(Math.random()*8)+1; //random number 1-8
      Point sele=a;//just to initialize but will change in switch
      
      switch (r)
      {
         case 1: sele= a;break;
         case 2: sele= b;break;
         case 3: sele= c;break;
         case 4: sele= d;break;
         case 5: sele= e;break;
         case 6: sele= f;break;
         case 7: sele= gg;break;
         case 8: sele= h;break;
         default: break;
      }
     return sele;
   }
   
   public static void FindVert()
   {
      //random number between 4 vertices
      int rand= (int)(Math.random()*4)+1; //random number 1-4      
      
      switch (rand)
      {
         case 1: v= a;break;
         case 2: v= b;break;
         case 3: v= c;break;
         case 4: v= d;break;
         default: break;
      }
   }

   public static void main(String[] args)
   {
      JFrame fr = new JFrame("Square Fractal"); //get a frame
      fr.setBounds(0,0, 450,450);
      
      JPanel panel = new Square(); //calls constructor
      fr.add(panel); //add the panel to the frame
      fr.setVisible(true);
   }
   
}