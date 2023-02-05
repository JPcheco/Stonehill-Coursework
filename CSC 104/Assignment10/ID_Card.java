//Jacob Pacheco
//CSC 104-A
//Assignment 10.1.1

import java.awt.*;
import javax.swing.*;

public class ID_Card extends JFrame
{   
   public ID_Card()
   {
      super("ID Card");
      setBounds(0,0,250,150); // card at position (0,0)
      
      JLabel picture = new JLabel(new ImageIcon("Lelouch.jpg"));
      add(picture, BorderLayout.CENTER);
      
      JLabel name = new JLabel("                           McLOVIN");
      add(name, BorderLayout.NORTH);
      
      JLabel words = new JLabel("               JAVA PROGRAMMER");
      add(words, BorderLayout.SOUTH);
      
      JPanel LeftPanel = new JPanel();
      LeftPanel.setLayout(new GridLayout(3,1));
      
      JLabel L1 = new JLabel("Height: 6'0" );
      LeftPanel.add(L1);
      
      JLabel L2 = new JLabel("Weight: 120lb" );
      LeftPanel.add(L2);
      
      JLabel L3 = new JLabel("Eyes: Brown" );
      LeftPanel.add(L3);
      
      add(LeftPanel, BorderLayout.WEST);
      
      JPanel RightPanel = new JPanel();
      RightPanel.setLayout(new GridLayout(3,1));
      
      JLabel R1 = new JLabel("892 MOMONA ST." );
      RightPanel.add(R1);
      
      JLabel R2 = new JLabel("HONOLULU" );
      RightPanel.add(R2);
      
      JLabel R3 = new JLabel("HAWAII" );
      RightPanel.add(R3);
      
      add(RightPanel, BorderLayout.EAST);
      
      setResizable(false);
      setVisible(true);
   }

   public static void main(String[] args)
   {
      JFrame ID_Card = new ID_Card();
   }
}