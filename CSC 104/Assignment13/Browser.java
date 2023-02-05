//Jacob Pacheco
//CSC 104-A
//Assignment 13.1.1

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Browser extends JFrame
{
   private JButton BackButton;
   private JButton ForwardButton;
   private JButton ExitButton;
   
   private JTextField InputArea;
   private JLabel imageLabel;
   
   private Stack<String> backButtonStack;
   private Stack<String> forwardButtonStack;
   
   String currentPicture = new String ("bs.jpg");
   
   public Browser()
   {
      super("Browser");
      setBounds(100,100,300,350);
      
      Font FONT = new Font("Arial", Font.PLAIN, 18);
            
      // handle the North panel
      
      JPanel NorthPanel = new JPanel();
      FlowLayout flow = new FlowLayout();
      NorthPanel.setLayout(flow);
      
      InputArea = new JTextField(10); 
      InputArea.setFont(FONT);

      InputArea.setText(currentPicture);
                
      NorthPanel.add(InputArea);
      
      InputArea.addActionListener(new Handler());      
      
      add(NorthPanel,BorderLayout.NORTH);
      
      // handle the center panel 
      
      imageLabel = new JLabel();
      imageLabel.setIcon(new ImageIcon(currentPicture));
      
      JPanel centerPanel = new JPanel();                 
      centerPanel.setLayout(null);
      
      imageLabel.setBounds(85, 35, 100, 200);
      
      centerPanel.add(imageLabel);
      
      add(centerPanel, BorderLayout.CENTER);
      
      // handle the south panel
      
      JPanel southPanel = new JPanel();
      southPanel.setLayout(flow);
      
      BackButton = new JButton("Back");
      ForwardButton = new JButton("Forward");
      ExitButton = new JButton("Exit");  
      
      BackButton.setEnabled(false);
      ForwardButton.setEnabled(false);
      
      BackButton.setFont(FONT);
      ForwardButton.setFont(FONT);
      ExitButton.setFont(FONT);
                
      southPanel.add(BackButton);
      southPanel.add(ForwardButton);
      southPanel.add(ExitButton);
      
      BackButton.addActionListener(new Handler());
      ForwardButton.addActionListener(new Handler());
      ExitButton.addActionListener(new Handler());       
      
      add(southPanel, BorderLayout.SOUTH);      
            
      setVisible(true);
      setResizable(false);      
      
      // create the Stacks to play with 
      backButtonStack = new Stack<String> (100);
      forwardButtonStack = new Stack<String> (100);
   }

   private class Handler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {     
         // if [exit] is ever pressed, leave immediately
         if (e.getSource() == ExitButton)
         {
            System.exit(0);
         }
         else if (e.getSource() == BackButton)
         {
            // get the current filename from the text field
            String fileName = InputArea.getText(); 
            if ( validateFileName(fileName) == false )
               return;

            // push it on the forward Stack 
            forwardButtonStack.push(fileName);
            
            // pop the current name from the back stack
            fileName = backButtonStack.pop();

            adjustStackButtons();

            displayInfo(fileName);          
            
         }
         else if (e.getSource() == ForwardButton)
         {
            // get the current filename from the text field
            String fileName = InputArea.getText(); 
            if ( validateFileName(fileName) == false )
               return;
               
            // push it onto the back stack   
            backButtonStack.push(fileName);   
               
            // pop the current name from the forward stack
            fileName = forwardButtonStack.pop();
            
            adjustStackButtons();    
               
            displayInfo(fileName);
            
         }
         else if (e.getSource() == InputArea)
         {
            // get the current filename from the text field
            String fileName = InputArea.getText(); 
            if ( validateFileName(fileName) == false )
               return;
               
            // at this point, we've got a legit file name
               
            // push the current name and image onto the stack
            backButtonStack.push(currentPicture);
           
            adjustStackButtons();
               
            displayInfo(fileName);
         }         
      }
   }
      
   public void adjustStackButtons()
   {
      if ( forwardButtonStack.size() == 0 )
         ForwardButton.setEnabled(false); 
      else
         ForwardButton.setEnabled(true);
                
      if ( backButtonStack.size() == 0 )
         BackButton.setEnabled(false);
      else      
         BackButton.setEnabled(true);
   }          
      
   public void displayInfo(String f)
   {
      // this is the new current picture 
      currentPicture = f;
               
      // show the new pic and name
      InputArea.setText(f);
      imageLabel.setIcon(new ImageIcon(f));
   }          
      
   public boolean validateFileName(String f )
   {
      boolean result = true;     
                  
      if (f == null)
         result = false;
      else
      {
         try
         {
            FileReader in = new FileReader(f);
            in.close();
         }
         catch (FileNotFoundException ex)
         {
            JOptionPane.showMessageDialog(null,"File not Found","Input Error", JOptionPane.ERROR_MESSAGE);
            result = false;
         }
         catch (IOException ex)
         {
            JOptionPane.showMessageDialog(null,"IO Exception","Input Error", JOptionPane.ERROR_MESSAGE);
            result = false;
         }
      }
      
      if ( result == false) 
         displayInfo(currentPicture);  
      
      return result;  
   }          
      
   public static void main(String[] args)
   {      
      JFrame Browser = new Browser();
   }
}