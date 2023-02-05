//Jacob Pacheco
//CSC 104-A
//Assignment 10.3.1

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Framer
{
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      System.out.print("How many rectangles: ");
      
      int num = input.nextInt();
      
      JFrame f = new JFrame();
      f.setBounds(100,100,800,800); // you figure that out
      
      JPanel p = new TunnelVision(num);
      
      f.add(p); // add panel to center of frame
      
      f.setVisible(true);
      
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}