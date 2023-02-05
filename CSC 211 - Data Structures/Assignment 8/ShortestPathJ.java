// Jacob Pacheco
// CSC 211
// Assingment 8.2.1

import java.util.*;
import java.io.*;

public class ShortestPathJ
{     
   public static Scanner input = new Scanner(System.in);
   public static int NO_PREDECESSOR = 9999;
   
   int FinalDistance = 0;
 
   String Namefile = "Names.txt";
   String Mapfile = "Map.txt"; 

   ArrayList<String> Names = new ArrayList<String>();
   ArrayList<Information> Map = new ArrayList<Information>();  
   ArrayList<Boolean> colorGreen = new ArrayList<Boolean>(); //boolean array of size Name
   
   ArrayList<Information> visited = new ArrayList<Information>();
   PriorityQueue<Information> PQ = new PriorityQueue<Information>();
   
   Stack<String> stack = new Stack<String>();   
   
   public ShortestPathJ()
   {
      init (true);
   }
   
   public void init(boolean readNames)
   {
      int i = 0;
      int size = 0;
  
      FinalDistance = 0;
  
      // clean up the coloredGreen structure
      size = colorGreen.size();
      for ( int j = 0; j < size; j++ )
      {
         colorGreen.set(j, false);
      }  
     
      // clean up the visited structure
      size = visited.size();    
      for ( int j = 0; j < size; j++ )
      {
         visited.remove(0);
      }  
   
      // clean up the priority queue structure
      while ( !PQ.empty() )    
      {
         PQ.remove();
      }
      
      // clean up the map structure
      size = Map.size();    
      for(int j = 0; j < size; j++)
      {
         Map.remove(0);
      }  
      
      // re- read the map file back in   
      //readFiles(readNames);   
      
      readNamesFile(readNames);
      readMapFile(); 
   }
   
   public distanceGUIResults findDistance( String start, String end )
   { 
     int temp;
     init(false); //clear out leftover data
   
     int startLocation = lookupName(start);
     int endlocation = lookupName(end);
   
     // create a struct to return the data within for the GUI
     distanceGUIResults result = new distanceGUIResults();
              
     boolean initialized = false;   
     
     // put the starting vertex on the priority queue
     Information m = new Information(startLocation, NO_PREDECESSOR, 0);
     // put on the priority queue
     PQ.insert(new Information(m)); 
     
     while(true)
     {
         if ( PQ.getSize() == 0 )
         {
            System.out.println();
            continue;
         }   
            
         m = PQ.remove(); //remove the m from the priority queue
         
         // check the colorGreen here...if we've already vsited, skip to teh next one
         if ( colorGreen.get(m.getVertex()) == true )
            continue;
         
         visited.add(m);
         colorGreen.set(m.getVertex(), true);
         
         if(m.getVertex() == endlocation)
         {
            break;
         }
               
         temp = visited.size();    
         for(int i = 0; i < temp; i++) //if m has not been visited
         {
             if(visited.get(i) == m)
             {               
                //add each non visited adjacent vertex to the PQ
                while(!initialized)
                {
                   int v, p;
                   for(int j = 0; j < Map.size(); j++)
                   {
                      if(Map.get(j).getVertex() == m.getVertex())
                      {
                        Information im = new Information(Map.get(j).getPredecessor(), m.getVertex(), (Map.get(j).getDistance() + m.getDistance()) );
                      
                        PQ.insert(im);
                      }   
                   }
                   initialized = true;
                }
              }
              initialized = false;
         }
       }

      FinalDistance = m.getDistance();
      
      while ( m.getVertex() != startLocation)
      {
         stack.push( lookupIndex(m.getVertex()) );
         m = iterateVisited( m.getPredecessor() );
    
      }
      stack.push( lookupIndex(startLocation) );  // don't forget the starting vertex
      
      // create the path string in teh results structure
      String str = new String();
   
      // get the first element
      if ( stack.empty() == false )
          str = str.concat( stack.pop() );
          
      // get teh rest of teh elements, each on proceed by an arrow    
      while ( stack.empty() == false )
      {
          str = str.concat( " --> " + stack.pop() );
      }
      
      result.setDistance(FinalDistance);
      result.setPath(str);
      
      return result;
   }

   public Information iterateVisited(int v)
   {
      Information m;
      
      // walk the visited list and find the vertex v and return it
      Iterator<Information> iter = visited.iterator();
      while (iter.hasNext() )
      {
         // get the next Information element from clean list
         m = iter.next();
            
         if ( m.getVertex() == v )
            return m;
         }  // while (iterator)
    
      return null;   
   }
 /*
   public void displayVisitedVerbose()
   {
      int size = visited.size();
      
      for (int i = 0; i < size; i++ )
         displayInformationVerbose( visited.get(i) );
   }   
   
   // to get the priority queus contents in order, we need to remove each element...but that destroys
   // the priority queue, so we need to make a copy of it as we read it...then return it back into the
   // original so this operation isn not destructive
   public void displayPQ()
   {
      PriorityQueue<Information> qp = new PriorityQueue<Information>(); 
      Information im;


      System.out.println();
      System.out.println("displayPQ:  ");

      while (!PQ.empty())
      {
         im = PQ.remove();
         qp.insert(im);
         displayInformationVerbose(im);
      }

      while (!qp.empty())
      {
         im = qp.remove();
         PQ.insert(im);
      }

      System.out.println();
   }
   
   public void displayInformationVerbose(Information im)
   {
      System.out.println( lookupIndex( im.getVertex()) + " " +  lookupIndex( im.getPredecessor() ) + " " + im.getDistance() );
   }  
 */  
   public void readNamesFile( boolean readName)
   {
     File file = new File (new File(Namefile).getAbsolutePath());
     String s1 = "";

     try
     {
       Scanner sc = new Scanner(file);
       
        if( readName )
        {
           while(sc.hasNextLine()) 
           {
              s1 = sc.nextLine();
              if (s1.equals(""))
                 break;
           
              Names.add(s1);
              colorGreen.add(false);                  
            } // while loop
           sc.close();
         }
      }
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }
   }

   public void readMapFile()
   {
      File file = new File (new File(Mapfile).getAbsolutePath());  
      
      String s1 = "";
      int vertex;
      int predecessor;
      int distance;
      
      try
      {
         Scanner sc = new Scanner(file);
         
         while(sc.hasNextLine()) 
         {
            s1 = sc.nextLine();
            if (s1.equals(""))
               break;
   
           // System.out.println(s1);
            String[] a = s1.split(" ", 5); 
            
            vertex = lookupName(a[0]);
            predecessor = lookupName(a[2]);
            distance = Integer.parseInt(a[4]);
            
            Map.add(new Information(vertex, predecessor, distance));                      
          } // while loop
         sc.close();
      }
      catch (FileNotFoundException e) 
      {
         e.printStackTrace();
      }
   }
      
   public int lookupName(String s)
   {
      int size = Names.size();
      
      for(int i = 0; i < size; i++)
      {
         if(Names.get(i).equals(s))
            return i;
      }  
      return 9999;  
   }
   
   public String lookupIndex(int index)
   {
      if ( index == 9999 )
         return "Foo";
   
      return Names.get(index);   
   }  

   // used to create the list of strings to display in the GUI combo box
   public String[] getChoices ()
   {
      String[] s = new String[Names.size()];
      int index = 0;
      String st;
      
      Iterator<String> iter = Names.iterator();
      while (iter.hasNext() )
      {
         // get the next string from the list
         st = iter.next();
         s[index++] = st;
      }

      return s;
   }
}