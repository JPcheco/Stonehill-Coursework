//Jacob Pacheco
//CSC 104-A
//Assignment 11.2.1

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class carORgoat extends JFrame
{
   private JButton Door_1;
   private JButton Door_2;
   private JButton Door_3;
   
   Icon iconDoor = new ImageIcon("door.jpg");
   Icon iconCar = new ImageIcon("car.jpg");
   Icon iconGoat = new ImageIcon("goat.jpg"); 
   
   private JLabel TotalGoat;
   private int Goats = 0;
   private JLabel TotalCar;
   private int Cars = 0;   
   private JLabel TotalGames;
   private int Games = 0;
   private JLabel GamesSwitched;
   private int Switch = 0;  
   
   private JButton Reset;
   private JTextArea LabelBottom;
   private JButton Exit;
   
   protected static final int GOAT = 0;
   protected static final int CAR = 1;
   
   int[] doorPrize = new int[4];   
   JButton[] door = new JButton[4];  
   
   private int move = 1;
   protected static final int NO_SELECTION = 0;
   protected static final int FIRST_DOOR = 1;
   protected static final int SECOND_DOOR = 2;
   protected static final int THIRD_DOOR = 3;
   
   protected int goatDoor = 0;  
   
   public carORgoat()
   {
      super("Car Or Goat");
      setBounds(100,100,750,500);
      
      Font font = new Font("Arial", Font.BOLD, 24);
      Font nfont = new Font("Roboto", Font.BOLD, 64);
            
      // handle the center panel area
      
      JPanel centerPanel = new JPanel();
      FlowLayout flow = new FlowLayout();
      centerPanel.setLayout(null);      
      
      Door_1 = new JButton(iconDoor);
      Door_1.setText("1");
      Door_1.setVerticalTextPosition(AbstractButton.CENTER);
      Door_1.setHorizontalTextPosition(AbstractButton.CENTER);
      Door_1.setFont(nfont);
      
      Door_2 = new JButton(iconDoor);
      Door_2.setText("2");
      Door_2.setVerticalTextPosition(AbstractButton.CENTER);
      Door_2.setHorizontalTextPosition(AbstractButton.CENTER);
      Door_2.setFont(nfont);
      
      Door_3 = new JButton(iconDoor);
      Door_3.setText("3");
      Door_3.setVerticalTextPosition(AbstractButton.CENTER);
      Door_3.setHorizontalTextPosition(AbstractButton.CENTER);
      Door_3.setFont(nfont);     
      
      setDoor (1, Door_1);
      setDoor (2, Door_2);      
      setDoor (3, Door_3);   
      
      getDoor(1).setBounds(50, 35, 175, 300);
      getDoor(2).setBounds(275, 35, 175, 300);
      getDoor(3).setBounds(500, 35, 175, 300);  
            
      centerPanel.add(getDoor(1));
      centerPanel.add(getDoor(2));
      centerPanel.add(getDoor(3));
      
      getDoor(1).addActionListener(new Handler());
      getDoor(2).addActionListener(new Handler());
      getDoor(3).addActionListener(new Handler());        
      
      add(centerPanel,BorderLayout.CENTER);
      
      // handle the south panel area
            
      JPanel southPanel = new JPanel();
      southPanel.setLayout(flow);
      
      Reset = new JButton("Play Again");
      LabelBottom = new JTextArea("Play Car Or Goat!", 2, 20);
      LabelBottom.setEnabled(false);
      LabelBottom.setLineWrap(true);
      Exit = new JButton("Exit");
      
      LabelBottom.setFont(font);
      Reset.setFont(font);
      Exit.setFont(font);
      
      southPanel.add(Reset);
      southPanel.add(LabelBottom);
      southPanel.add(Exit);
      
      Reset.addActionListener(new Handler());
      Exit.addActionListener(new Handler());
      
      add(southPanel,BorderLayout.SOUTH);
      
      // handle the north panel area
      
      JPanel northPanel = new JPanel();
      northPanel.setLayout(flow);
        
      JLabel LTotalGames = new JLabel("Games Played:");
      TotalGames = new JLabel("" + Games);
      JLabel LGamesSwitched = new JLabel("Games Switched:");
      GamesSwitched = new JLabel("" + Switch);
      JLabel LTotalGoat = new JLabel("Goats:");
      TotalGoat = new JLabel("" + Goats);
      JLabel LTotalCar = new JLabel("Cars:");
      TotalCar = new JLabel("" + Cars);
      
      LTotalGames.setFont(font);
      TotalGames.setFont(font);
      LGamesSwitched.setFont(font);
      GamesSwitched.setFont(font);
      LTotalGoat.setFont(font);
      TotalGoat.setFont(font);
      LTotalCar.setFont(font);
      TotalCar.setFont(font);
      
      northPanel.add(LTotalGames);
      northPanel.add(TotalGames);
      northPanel.add(LGamesSwitched);
      northPanel.add(GamesSwitched);
      northPanel.add(LTotalGoat);
      northPanel.add(TotalGoat);
      northPanel.add(LTotalCar);
      northPanel.add(TotalCar);
      
      add(northPanel,BorderLayout.NORTH);   
            
      setVisible(true);
      setResizable(false);
      
      seedPrizes(); 
   }

   private class Handler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {     
      // if [exit] is ever pressed, leave immediately
         if ( e.getSource() == Exit)
         {
            System.exit(0);
         }
         
         // if [Play Again] is ever pressed, start over immediately
         else if ( e.getSource() == Reset)
         {         
            // set buttons back to closed doors   
            for (int i = 1; i < 4; i++ )
               {
               getDoor(i).setIcon(iconDoor);          
               getDoor(i).setEnabled(true);    
               }

            showBottomText("Play Car Or Goat!");
             
            seedPrizes(); 
            resetMove();
         }                  
            
         else if(e.getSource() == Door_1)
         {
            doorWasPressed(FIRST_DOOR);
         }
         else if(e.getSource() == Door_2)
         {
            doorWasPressed(SECOND_DOOR);
         }
         else if(e.getSource() == Door_3)
         {
            doorWasPressed(THIRD_DOOR);
         }        
      }
   }

   public void doorWasPressed (int n)
   {
      if(getMove() == NO_SELECTION)      //for first move
      {
         setMove(n);
         goatDoor = findRemainingGoatDoor(n);
    
         displayComputerChoiceGoatImage(goatDoor);
            
         showBottomText(n, goatDoor);            
      }
      
      else
      {
         // reveal users choice
         showPrize(n);
         
         // bump stats
         if ( getMove() != n )
            incrementSwitch();

         incrementGames();
               
         if (getPrize(n) == GOAT )
         {
            showBottomText("      Sorry...you lose");
            incrementGoats();
         }   
         else
         {
            showBottomText("     Big WINNER!");
            incrementCars();   
         }
         
         for (int i = 1; i < 4; i++ )
            getDoor(i).setEnabled(false);             
      }    
   }

   public int findRemainingGoatDoor(int d)
   {
      //d is the current door selected by the user, so pick a different one
      int g = 0;     
      
      switch (d)
      {
         case 1:
            if (getPrize(2) == GOAT )
               g = 2;
            else
               g = 3;
            break;         
          case 2:
            if (getPrize(1) == GOAT )
               g = 1;
            else
               g = 3;
            break;         
         case 3:
            if (getPrize(2) == GOAT )
               g = 1;
            else
               g = 2;
            break;         
      }     
     return g;
   }

   public void seedPrizes()
   {
      Random r = new Random();
      int n = r.nextInt(3) + 1;

      for ( int i = 1; i < 4; i++ )         
         setPrize(i, GOAT);

      setPrize(n, CAR);
      
      // also clear the goat door
      goatDoor = 0;
      setMove(NO_SELECTION);
   }

   public void showPrize(int d)
   {
      if ( getPrize(d) == GOAT )
         setGoatImage(getDoor(d));
      else
         setCarImage(getDoor(d));
   }

   public void showBottomText(int d, int g)
   {
      LabelBottom.setText("You Selected Door " + d + "!  Host selects    Door "+ g +", it's a goat! Your move...");
   }
   
//Getters and Steters
   public void displayComputerChoiceGoatImage(int n)
   {
      setGoatImage(getDoor(n));
      getDoor(n).setEnabled(false);
   }

   public JButton getDoor ( int i )
   {
      return door[i];
   }

   public void setDoor (int i, JButton b )
   {
      door[i] = b;
   }

   public void showBottomText(String s)
   {
      LabelBottom.setText(s);
   }

   public void setCarImage(JButton j)  
   {
      j.setIcon(iconCar); 
   }
   
   public void setDoorImage(JButton j)  
   {
      j.setIcon(iconDoor); 
   }

   public void setGoatImage(JButton j)  
   {
      j.setIcon(iconGoat); 
   }

   public int getMove()
   {
      return move;
   }

   public void setMove(int n)
   {
      move = n; 
   }

   public void resetMove()
   {
      setMove(NO_SELECTION);
   }
   
   public int getPrize(int i)
   {
      return doorPrize[i];
   }

   public void setPrize(int i, int p)
   {
      doorPrize[i] = p;
   }

   public int getGames()
   {
      return Games;
   }

   public void setGames(int n)
   {
      Games = n;
      TotalGames.setText(Integer.toString(n));  
   }

   public void incrementGames()
   {
      int n = getGames();
      n++;
      setGames(n);
   }
   
   public int getGoats()
   {
      return Goats;
   }

   public void setGoats(int n)
   {
      Goats = n; 
      TotalGoat.setText(Integer.toString(n));
   }
   
   public void incrementGoats()
   {
      int n = getGoats();
      n++;
      setGoats(n);
   }
   
   public int getCars()
   {
      return Cars;
   }

   public void setCars(int n)
   {
      Cars = n; 
      TotalCar.setText(Integer.toString(n));
   }
   
   public void incrementCars()
   {
      int n = getCars();
      n++;
      setCars(n);
   }
   
   public int getSwitch()
   {
      return Switch;
   }

   public void setSwitch(int n)
   {
      Switch = n; 
      GamesSwitched.setText(Integer.toString(n));
   }
   
   public void incrementSwitch()
   {
      int n = getSwitch();
      n++;
      setSwitch(n);
   }

   public static void main(String[] args)
   {      
      JFrame carORgoat = new carORgoat();
   }
}