//Jacob Pacheco
//CSC 104-A
//Assignment 14.1.3

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class InfixPostfixCONVERTER extends JFrame
{
   private JButton PostfixButton;
   private JButton EvaluateButton;
   private JButton ClearButton;
   private JButton ExitButton;
   
   private JTextField InfixInputArea;
   private JLabel InfixLabel;
   private JTextField PostfixInputArea;
   private JLabel PostfixLabel;
   private JTextField ResultOutputArea;
   private JLabel ResultLabel;
   
   public InfixPostfixCONVERTER()
   {
      super("Infix-Postfix-CONVERTER");
      setBounds(100,100,500,300);
      
      Font FONT = new Font("Arial", Font.PLAIN, 18);
            
      // handle the center panel
      
      JPanel CenterPanel = new JPanel();
      FlowLayout flow = new FlowLayout();
      GridLayout grid = new GridLayout(2,1);
      CenterPanel.setLayout(flow);
      
      JPanel p1 = new JPanel();
      JPanel p2 = new JPanel();
      JPanel p3 = new JPanel();      
      
      p1.setLayout(grid);
      p2.setLayout(grid);
      p3.setLayout(grid);
      
      InfixInputArea = new JTextField(20);
      InfixInputArea.setFont(FONT);
      InfixLabel = new JLabel("                       Infix");
      InfixLabel.setFont(FONT);
      
      p1.add(InfixLabel);
      p1.add(InfixInputArea);
      
      PostfixInputArea = new JTextField(20);
      PostfixInputArea.setFont(FONT);
      PostfixLabel = new JLabel("                     Postfix");
      PostfixLabel.setFont(FONT);
      
      p2.add(PostfixLabel);
      p2.add(PostfixInputArea);
      
      ResultOutputArea = new JTextField(20);
      ResultOutputArea.setFont(FONT);
      ResultLabel = new JLabel("                      Result");
      ResultLabel.setFont(FONT);
      
      ResultOutputArea.setEnabled(false);
      
      p3.add(ResultLabel);
      p3.add(ResultOutputArea);
      
      CenterPanel.add(p1);      
      CenterPanel.add(p2);
      CenterPanel.add(p3);     
      
      //InfixInputArea.addActionListener(new Handler());      
      //PostfixInputArea.addActionListener(new Handler()); 
      //ResultOutputArea.addActionListener(new Handler());       
      
      add(CenterPanel,BorderLayout.CENTER);
      
      // handle the South panel 
      
      JPanel southPanel = new JPanel();
      southPanel.setLayout(flow);
      
      PostfixButton = new JButton("Postfix");
      EvaluateButton = new JButton("Evaluate");
      ClearButton = new JButton("Clear");
      ExitButton = new JButton("Exit");  
      
      PostfixButton.setFont(FONT);
      EvaluateButton.setFont(FONT);
      ClearButton.setFont(FONT);
      ExitButton.setFont(FONT);
                
      southPanel.add(PostfixButton);
      southPanel.add(EvaluateButton);
      southPanel.add(ClearButton);
      southPanel.add(ExitButton);
      
      PostfixButton.addActionListener(new Handler());
      EvaluateButton.addActionListener(new Handler());
      ClearButton.addActionListener(new Handler());
      ExitButton.addActionListener(new Handler());       
      
      add(southPanel, BorderLayout.SOUTH);      
            
      setVisible(true);
      setResizable(false);      
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
         
         if(e.getSource() == ClearButton)
         {
            InfixInputArea.setText("");      
            PostfixInputArea.setText("");  
            ResultOutputArea.setText(""); 
         }
         
         else if (e.getSource() == EvaluateButton)
         {
            String p, r;
            p = PostfixInputArea.getText();
            
            if(!p.equals(""))
            {
               r = Integer.toString(Postfix.evaluate(p));
               ResultOutputArea.setText(r);
            }
         }   
         
         else if (e.getSource() == PostfixButton)
         {
            String i, p;
            i = InfixInputArea.getText();
            
            if(!i.equals(""))
            {
               p = ConvertToPostfix.convert(i);
               PostfixInputArea.setText(p);
            }
         }      
      }
   }
   
   public static void main(String[] args)
   {      
      JFrame InfixPostfixCONVERTER = new InfixPostfixCONVERTER();
   }
}