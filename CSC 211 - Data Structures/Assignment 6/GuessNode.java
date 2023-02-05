// Jacob Pacheco
// CSC 211
// Assingment 6.2.2

import java.util.*;
import java.io.*;
import java.lang.*;

//Will reppresent one node in the tree
public class GuessNode implements Serializable
{
   String question;
   GuessNode yes; //left
   GuessNode no;  //right
   
   public GuessNode(String s)
   {
      question = s;
      yes = no = null;
   }
   
   public String getQuestion()
   {
      return question;
   }
   
   public void setQuestion(String s)
   {
      question = s;
   }   
   
   public GuessNode getYesNode()
   {
      return yes;
   }
   
   public void setYesNode(GuessNode n)
   {
      yes = n;
   }
 
   public GuessNode getNoNode()
   {
      return no;
   }
   
   public void setNoNode(GuessNode n)
   {
      no = n;
   }

   public boolean getYesSubNode()
   {
      if(yes == null)
         return false;
      else 
         return true;    
   }

   public boolean getNoSubNode()
   {
      if(no == null)
         return false;
      else 
         return true; 
   }
}