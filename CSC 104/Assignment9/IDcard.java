//Jacob Pacheco
//CSC 104-A
//Assignment 9.1.1

import java.awt.*;
import javax.swing.*;

public class IDcard extends JFrame
{   
   public IDcard()
   {
      super("Java Programmer");
      setBounds(50,50,350,275); // card at position (50,50)
      
      JButton picture = new JButton(new ImageIcon("McLovin.jpg"));
      add(picture, BorderLayout.CENTER);
      
      JButton name = new JButton("McLOVIN");
      add(name, BorderLayout.NORTH);
      
      JButton address = new JButton("892 MOMONA ST, HONOLULU HI");
      add(address, BorderLayout.SOUTH);
      
      setResizable(false);
      setVisible(true);
   }

   public static void main(String[] args)
   {
      JFrame IDcard = new IDcard();
   }
}