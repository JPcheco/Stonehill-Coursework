// Jacob Pacheco
// CSC 211
// Assingment 6.2.1

import java.util.*;
import java.io.*;
import java.lang.*;

public class GuessingGame
{    
   FirstTree t;
   
   public GuessingGame() throws IOException, ClassNotFoundException
   {
      // Read the tree object from disk
      ObjectInputStream input = new ObjectInputStream(new FileInputStream("GuessingTree.dat"));
      t = (FirstTree)input.readObject();
   }
   
   public boolean isLeaf(GuessNode p)
   { //if we are a leaf meaning no .yes and .no
      if(p.getYesSubNode()==false && p.getNoSubNode()==false)
         return false;
      else
         return true;
   }
   
   public void play() //play the game
   {
      Scanner input = new Scanner(System.in);
      String question, answer;
      boolean valid = false;
      
      GuessNode node = t.root;
      GuessNode helper = null;
      
      System.out.println("Computer: Think of an Object or Person");
      
      do
      {
         helper = node;
      
         question = node.getQuestion(); // get question from tree
         System.out.println("Computer: " + question);     
         
         do //get valid input from user
         {
            answer = input.nextLine(); //user response

            if(answer.equals("Y") || answer.equals("y"))
            {
               node = node.yes; // update root to yesNode
               valid = true;
            }
            else if(answer.equals("N") || answer.equals("n"))
            {
               node = node.no; //update root to noNode
               valid = true;
            }
            else
               System.out.println("Invalid Input... try again");
         }while(valid == false); 
         valid = false;  
      
      }while(isLeaf(node));
      
      question = node.getQuestion(); // get question from tree
      System.out.println("Is it " + question + "?");      
      
      if(helper.getYesNode().equals(node))
         helper.setYesNode(null);
      else if(helper.getNoNode().equals(node))
         helper.setNoNode(null);
      
      GuessNode newAnswer, newQuestion;      
      do
      {
         answer = input.nextLine(); //user response
         if(answer.equals("Y") || answer.equals("y")) //yes
         {
            System.out.println("Computer: Got it!");
            valid = true;
         }
         else if(answer.equals("N") || answer.equals("n")) //no
         {
            //get new question and answer
            System.out.println("Who/What were you thinking of?");
            answer = input.nextLine();            
            newAnswer = new GuessNode(answer);
          
            System.out.println("Enter a question a yes/no question thats question would distinguish " +node.getQuestion()+ " from " +answer); 
            answer = input.nextLine();
            newQuestion = new GuessNode(answer);                      
                       
            newQuestion.setYesNode(newAnswer);
            newQuestion.setNoNode(node);          
            
            if(helper.getYesSubNode() == false)
               helper.setYesNode(newQuestion);
            else if(helper.getNoSubNode() == false)
               helper.setNoNode(newQuestion);
               
            valid = true;
         }
         else
               System.out.println("Invalid Input... try again");
      }while(valid == false);

      System.out.println();
   }
 
   public static void main(String[] args) throws IOException, ClassNotFoundException
   {
      Scanner in = new Scanner(System.in);
      GuessingGame g = new GuessingGame(); 
      
      String answer;

      do
      {
         g.play();
         
         System.out.print("Play again? Y or y for yes any other key for no: ");
      
         answer = in.nextLine();
      }while(answer.equals("Y") || answer.equals("y"));
      
      // save the updated tree to the disk
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("GuessingTree.dat"));
      output.writeObject(g.t);
   }
}