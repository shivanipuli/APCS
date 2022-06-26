// Name:Shivani Puli
// Date:6/01/19
 
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
   
   public wVertex getTarget()
   { 
      return target; }
   
   public double getWeight()
   { 
      return weight; }
   
   public String toString()
   { 
      return target.getName() + " : " + weight; }
   
   public boolean equals(Edge other)
   { 
      return target.getName().equals(other.getTarget().getName()); }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
   public wVertex( String n)
   {
      name=n;
      adjacencies=new ArrayList<Edge>();
   }
   public void setPrevious(wVertex v)
   { previous=v; }
   public wVertex getPrevious()
   { 
      return previous; }
   public String getName()
   { 
      return name; }
   
   public double getMinDistance()
   { 
      return minDistance; }
   
   public void setMinDistance(double m)
   { minDistance=m; }
   
   public ArrayList<Edge> getAdjacencies()
   { 
      return adjacencies; }
   
   public void addEdge(wVertex v, double weight)
   { adjacencies.add(new Edge(v,weight)); } 
   
   public String toString()
   {
      String t=name + minDistance +":";
      for(Edge x: adjacencies)
         t+=x.getTarget().getName();
      return t;
   }
   public int compareTo(wVertex other)
   { 
      return (int)(this.getMinDistance()-other.getMinDistance()); }
   public boolean equals(wVertex other)
   { 
      return name.equals(other.getName()); }
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

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 

public class AdjListWeighted implements AdjListWeightedInterface  //AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
  
   /*  enter your code for Graphs 6 */ 
   public List<wVertex> getVertices()
   { 
      return vertices; }
   
   public Map<String, Integer> getNameToIndex()
   { 
      return nameToIndex; }
   
   public wVertex getVertex(int i)
   { 
      return vertices.get(i); }
      
   public wVertex getVertex(String vertexName)
   { 
      return getVertex(nameToIndex.get(vertexName)); }
   
   public void addVertex(String v)
   {
      vertices.add(new wVertex(v));
      nameToIndex.put(v,vertices.size()-1);
   }
   
   public void addEdge(String source, String target, double weight)
   {
      int x=nameToIndex.get(source);
      int y=nameToIndex.get(target);
      wVertex temp=vertices.get(x);
      temp.addEdge(getVertex(y),weight);
      vertices.remove(x);
      vertices.add(x,temp);
   }
   public void minimumWeightPath(String vertexName)   //Dijkstra's
   {
      PriorityQueue<wVertex> pq=new PriorityQueue();
      wVertex v=getVertex(vertexName);
      v.setMinDistance(0);
      pq.add(v);
      List<wVertex> paths=new ArrayList();
      while(!pq.isEmpty())
      {
         wVertex start=pq.remove();
         paths.add(start);
         for(Edge x : start.getAdjacencies() )
         { 
            wVertex temp=x.getTarget();
            double old=temp.getMinDistance();
            double news=start.getMinDistance()+x.getWeight();
            if(news<old)
            {
               temp.setPrevious(start);
               temp.setMinDistance(news);
            }
            if(!pq.contains(temp)&&!paths.contains(temp))
               pq.add(temp);
                        
         }
      }
   }

   
   
   
   /*  enter your code for two new methods in Graphs 7 */
   public List<String> getShortestPathTo(wVertex v)
   {
      List<String> path=new ArrayList();
      if(v.getMinDistance()==Double.POSITIVE_INFINITY)
      return path;
      while(v.getMinDistance()!=0)
      {
      path.add(v.getName());
      v=v.getPrevious();
      }
      path.add(v.getName());
      List<String> reverse=new ArrayList();
      for(int x=path.size()-1;x>=0;x--)
      reverse.add(path.get(x));
      return reverse;
   }

   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      Scanner sc=new Scanner(vertexNames);
      int size=Integer.parseInt(sc.nextLine());
      for(int x=0;x<size;x++)
         addVertex(sc.nextLine());
      sc.close();
      sc=new Scanner(edgeListData);
      while(sc.hasNext())
         addEdge(sc.next(),sc.next(),sc.nextInt());
      sc.close();
      return this;
   } 
   
}   


