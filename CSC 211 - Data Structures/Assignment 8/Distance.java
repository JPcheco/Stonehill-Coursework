
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Distance implements ActionListener
 {
    
   private static JLabel startLabel = new JLabel("Start");
   private static JLabel endLabel = new JLabel("End");
   private static JComboBox<String> startCB;    
   private static JComboBox<String> endCB;  
   private static JButton okButton = new JButton("OK");
   private static JLabel results1 = new JLabel("");
   private static JLabel results2 = new JLabel("");

   private static ShortestPathJ s; 
   
   public static void main(String[] args) 
   {       
      s = new ShortestPathJ ();

      // get the list of cities from the ShortestPath class
      String[] choices = s.getChoices();
   
      startCB = new JComboBox<String>(choices);   
      endCB = new JComboBox<String>(choices);   
   
      JFrame frame = new JFrame("Distance Calculator");

      frame.setSize(600, 200);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel panel = new JPanel();    
      frame.add(panel);

      panel.setLayout(null);

      // start lable andcombo box
      startLabel.setBounds(10,20,150,25);
      panel.add(startLabel);

      startCB.setBounds(100, 20, 300, 25);
      startCB.setVisible(true);
      panel.add(startCB);

      // end label and combo box
      endLabel.setBounds(10,50,100,25);
      panel.add(endLabel);

      endCB.setBounds(100, 50, 300,25);
      endCB.setVisible(true);
      panel.add(endCB);

      // Creating login button
      okButton.setBounds(10, 80, 80, 25);
      okButton.addActionListener(new Distance() );
      panel.add(okButton);
        
      // heres where the results go 
      results1.setBounds(10, 110, 300,25);
      panel.add(results1);
    
      results2.setBounds(10, 140, 3000,25);
      panel.add(results2);            
        
      // display it all
      frame.setVisible(true);
    }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      distanceGUIResults r;     
      
      String answer1 = (String)startCB.getSelectedItem();
      String answer2 = (String)endCB.getSelectedItem();
      
      r = s.findDistance(answer1, answer2);
      
      results1.setText("Distance travelled was: " + String.valueOf(r.getDistance()) );
      results2.setText( r.getPath() );
    }
}