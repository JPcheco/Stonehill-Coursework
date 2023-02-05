import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class JuliaSet extends JPanel
{
   //for -2 to 2 nested loops
   int lower_x=-2;
   int lower_y = -2;
   int upper_x = 2;
   int upper_y = 2;
   int max=2;
   int maxi=50;
   int c=0;
   
      public JuliaSet()
      {
         setBackground(Color.WHITE); 
      }
      
      public void paintComponent(Graphics g)
      {
         //call paintComponent of the parent
         super.paintComponent(g); 
         draw(g);//initial call 
         
      }
      
      public Complex f(Complex z)
      {
         Complex c= new Complex(0,0);
         return z.mul(z).add(c);
      }
      
      //draws points in the grid 
      public void draw(Graphics g )
      {
         
         int u=0;
         int v;
         int count;
    
         //working with complex numbers and equation to determine prisoner or escapee
          for (double x = lower_x; x < upper_x; x += .005)
            {
            
            v=0;
             for (double y = lower_y; y < upper_y; y += .005)
               {
                  
                  Complex z= new Complex(x,y);
                  for(count=0; count<maxi; count++)
                  {
                     z=f(z);
                     
                     //determine whether escapee or not
                     if(z.abs()>max)
                        break;
                                    
                  }
                    
                    int countt= maxi-count;
                    float  red = (countt * 24  %   256)/256.0f;  // the f makes 256.0 a float, rather than a doubl
                    float green = (countt * 6   %   256)/256.0f;
                    float blue = (countt * 13   %   256)/256.0f;
                    Color color = new Color(red, green, blue); // parameters are float
                           
                  g.setColor(color);
                  g.drawRect(u,v,1,1);
                 v++; 
               }
               u++;
            }
      }
      
      
       public static void main(String[] args)
      {
         JFrame f = new JFrame("Julia Set"); // get a frame
         f.setBounds(0,0, 800,800);
         
         JPanel c= new JuliaSet();
         f.add(c); //add the panel to the frame
         f.setVisible(true);
      }

}