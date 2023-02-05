//Jacob Pacheco
//CSC 104-A
//Assignment 9.2.2

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Dice2 extends JFrame
{   
   public Dice2()
   {
      super("Dice2");
      
      GridLayout grid = new GridLayout(2,3);
      setLayout(grid);
      
      setBounds(100,100,750,500); // card at position (50,50)
      
      JButton button = new JButton();
      
      for(int i=0; i<6; i++)
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
         add(button);
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
      JFrame Dice2 = new Dice2();
   }
}