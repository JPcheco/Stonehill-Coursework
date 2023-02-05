import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class SierpienskiRecursive extends JPanel
{
      

   public SierpienskiRecursive() 
   {
      setBackground(Color.WHITE);       
   } 
   
    public void paintComponent(Graphics g)
   {
      //call paintComponent of the parent
      super.paintComponent(g); 
      //call initial sierpienski method 
      sierpienski(210,10,10,410,410,410,10, g);
   }
   

   public void sierpienski(int x1,int y1, int x2, int y2, int x3, int y3, int depth, Graphics g)
   {
     if(depth>0)
     {
      g.setColor(Color.RED);
     
     //draw initial points
     // g.drawLine(x1, y1, x2,y2);
     // g.drawLine(x2, y2,x3 ,y3);
     // g.drawLine(x3, y3, x1 ,y1);
        g.drawRect(x1, y1, 1 ,1);
        g.drawRect(x2, y2, 1 ,1);
        g.drawRect(x3, y3, 1 ,1);
      
      g.setColor(Color.RED);
    

     //Midpoint calculations
     int x4= (x2+x1)/2;
     int y4= (y1+y2)/2;
     int x5= (x3+x1)/2;
     int y5= (y3+y1)/2;
     int x6= (x3+x2)/2;
     int y6= (y3+y2)/2;
 

     //recursive call using midpoints
     sierpienski(x1,y1, x4, y4,x5, y5,depth-1,g);
     sierpienski(x4,y4, x2, y2,x6, y6,depth-1,g);
     sierpienski(x5,y5, x6, y6,x3, y3,depth-1,g);
     
     
     
     }
   }


   public static void main(String[] args)
   {
      JFrame f = new JFrame("Sierpienski Recursive"); // get a frame
      f.setBounds(0,0, 450,450);
      
      JPanel c= new SierpienskiRecursive();
      f.add(c); //add the panel to the frame
      f.setVisible(true);
   }




}