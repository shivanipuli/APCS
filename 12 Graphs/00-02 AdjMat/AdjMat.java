// Name: Shivani Puli
// Date: 
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 * Graph1 WarshallDriver,
 * and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
     
   /*  enter your code here  */  
   public AdjMat(int x)
   {
      grid=new int[x][x];
   }
   public int getCost(int from, int to)
   {
      return grid[from][to];
   }
   public int getCost(String from, String to)
   {
      return grid[vertices.get(from)][vertices.get(to)];
   }
   public void allPairsWeighted()
   {
      for(int x=0;x<grid[0].length;x++)
      {
         for(int y=0;y<grid.length;y++)
         {
            if(isEdge(y,x))
               for(int z=0;z<grid[0].length;z++)
                  if(isEdge(x,z))
                  {
                     int weight=grid[x][z]+grid[y][x];
                     grid[y][z]=Math.min(grid[y][z],weight);
                  }
         }
      }
   }
   public boolean isEdge(String from, String to)
   {
      return isEdge(vertices.get(from),vertices.get(to));
   }
   
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }  
     
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fileName));
      vertices=new TreeMap();
      int size=Integer.parseInt(infile.nextLine());
      for(int x=0; x<size;x++)
      {
         String t=infile.nextLine();
         vertices.put(t,x);
      }
      infile.close();
   }
   
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner infile=new Scanner(new File(fileName));
      int size=Integer.parseInt(infile.nextLine());
      grid=new int[size][size];
      for(int x=0;x<size;x++)
      {
         String[] row=infile.nextLine().split(" ");
         for(int y=0;y<row.length;y++)
            grid[x][y]=Integer.parseInt(row[y]);
      }
   }
   
   public void displayVertices()
   {
      for(String key: vertices.keySet())
         System.out.println(vertices.get(key) + "-" + key);
   }
   
   public void allPairsReachability()  // Warshall's Algorithm
   {
      for(int x=0;x<grid.length;x++)
      {
         for(int y=0;y<grid[0].length;y++)
         {
            if(grid[x][y]==1)
               for(int z=0;z<grid.length;z++)
                  if(grid[y][z]==1)
                     grid[x][z]=1;
         }
      }
   }


   public void addEdge(int source, int target)
   {
      grid[source][target]=1;
   }
   
   public void removeEdge(int source, int target)
   {
      grid[source][target]=0;
   }
   
   public boolean isEdge(int from, int to)
   {
      return grid[from][to]<9999 && grid[from][to]>0;
   }
   
   public String toString()
   {
      String toRet="";
      for(int x=0;x<grid.length;x++)
      {
         for(int y=0;y<grid[0].length;y++)
            toRet+=grid[x][y] + "\t";
         toRet+="\n";
      }
      return toRet;
   }   
   
   public int edgeCount()
   {
      int count=0;
      for(int row=0;row<grid.length;row++)
         for(int col=0;col<grid[0].length;col++)
            if(isEdge(row,col))
               count++;
      return count;
   }
   
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> list=new ArrayList();
      for(int y=0;y<grid[source].length;y++)
         if(grid[source][y]==1)
            list.add(y);
      return list;
   }
   
}
