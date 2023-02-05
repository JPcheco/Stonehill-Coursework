//Jacob Pacheco
//CSC 104-A
//Assignment 10.2.1

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Faces extends JFrame
{   
   public Faces()
   {
      super("Faces");
      setBounds(50,50,500,400); // card at position (50,50)
            
      JLabel picture = new JLabel(); 

      int roll = getRoll();      
      
      switch(roll)
      {
         case 1: picture = new JLabel(new ImageIcon("Lelouch.jpg")); break;
         case 2: picture = new JLabel(new ImageIcon("ErenYeager.jpg")); break;
         case 3: picture = new JLabel(new ImageIcon("Askeladd.jpg")); break;
         default: break;
      }
      add(picture, BorderLayout.CENTER);

      JLabel quote = new JLabel();
      
      switch(roll)
      {
         case 1: quote = new JLabel("To defeat evil, I must become a greater evil"); break;
         case 2: quote = new JLabel("Freedom."); break;
         case 3: quote = new JLabel("A fight you can’t win becomes an obsession."); break;
         default: break;
      }
      add(quote, BorderLayout.SOUTH); 
      
      JPanel TopPanel = new JPanel();
      TopPanel.setLayout(new GridLayout(1,3));
      
      JButton L = new JButton("Lelouch");
      TopPanel.add(L);
      
      JButton M = new JButton("Eren");
      TopPanel.add(M);
      
      JButton R = new JButton("Askeladd");
      TopPanel.add(R);
      
      add(TopPanel, BorderLayout.NORTH);      
      
      setResizable(false);
      setVisible(true);
   }

   public int getRoll()
   {
      Random r = new Random();
            
      int random = (r.nextInt(3)+1);
      
      if(random==0)
         random = (r.nextInt(3)+1);
      
      return random;     
   }

   public static void main(String[] args)
   {
      JFrame Faces = new Faces();
   }
}