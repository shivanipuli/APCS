// Name: Saahil Singh
// Date: 5/29/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 * and Graphs7: Dijkstra with Cities
 */

class Edge 
{
//public fields not common on AP exam
   public final wVertex target;  
   public final double weight;   
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
//wVertex getPrevious();   //for Dijkstra 7
//void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}


class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
//private wVertex previous;  //for building the actual path in Dijkstra 7

/*  enter your code for this class here   */ 
   public wVertex(String ting){
      name = ting;
      adjacencies = new ArrayList<Edge>();
   }
   public String getName(){
      return name;
   }
   public String toString()//just added so its easier to see when debugging
   {
      String t=name + minDistance;
      for(Edge e: adjacencies)
         t+=e.target.getName();
      return t;
   }
   public double getMinDistance(){
      return minDistance;
   }
   public void setMinDistance(double m){
      minDistance = m;
   }
   public ArrayList<Edge> getAdjacencies(){
      return adjacencies;
   }
   public void addEdge(wVertex v, double weight){
      adjacencies.add(new Edge(v, weight));
   }
   public int compareTo(wVertex other){
      return (int) (minDistance - other.getMinDistance());
   }

}


interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}


/* Interface for Graphs 7:  Dijkstra with Cities 
 */
/*
interface AdjListWeightedInterfaceWithCities 
{       
List<String> getShortestPathTo(wVertex v);
AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
*/ 

public class AdjListWeighted implements AdjListWeightedInterface  //AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
//the constructor is a no-arg constructor 

/*  enter your code for Graphs 6 */
   public AdjListWeighted(){
   } 
   public List<wVertex> getVertices(){
      return vertices;
   }
   public wVertex getVertex(int i){
      return vertices.get(i);
   }
   public Map<String, Integer> getNameToIndex(){
      return nameToIndex;
   }
   public wVertex getVertex(String vertexName){
      int temp = nameToIndex.get(vertexName);
      return vertices.get(temp);
   }
   public void addVertex(String v){
      if(!nameToIndex.containsKey(v))
      {
         vertices.add(new wVertex(v));
         nameToIndex.put(v, vertices.size() - 1);
      }
   }
   public void addEdge(String source, String target, double weight){
      addVertex(target);
      wVertex temp = getVertex(target);
      wVertex s = getVertex(source);
      s.addEdge(temp, weight);
   }
   public void minimumWeightPath(String vertexName){
      PriorityQueue<wVertex> q = new PriorityQueue<wVertex>();
      wVertex a = getVertex(vertexName);
      a.setMinDistance(0);//min distance to itself should be 0 to start it off
      q.add(a);
      while(!q.isEmpty()){
         wVertex temp = q.remove();
      
         for(Edge e : temp.getAdjacencies()){
            double d = e.weight + temp.getMinDistance();//+a.getMinDistance();
            if(d<e.target.getMinDistance()){//(e.target.compareTo(temp) < e.target.getMinDistance()){
               //double dist = e.target.compareTo(temp);
               e.target.setMinDistance(d);
               q.remove(e.target); 
               q.add(e.target);
            
            }
                        
         }
      }
   }




/*  enter your code for two new methods in Graphs 7 */


} 
