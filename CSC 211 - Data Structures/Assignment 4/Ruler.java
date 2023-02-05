//Jacob Pacheco
//CSC 211
//Assignment 4.2.1

import java.awt.*;
import javax.swing.*;

public class Ruler extends JPanel
{
   public static int i = 0;
   
    public void paintComponent(Graphics g)
    {
       super.paintComponent(g);
       setBackground(Color.WHITE);
       g.setColor(Color.BLUE);
       g.drawLine(0,200, 810,200); // the horizontal line in the picture
       addMark(0,800,100, g); //between 0 and 800 of height 100
    }
    
    public void addMark(int lo,int hi, int size, Graphics g)
    {
       if(size < 10) // draws a mark no smaller than 1/8
         return;    
    
       int mid = lo + ((hi-lo)/2); //gets the mid point of lo and hi
       g.drawLine(mid, 200, mid, 200-size); //draws the mark on the ruler
       
       System.out.println(i++  + "("+mid+ ", " + (size) + ")"); //degub
         
       addMark(lo, mid, size/2, g); //left
       addMark(mid, hi, size/2, g); //right
    }
    
    public static void main(String[] args)
    {
       JFrame f = new JFrame();
       f.setBounds(0,0, 850, 500);
       Ruler r = new Ruler();
       f.add(r);
       f.setVisible(true);
    }
}