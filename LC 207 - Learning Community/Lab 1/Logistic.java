import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Logistic extends JPanel
{
   double x=.5;
   double y;
   
    public Logistic()
      {
         setBackground(Color.WHITE); 
      }
      
      public void paintComponent(Graphics g)
      {
         //call paintComponent of the parent
         super.paintComponent(g); 
         draw(g);//initial call 
         
      }


      public void draw(Graphics g )
      {
      
         for(double  r = 0; r <= 4.0; r += .005)// looping through the rate
         {
            x=.5;// reset pop to start with .5 each time for each rate 
            for(int gen=0; gen<500; gen++)//looping through the generations 
            {
               
               if(gen<100)//skipping first 100 generations (not plotting)
               {
                  y=r*x*(1-x);//finding pop for next year
                  x=y; //that pop becomes next x value
               }
               else //generations 101-500
               {
                  //if y is population size,  plot a pixel (r,y)
                  y=r*x*(1-x);
                  x=y;
                  g.drawRect((int)(r*200),(int)(650-(y*650)),1,1);
               }
            }
         }
      }
   
   
      public static void main(String[] args)
      {
         JFrame f = new JFrame("Logistic Equation"); // get a frame
         f.setBounds(0,0, 800 ,800);
         
         JPanel c= new Logistic();
         f.add(c); //add the panel to the frame
         f.setVisible(true);
      }

}