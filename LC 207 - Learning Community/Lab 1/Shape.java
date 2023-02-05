import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Shape extends JPanel
{
      

   public Shape() 
   {
      setBackground(Color.WHITE);       
   } 
   
    public void paintComponent(Graphics g)
   {
      //call paintComponent of the parent
      super.paintComponent(g); 
      //call initial draw method 
      
      draw(10,10,410,10,410,410,10,410,5, g);
   }
   

   public void draw(int x1,int y1, int x2, int y2, int x3, int y3,int x4, int y4, int depth, Graphics g)
   {
     if(depth>0)
     {
      
     //draw initial points
     Graphics2D g2= (Graphics2D)g;
      g2.setStroke(new BasicStroke(10));
       g2.setColor(Color.BLUE);

      g2.drawLine(x1, y1, x2,y2);
      
      g2.drawLine(x2, y2,x3 ,y3);
      g2.drawLine(x3, y3, x4 ,y4);
      
      g2.drawLine(x4, y4, x1, y1);
      
     //Midpoint calculations
     int x5= (x1+x2)/2;
     int y5= (y1+y2)/2;
     int x6= (x2+x3)/2;
     int y6= (y2+y3)/2;
     int x7= (x3+x4)/2;
     int y7= (y3+y4)/2;
     int x8= (x4+x1)/2;
     int y8= (y4+y1)/2;

     g2.setColor(Color.GREEN);
     //recursive call using midpoints
     draw(x1, y1, x5, y5, x7, y7,  x4, y4,depth-1,g);
     
     draw(x5, y5, x2, y2, x3, y3, x7, y7,depth-1,g);
     draw(x2, y2, x6, y6, x8, y8, x1, y1,depth-1,g);
     
     draw(x6, y6, x3, y3, x4, y4, x8, y8,depth-1,g);
     
     }
   }


   public static void main(String[] args)
   {
      JFrame f = new JFrame("Cool Fractal"); // get a frame
      f.setBounds(0,0, 450,450);
      
      JPanel c= new Shape();
      f.add(c); //add the panel to the frame
      f.setVisible(true);
   }




}