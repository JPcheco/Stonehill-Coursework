// Jacob Pacheco
// CSC 211
// Assingment 8.1.1

import java.util.*;
import java.io.*;

public class TopoSort
{
   public String filename = "edges.txt";
   public int filelength = 0;
   
   List<Integer> adj[];
   
   public TopoSort()
   {//read in file
      int source;
      int destination;
      boolean initialized = false;
   
      // read file of movements
      String s1 = "";
 
      File file = new File (new File(filename).getAbsolutePath());
  
      try 
      {
         Scanner sc = new Scanner(file);

         while(sc.hasNextLine()) 
         {
            s1 = sc.nextLine();
            if (s1.equals(""))
               break;
            
            if(!initialized)
            {
               filelength = Integer.parseInt(s1);
               initialized = true;
               
               adj = new List[filelength];
      
               for(int i =0; i < filelength; i++)
                  adj[i] = new ArrayList<Integer>();
            }
            else
            {
               String[] a = s1.split(",", 2); 
            
               source = Integer.parseInt(a[0]);
               destination = Integer.parseInt(a[1]);
               
               adj[source].add(destination);  //add edge to graph 
            }                           
          } // while loop
         sc.close();
      }  //try loop  
      catch(FileNotFoundException e) 
      {
         e.printStackTrace();
      }
   }
   
   public void sort()
   {      
      //array to store the count & count is initialized as 0
      int numCount[] = new int[filelength];
      
      for(int i = 0; i < filelength; i++)
      {
         ArrayList<Integer> temp = (ArrayList<Integer>)adj[i];
         
         for(int node : temp)
            numCount[node]++;
      }
      //Queue to store '0' Nodes
      Queue<Integer> q = new LinkedList<Integer>();
      //add '0' Nodes to queue
      for(int i = 0; i< filelength; i++)
      {
         if(numCount[i] == 0)
            q.add(i);
      }
      
      System.out.print("Topological Sort Output:");
            
      while(!q.isEmpty())
      {
         int x = q.poll();
         
         System.out.print(" " + x);
         
         for(int node: adj[x])
         {
            //if numCount becomes 0, add it to the queue
            if(--numCount[node] == 0) //adjust numCounts
               q.add(node);
         }
      }
   }
      
   public static void main(String[] args) throws IOException
   {
      TopoSort top = new TopoSort();
      top.sort();
   }
}