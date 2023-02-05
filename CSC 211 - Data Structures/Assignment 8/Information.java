// Jacob Pacheco
// CSC 211
// Assingment 8.2.2

public class Information implements Comparable
{
   private int Vertex;
   private int Predecessor;
   private int Distance;
   
   public Information()
   {
      Vertex = 0;
      Predecessor = 0;
      Distance = 0;
   }
   
   public Information(int v, int p, int d)
   {
      Vertex = v;
      Predecessor = p;
      Distance = d;
   }
   
   public Information( Information m )
   {
      Vertex = m.getVertex();
      Predecessor = m.getPredecessor();
      Distance = m.getDistance();
   }
   
   public void swapVertexandPredecessor()
   {
      int temp = getPredecessor();
      setPredecessor( getVertex() );
      setVertex( temp );
   }
   
   public int getVertex()
   {
      return Vertex;
   }
   
   public void setVertex(int v)
   {
      Vertex = v;
   }
   
   public int getPredecessor()
   {
      return Predecessor;
   }
   
   public void setPredecessor(int p)
   {
      Predecessor = p;
   }
   
   public int getDistance()
   {
      return Distance;
   }
   
   public void setDistance(int d)
   {
      Distance = d;
   }
   
   @Override 
   public int compareTo(Object o)
   {
      int thisDistance;
      int passedDistance;
      
      thisDistance = getDistance();
       
      passedDistance = ((Information)o).getDistance();  
       
      if(thisDistance == passedDistance)
         return 0;     
      else if(thisDistance > passedDistance)
         return 1;
      else
         return -1;
   }
}