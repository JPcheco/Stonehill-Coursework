//Jacob Pacheco
//CSC 104-A
//Assignment 9.3.1

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Frame3Dice extends JFrame
{   
   public Frame3Dice()
   {
      super("Frame3Dice");
      
      setLayout(null);
      
      setBounds(30,30,525,250); // card at position (30,30)
      
      JButton button = new JButton();
      
      int position = 50;
      
      for(int i=0; i<3; i++)
      {  
         int roll = getRoll();      
         
         switch(roll)
         {
            case 1: button = new JButton(new ImageIcon("1.jpg")); break;
            case 2: button = new JButton(new ImageIcon("2.jpg")); break;
            case 3: button = new JButton(new ImageIcon("3.jpg")); break;
            case 4: button = new JButton(new ImageIcon("4.jpg")); break;
            case 5: button = new JButton(new ImageIcon("5.jpg")); break;
            case 6: button = new JButton(new ImageIcon("6.jpg")); break;
            default: break;
         }
         button.setBounds(position, 70, 100, 100);
         add(button);
            
         position = position + 150;
      }
      
      setResizable(false);
      setVisible(true);
   }
   
   public int getRoll()
   {
      Random r = new Random();
            
      int random = (r.nextInt(5)+1);
      
      if(random==0)
         random = (r.nextInt(5)+1);
      
      return random;     
   }
   
   public static void main(String[] args)
   {
      JFrame Frame3Dice = new Frame3Dice();
   }
}