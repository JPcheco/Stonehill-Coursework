// Jacob Pacheco
// CSC 211
// Assingment 6.2.3

import java.util.*;
import java.io.*;
import java.lang.*;

public class FirstTree implements Serializable 
{
   GuessNode root;
   
   public FirstTree()
   {
      root = new GuessNode("Is it human?"); // some other question
      
      root.yes = new GuessNode("Matt Damon"); // left
      
      root.no = new GuessNode("Elephant"); // right
   }
   
   public static void main(String[] args) throws IOException
   {
      FirstTree root = new FirstTree();
      
      ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("GuessingTree.dat"));
      
      output.writeObject(root);
   }
}