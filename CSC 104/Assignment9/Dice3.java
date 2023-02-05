//Jacob Pacheco
//CSC 104-A
//Assignment 9.2.3

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Dice3 extends JFrame
{   
   public Dice3()
   {
      super("Dice3");
      
      GridLayout grid = new GridLayout(2,3);
      setLayout(grid);
      
      setBounds(100,100,750,500); // card at position (50,50)
      
      JLabel label = new JLabel();
      
      for(int i=0; i<6; i++)
      {  
         int roll = getRoll();      
         
         switch(roll)
         {
            case 1: label = new JLabel(new ImageIcon("1.jpg")); break;
            case 2: label = new JLabel(new ImageIcon("2.jpg")); break;
            case 3: label = new JLabel(new ImageIcon("3.jpg")); break;
            case 4: label = new JLabel(new ImageIcon("4.jpg")); break;
            case 5: label = new JLabel(new ImageIcon("5.jpg")); break;
            case 6: label = new JLabel(new ImageIcon("6.jpg")); break;
            default: break;
         }
         add(label);
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
      JFrame Dice3 = new Dice3();
   }
}