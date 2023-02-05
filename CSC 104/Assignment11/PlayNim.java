//Jacob Pacheco
//CSC 104-A
//Assignment 11.1.1

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class PlayNim extends JFrame
{
   protected static final int PLAYER = 0;
   protected static final int COMPUTER = 1;
   private int turn = PLAYER;

   private JLabel info;
   private JButton take_1;
   private JButton take_2;
   private JButton take_3;
   
   private JLabel TotalGames;
   private int Games = 0;
   private JLabel PlayerWins;
   private int Wins = 0; 
   
   private JButton Reset;
   private JButton Exit;
   
   private JTextField inputBox;
   private JButton PlayerFirst;
   private JButton ComputerFirst;
   private int who;
   
   private JLabel Sticks;
   private int numSticks = 0;

   public PlayNim()
   {
      super("123-Nim");
      setBounds(100,100,800,800);
            
      // handle the east panel
      
      JPanel rightPanel = new JPanel();
      GridLayout gridRight = new GridLayout(4,1);
      rightPanel.setLayout(gridRight);
      
      take_1 = new JButton("1");
      take_2 = new JButton("2");
      take_3 = new JButton("3");
      info = new JLabel("Click the amount of Sticks to withdraw");
            
      rightPanel.add(info);
      rightPanel.add(take_1);
      rightPanel.add(take_2);
      rightPanel.add(take_3);
      
      take_1.addActionListener(new Handler());
      take_2.addActionListener(new Handler());
      take_3.addActionListener(new Handler());
      
      take_1.setEnabled(false);
      take_2.setEnabled(false);
      take_3.setEnabled(false);            
      
      add(rightPanel,BorderLayout.EAST);
      
      // handle the south panel area
            
      JPanel southPanel = new JPanel();
      FlowLayout flow = new FlowLayout();
      southPanel.setLayout(flow);
      
      Reset = new JButton("Play Again");
      Exit = new JButton("Exit");
      
      southPanel.add(Reset);
      southPanel.add(Exit);
      
      Reset.addActionListener(new Handler());
      Exit.addActionListener(new Handler());
      
      add(southPanel,BorderLayout.SOUTH);
      
      // handle the west panel
      
      JPanel LeftPanel = new JPanel();
      GridLayout gridLeft = new GridLayout(2,1);
      LeftPanel.setLayout(gridLeft);
      
      JPanel topLeftPanel = new JPanel();
      JPanel subtopLeftPanel = new JPanel();
      
      JLabel GamesLabel = new JLabel("Games Played: ");
      TotalGames = new JLabel(" " + Games);
      JLabel PlayerLabel = new JLabel("Player Wins: ");
      PlayerWins = new JLabel(" " + Wins);
      
      topLeftPanel.add(GamesLabel);
      topLeftPanel.add(TotalGames);
      subtopLeftPanel.add(PlayerLabel);
      subtopLeftPanel.add(PlayerWins);
            
      LeftPanel.add(topLeftPanel);
      LeftPanel.add(subtopLeftPanel);      
            
      add(LeftPanel,BorderLayout.WEST);
      
      // handle the north panel      
      
      JPanel topPanel = new JPanel();
      JPanel subtopPanel = new JPanel();
      GridLayout gridTop = new GridLayout(2,1);
      topPanel.setLayout(gridTop);
      
      JLabel sticksLabel = new JLabel("Number of Sticks", JLabel.RIGHT);
      inputBox = new JTextField(2);
      inputBox.setText("0");     
     
      PlayerFirst = new JButton("Player Goes First");
      ComputerFirst = new JButton("Computer Goes First");
      
      subtopPanel.add(PlayerFirst);
      subtopPanel.add(ComputerFirst);
      topPanel.add(sticksLabel);
      topPanel.add(inputBox);
      topPanel.add(subtopPanel);
      
      PlayerFirst.setEnabled(false);
      ComputerFirst.setEnabled(false);      
 
      PlayerFirst.addActionListener(new Handler());
      ComputerFirst.addActionListener(new Handler());
      inputBox.addActionListener(new Handler());
      
      add(topPanel,BorderLayout.NORTH); 
 
      // handle the center panel
      
      Sticks = new JLabel();
      setSticks(0);
      add(Sticks,BorderLayout.CENTER);
      
      // handle the input box
      
      setVisible(true);
      setResizable(false);
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
      if ( e.getSource() == Reset)
      {
         setSticks(0);
         inputBox.setText("0");
         inputBox.setEditable(true);
         
         // set buttons back to default state
         take_1.setEnabled(false);
         take_2.setEnabled(false);
         take_3.setEnabled(false);
         PlayerFirst.setEnabled(false);
         ComputerFirst.setEnabled(false);            

         return;
      }
      
      if ( e.getSource() == inputBox )
      {
         try
         {
            String s = inputBox.getText();
            int n = Integer.parseInt(s);
               
            if ( (n < 1) || ( n > 50 ) )
               throw new NumberFormatException();               
               
            setSticks(n); //send the input to the sticks label
            
            // prevent further editing of the text box   
            inputBox.setEditable(false); 
               
            // enable the slect buttons for who goes first   
            PlayerFirst.setEnabled(true);
            ComputerFirst.setEnabled(true);
            }
            catch(NumberFormatException ex)
            {
               inputBox.setText("  Illegal Input, please play again");
               return;
            }
         }        
     
         else if( e.getSource() == PlayerFirst ) // Player First
         {
            PlayerFirst.setEnabled(false);
            ComputerFirst.setEnabled(false);  
            
            int n = getSticks();
            if ( n > 0 )
               take_1.setEnabled(true);
            if ( n > 1 )   
               take_2.setEnabled(true);
            if ( n > 2)   
               take_3.setEnabled(true);   
            
            incrementGames();                        
         }
         
         else if( e.getSource() == ComputerFirst ) // Computer First
         {
            computerMove();
            PlayerFirst.setEnabled(false);
            ComputerFirst.setEnabled(false);  
       
            int n = getSticks();
            if ( n > 0 )
               take_1.setEnabled(true);
            if ( n > 1 )   
               take_2.setEnabled(true);
            if ( n > 2)   
               take_3.setEnabled(true);   
            
            incrementGames();  
         }         
            
         else if(e.getSource() == take_1)
         {
            removeSticks(1);
            computerMove();
         }
         else if(e.getSource() == take_2)
         {
            removeSticks(2);
            computerMove();
         }
         else if(e.getSource() == take_3)
         {
            removeSticks(3);
            computerMove();
         }        
      }
   }
   
   public void computerMove()
   {
      int n = getSticks();
      if ( n == 0 )
         return;
      
      n = n % 4;
      
      if ( n == 0 )
      {
         Random r = new Random();
         n = r.nextInt(3) + 1;
      }
      who = COMPUTER;
      removeSticks(n);
         
      who = PLAYER;
      
      if ( getSticks() == 0 )
         inputBox.setText("  Computer Wins");     
   }
   
   public int getSticks()
   {
       return numSticks;
   }

   public void setSticks(int n)
   {  
      numSticks = n;
      inputBox.setText(Integer.toString(n));
   }

   public void removeSticks(int n)
   {
      int x;
      
      x = getSticks();
      if ( (n < 1) || (n > 3) )
         return;

      if ( n > x )
         n = x;
         
      x = x - n;
      setSticks(x);     
      
      // disable buttons if there are not enough sticks to support that 
      if ( x < 3 )
         take_3.setEnabled(false);   
      if ( x < 2 )
         take_2.setEnabled(false);   
      if ( x < 1 )
         {
         take_1.setEnabled(false);   
         
         if ( who == PLAYER )
         incrementWins();
         }         
   }

   public int getGames()
   {
      return Games;
   }

   public void setGames(int n)
   {
      int Games = n;
      TotalGames.setText(Integer.toString(n));  
   }

   public void incrementGames()
   {
      Games = getGames();
      Games++;
      setGames(Games);
   }

   public int getWins()
   {
      return Wins;
   }

   public void setWins(int n)
   {
      int Wins = n;
         PlayerWins.setText(Integer.toString(n));

   }

   public void incrementWins()
   {
      int n = getWins();
      n++;
      setWins(n);
   }

   public static void main(String[] args)
   {      
      JFrame PlayNim = new PlayNim();
   }
}