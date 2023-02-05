// Jacob Pacheco
// CSC 211
// Assingment 8.2.1

import java.util.*;
import java.io.*;

public class ShortestPath
{    
   public static String NamesFile = "Names.txt";
   public static String MapFile = "Map.txt";
   
   public static int NO_PREDECESSOR = 9999;
   int FinalDistance = 0;

   ArrayList<String> Names = new ArrayList<String>();
   ArrayList<Information> Map = new ArrayList<Information>();  
   ArrayList<Boolean> colorGreen = new ArrayList<Boolean>(); //boolean array of size Name
   
   ArrayList<Information> visited = new ArrayList<Information>();   
   PriorityQueue<Information> PQ = new PriorityQueue<Information>();
   
   Stack<String> path = new Stack<String>();
   
   public ShortestPath(String start, String end) throws IOException
   {     
     readNamesFile();
     readMapFile();  
     
     boolean initialized = false;   
     
     int startLocation = lookupName(start);
     int endlocation = lookupName(end);
     
     // put the starting vertex on the priority queue
     // predecessor and distance are 0 for the first entry
     Information m = new Information(startLocation, NO_PREDECESSOR, 0);
     // put on the priority queue
     PQ.insert(new Information(m));
     
     while(true)
     {               
         if(PQ.empty())
         {
            System.out.println("findDistance: PQ is now empty !");  //debug
            continue;
         }   
            
         m = PQ.remove(); //get the m from the priority queue         
         
         //System.out.print("Just removed from PQ: ");
         //displayInformationVerbose( m );

         
         
         int v = m.getVertex();
         int p = m.getPredecessor();
         int d = m.getDistance();
         
         // check the colorGreen here...if we've already vsited, skip to teh next one
         if(colorGreen.get(v) == true)
            continue;
         
         // mark the vertex that we've been there (i.e. color it green)
         colorGreen.set(v, true);
         // put the new vertex on the visited array
         visited.add(m);
         
         // check if we are at the destination
         if(v == endlocation)
         {
            break;
         }
          
         int previousDistance = d; 
               
         // get the non-visited adjacent vertexs
         ArrayList<Information> temp_m;
         ArrayList<Information> temp_mm;
         temp_m = getAdjacent( v ); 
         temp_mm = removeVisitedNodes(temp_m);
   
         // if there are no nodes to visit, loop back
         if ( temp_mm.size() == 0 )
            continue;
     
         // walk the list of adjacent nodes and add to the pq
         Iterator<Information> iter = temp_mm.iterator();
         while (iter.hasNext() )
         {
            Information M  ;
            
            // get the next Information element from clean list
            M = iter.next();
            
            // update with the previous disance
            int x = M.getDistance() + previousDistance;
            M.setDistance(x);
            
            // add to the pq
            PQ.insert( new Information(M) );
         }  // while (iterator)
   
         // if we're at the destination, break out of the main loop...
         if ( v == endlocation )
         {
            break;
         }
         
         //displayPQ();
         
         
         
       }
 
      m = iterateVisited(endlocation);
      if ( m == null )
      {
         System.out.println("findDistance failed!");  //debug
         System.exit(0);
      }
      
      FinalDistance = m.getDistance();
      
      // at this point, m points to the destination and is the obviously the final place on the path 
      // taken.  Push it on the stack, and get it's predecessor, and push that on the stack.  When we reach the
      // starting point, all we need to do it pop them off the stack and they are on order :-)
      // the path is on the stack backward, just pop off and display
      while ( m.getVertex() != startLocation)
      {
         path.push( lookupIndex(m.getVertex()) );
         m = iterateVisited( m.getPredecessor() );    
      }
      path.push( lookupIndex(startLocation) );  // starting vertex
      
      // create the path string in teh results structure
      String str = new String();
   
      // get the first element
      if ( path.empty() == false )
          str = str.concat( path.pop() );
          
      // get teh rest of teh elements, each on proceed by an arrow    
      while ( path.empty() == false )
      {
          str = str.concat( " --> " + path.pop() );
      }         



  // System.out.print("path: " + str );


      // build the result structure and we're done
      //result.setDistance(FinalDistance);
      //result.setPath(str);
      
      //return result;           
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
   
   // for the given node (predecessor), walk the map and get a list of all the adjacent
   public ArrayList<Information> getAdjacent(int n)
   {
      ArrayList<Information> al = new ArrayList<Information>();
      Information m;
        
      // walk the map and look for instances of v; if one is found, add to the array to return   
      Iterator<Information> iter = Map.iterator();
      while (iter.hasNext() )
      {
         // get the next Information element from the map
         m = iter.next();
         // if either the vertex or predecesor from the map matches, we have a match
         if ( (m.getVertex() == n ) || (m.getPredecessor() == n ) )
         {
            // swap them to keep the predecessor straight 
            if ( m.getVertex() == n )
               m.swapVertexandPredecessor();
         
            al.add(m);
         }
       }
    
      return al;
   }

   // for the given arraylist, walk the remove all visited
   public ArrayList<Information> removeVisitedNodes(ArrayList<Information> al)
   {
      int temp;
      Information m;
      ArrayList<Information> nl = new ArrayList<Information>();

      Iterator<Information> iter = al.iterator();
      while (iter.hasNext() )
      {
         // get the next Information element from the map
         m = iter.next();
         temp = m.getVertex();
         if ( !colorGreen.get(temp) )
         {
            nl.add(m);
         }
     }
      
      return nl;
   }


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
   
   public void readNamesFile() throws IOException
   {
     File file = new File (new File(NamesFile).getAbsolutePath());
     Scanner sc = new Scanner(file);
     String s1 = "";

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

   public void readMapFile() throws IOException
   {
      File file = new File (new File(MapFile).getAbsolutePath());  
      Scanner sc = new Scanner(file);
      
      String s1 = "";
      int vertex;
      int predecessor;
      int distance;
      
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

   public static void main(String[] args) throws IOException
   {
      Scanner input = new Scanner(System.in);
      
      String start = "BOSTON";
      String end = "BUFFALO";
      //, end;
   
    //  System.out.println(" Enter Start and End - XXX to exit");
    //  System.out.print("Start :");
    //  start = input.next();
    //  System.out.print("End :");
    //  end = input.next();
   
      ShortestPath path = new ShortestPath(start,  end);
   }
}

/*            
         while (!pq.empty())
         System.out.println(pq.remove());

         int temp = visited.size();    
         for(int i = 0; i < temp; i++) //if m has not been visited
         {
             if(visited.get(i) == m)
             {               
                //add each non visited adjacent vertex to the PQ
                while(!initialized)
                {
                   //int v, p;
                   for(int j = 0; j < Map.size(); j++)
                   {
                      if(Map.get(j).getVertex() == v)
                      {
                        Information im = new Information(Map.get(j).getPredecessor(), v, (Map.get(j).getDistance() + d) );  //debug
                        
                        //displayInformationVerbose(im); // debug
                      
                        PQ.insert(im);
                        
                        //displayPQ();  // debug
                      }   
                   }
                   initialized = true;
                }
              }
              initialized = false;
         }
       }
      Stack<String> path = new Stack<String>();
      
      System.out.println("Distance: " + m.getDistance());
      
      while(m.getVertex() != startLocation)
      {
         path.push(lookupIndex(m.getVertex()));
         m = visited.get( m.getPredecessor() );
      }
      path.push(lookupIndex(m.getVertex()));
            
      System.out.print("Here's your path: ");
      System.out.print(path.pop());
      while(!path.empty())
      {
         System.out.print(" --> " + path.pop());
      }

*/