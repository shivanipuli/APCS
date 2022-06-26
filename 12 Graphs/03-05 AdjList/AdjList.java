// Name: Shivani Puli 
// Date: 4/30/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 * Graphs4: DFS-BFS
 * and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
   public Vertex(String m)
   { 
      name=m;
      adjacencies=new ArrayList<Vertex>();
   }
  
   public Vertex(String m, ArrayList<Vertex> v)
   {
      name=m;
      adjacencies=v;
   }
  
   public void addEdge(Vertex v)
   {
      adjacencies.add(v); }
  
   public String getName()
   { 
      return name; }
  
   public ArrayList<Vertex> getAdjacencies()
   { 
      return adjacencies; }
  
   public String toString()
   {
      String toRet=name + " [";
      if(!adjacencies.isEmpty())
         toRet+=adjacencies.get(0).getName();
      for(int x=1;x<adjacencies.size();x++)
         toRet+=" " + adjacencies.get(x).getName();
      toRet+="]";
      return toRet;
   }
  
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   //List<Vertex> depthFirstRecur(String name);
   //List<Vertex> depthFirstRecur(Vertex v);
   //void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   boolean isReachable(String source, String target);
   boolean isFullyReachable();
}


public class AdjList implements EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
  
 /* enter your code here  */
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      Scanner infile=new Scanner(new File(fileName));
      while(infile.hasNext())
      {
         String[] line=infile.nextLine().split(" ");
         addVertex(line[0]);
         for(int x=1;x<line.length;x++)
            addEdge(line[0],line[x]);
      }
      infile.close();
   }
   
   public int edgeCount()
   {
      int edges=0;
      for(Vertex v: vertices)
      {
         edges+=v.getAdjacencies().size();
      }
      return edges;
   }
   
   public boolean isReachable(String source, String target)
   {
      List<Vertex> p=breadthFirstSearch(source);
      return (p.contains(getVertex(target)));
   }
   
   public boolean isFullyReachable()
   {
      for(Vertex v: vertices)
      {
         List<Vertex> temp=breadthFirstSearch(v);
         if(temp.size()!=vertices.size())
            return false;
      }
      return true;
   }
   public List<Vertex> depthFirstSearch(String name)
   {
      int t=nameToIndex.get(name);
      if(t<0)
         return null;
      else
         return depthFirstSearch(vertices.get(t));
   }
   
   public List<Vertex> depthFirstSearch(Vertex v)
   {
      List<Vertex> list=new ArrayList<Vertex>();
      Stack<Vertex> stack=new Stack<Vertex>();
      stack.push(v);
      while(!stack.isEmpty())
      {
         Vertex temp=stack.pop();
         if(list.contains(temp))
         {}
         else
         {
            list.add(temp);
            for(Vertex m : temp.getAdjacencies())
               stack.push(m);
         }
      }
      return list;
   }
   
   public List<Vertex> breadthFirstSearch(String name)
   {
      int t=nameToIndex.get(name);
      if(t<0)
         return null;
      else
         return breadthFirstSearch(vertices.get(t));
   }
   
   public List<Vertex> breadthFirstSearch(Vertex v)
   {
      List<Vertex> list=new ArrayList<Vertex>();
      Queue<Vertex> stack=new LinkedList<Vertex>();
      stack.add(v);
      while(!stack.isEmpty())
      {
         Vertex temp=stack.remove();
         if(list.contains(temp))
         {}
         else
         {
            list.add(temp);
            for(Vertex m : temp.getAdjacencies())
               stack.add(m);
         }
      }
      return list;
   }

   public List<Vertex> getVertices()
   { 
      return vertices; }
    
   public Vertex getVertex(int i)
   { 
      return vertices.get(i); }
   
   public Vertex getVertex(String vertexName)
   { 
      return getVertex(nameToIndex.get(vertexName)); }
   
   public Map<String, Integer> getVertexMap()
   { 
      return nameToIndex; }
   
   public void addVertex(String v){
      if(nameToIndex.containsKey(v))
         return;
      nameToIndex.put(v,vertices.size());
      vertices.add(new Vertex(v));
   }
   
   public void addEdge(String source, String target)
   {
      int spot=nameToIndex.get(source);
      Vertex sou=vertices.get(spot);
      if(!nameToIndex.containsKey(target))
         addVertex(target);
      Vertex tar=vertices.get(nameToIndex.get(target));
      sou.addEdge(tar);
      vertices.remove(spot);
      vertices.add(spot,sou);
   }
   
   
   public String toString()  //returns all vertices with their edges (omit commas)
   {
      String t="";
      for(Vertex x : vertices )
         t+=x.toString() + "\n";
      return t;
   }
}


