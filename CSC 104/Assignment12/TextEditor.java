//Jacob Pacheco
//CSC 104-A
//Assignment 12.1.1

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class TextEditor extends JFrame
{
   private JButton NewFileButton;
   private JButton OpenButton;
   private JButton SaveButton;
   private JButton CutButton;
   private JButton PasteButton;
   private JButton CopyButton;
   private JButton ExitButton;
   
   private JTextArea AreaText;
   
   private JCheckBox Bold, Italic;
   
   private JRadioButton Font1, Font2, Font3;
   private ButtonGroup BG;   

   private Font FONT;

   protected static String font_1 = "Times New Roman";
   protected static String font_2 = "Courier";
   protected static String font_3 = "Arial";   
   protected static final int SIZE = 14;
   protected static final int BOLD = 1;
   protected static final int ITALIC = 2;
   protected static final int PLAIN = 0;
   private int selection;
   private String font;

   public TextEditor()
   {
      super("Text Editor");
      setBounds(100,100,500,500);
      
      setselection(0);
      setfont(font_3);
      FONT = new Font(getfont(), getselection(), SIZE);
            
      // handle the North panel
      
      JPanel NorthPanel = new JPanel();
      FlowLayout flow = new FlowLayout();
      NorthPanel.setLayout(flow);
      
      NewFileButton = new JButton("New File");
      OpenButton = new JButton("Open");
      SaveButton = new JButton("Save");
      CutButton = new JButton("Cut");
      PasteButton = new JButton("Paste");
      CopyButton = new JButton("Copy");
      ExitButton = new JButton("Exit");  
                
      NorthPanel.add(NewFileButton);
      NorthPanel.add(OpenButton);
      NorthPanel.add(SaveButton);
      NorthPanel.add(CutButton);
      NorthPanel.add(PasteButton);
      NorthPanel.add(CopyButton);
      NorthPanel.add(ExitButton);
      
      NewFileButton.addActionListener(new Handler());
      OpenButton.addActionListener(new Handler());
      SaveButton.addActionListener(new Handler());
      CutButton.addActionListener(new Handler());
      PasteButton.addActionListener(new Handler());
      CopyButton.addActionListener(new Handler());
      ExitButton.addActionListener(new Handler());       
      
      add(NorthPanel,BorderLayout.NORTH);
      
      // handle the center panel 
      
      AreaText = new JTextArea();
      AreaText.setFont(FONT);
      AreaText.setLineWrap(true);
      
      JScrollPane pane = new JScrollPane(AreaText,
                                          ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                                          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      
      JPanel centerPanel = new JPanel();
      GridLayout grid = new GridLayout(1,2);                 
      centerPanel.setLayout(grid);
      
      centerPanel.add(pane);
      
      add(centerPanel, BorderLayout.CENTER);
      
      // handle the south panel
      
      JPanel southPanel = new JPanel();
      southPanel.setLayout(flow);
      
      Bold = new JCheckBox("Bold");
      Italic = new JCheckBox("Italic");
      
      southPanel.add(Bold);
      southPanel.add(Italic);
      
      Font1 = new JRadioButton("Times New Roman", true);
      Font2 = new JRadioButton("Courier", false);
      Font3 = new JRadioButton("Arial", false);
      
      BG = new ButtonGroup();
      BG.add(Font1);
      BG.add(Font2);
      BG.add(Font3);
      
      southPanel.add(Font1);
      southPanel.add(Font2);
      southPanel.add(Font3);
      
      Bold.addActionListener(new ButtonHandler());
      Italic.addActionListener(new ButtonHandler());
      Font1.addActionListener(new ButtonHandler());
      Font2.addActionListener(new ButtonHandler());
      Font3.addActionListener(new ButtonHandler()); 
      
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
         else if (e.getSource() == CutButton)
         {
            AreaText.cut();
         }
         else if (e.getSource() == PasteButton)
         {
            AreaText.paste();
         }
         else if (e.getSource() == CopyButton)
         {
            AreaText.copy();
         }
         else if (e.getSource() == NewFileButton)
         {
            AreaText.setText("");
         }
         else if (e.getSource() == OpenButton)
         {
            String fileName = JOptionPane.showInputDialog(null, "Enter File Name");
            
            if (fileName != null)
            {
               try
               {
                  FileReader in = new FileReader(fileName);
                  AreaText.read(in, null);
                  in.close();
               }
               catch (FileNotFoundException ex)
               {
                  JOptionPane.showMessageDialog(null,"File not Found",
                                                     "Input Error", JOptionPane.ERROR_MESSAGE);
               }
               catch (IOException ex)
               {
                  JOptionPane.showMessageDialog(null,"IO Exception",
                                                     "Input Error", JOptionPane.ERROR_MESSAGE);
               }
            }                        
         }
         else if (e.getSource() == SaveButton)
         {
            String fileName = JOptionPane.showInputDialog(null, "Enter File Name");
            
            if (fileName != null)
            {
               try
               {
                  FileWriter out = new FileWriter(fileName);
                  AreaText.write(out);
                  out.close();
               }
               catch (FileNotFoundException ex)
               {
                  JOptionPane.showMessageDialog(null,"File not Found",
                                                     "Input Error", JOptionPane.ERROR_MESSAGE);
               }
               catch (IOException ex)
               {
                  JOptionPane.showMessageDialog(null,"IO Exception",
                                                     "Output Error", JOptionPane.ERROR_MESSAGE);
               }                        
            }   
         }
      }
   }

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {     
         if (e.getSource() instanceof JCheckBox)
         {
            selection = Font.PLAIN;
            
            if (Bold.isSelected())
               selection = Font.BOLD;
            
            if (Italic.isSelected())
               selection = selection + Font.ITALIC;
            
            AreaText.setFont(new Font(getfont(), selection, SIZE));
         }
      
         else if (e.getSource() instanceof JRadioButton)
         {
            // find which is selected
            if (Font1.isSelected())
               setfont(font_1);
            else if (Font2.isSelected())
               setfont(font_2);
            else
               setfont(font_3);
              
            AreaText.setFont(new Font(getfont(), getselection(), SIZE));
         }         
      }
   }
   
   public String getfont()
   {
      return font;
   }
   
   public void setfont(String n)
   {
      font = n;
   }
   
   public int getselection()
   {
      return selection;
   }
   
   public void setselection(int n)
   {
      selection = n;
   }
      
   public static void main(String[] args)
   {      
      JFrame TextEditor = new TextEditor();
   }
}