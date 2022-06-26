//name:Shivani Puli    date:10/12/18
import java.util.*;
import java.io.*;
public class MazeMaster
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      //System.out.print("Enter the maze's filename (no .txt): ");
      //Maze m = new Maze(sc.next());
      Maze m = new Maze();
      //extension:  generate a random maze
      m.display();      //display maze
      System.out.println("Options: ");
      System.out.println("1: Mark all paths.");
      System.out.println("2: Mark all paths, and display the count of all STEPs.");
      System.out.println("3: Mark only the correct path.");
      System.out.println("4: Mark only the correct path. If no path exists, say so.");
      System.out.println("5: Mark only the correct path and display the count of STEPs.\n\tIf no path exists, say so.");
      System.out.print("Please make a selection: ");
      m.solve(sc.nextInt());
      m.display();      //display solved maze
   
   } 
}


class Maze
{
   //Constants
   private final char WALL = 'W';
   private final char DOT = '.';
   private final char START = 'S';
   private final char EXIT = 'E';
   private final char STEP = '*';
   //fields
   private char[][] maze;
   private int startRow, startCol;
  
  //constructors
   public Maze()  //extension:  generate a random maze
   {
      int row=(int)(Math.random()*10)+1;
      int col=(int)(Math.random()*10)+1;
      maze = new char[row][col];
      row=(int)(Math.random()*row);
      col=(int)(Math.random()*col);
      maze[row][col]=EXIT;
      int rowx,colx;
      do
      {
         rowx=(int)(Math.random()*row);
         colx=(int)(Math.random()*col);
      }while(rowx==row && colx==col);
      maze[rowx][colx]=START;
      startRow=rowx;
      startCol=colx;
      for(int x=0; x<maze.length; x++)
      {
         for(int y=0; y<maze[0].length; y++)
         {
            if(maze[x][y]=='\0')//!=EXIT||maze[x][y]!=START)
            {
               int r=(int)(Math.random()*2);
               if(r==0)
                  maze[x][y]=WALL;
               else// if(r==1)
                  maze[x][y]=DOT;
            }
         }
      }
   }
   public Maze(char[][] m)  //copy constructor
   {
      maze = m;
      for(int r = 0; r < maze.length; r++)
      {
         for(int c = 0; c < maze[0].length; c++)
         { 
            if(maze[r][c] == START)      //identify start
            {
               startRow = r;
               startCol = c;
            }
         }
      }
   } 
   public Maze(String filename)    
   {
       //use a try-catch block
       //use next(), not nextLine()
      filename+=".txt";
      Scanner infile=null;
      try{
         infile=new Scanner(new File(filename));
      }
      catch(Exception e)
      {
         System.out.println("File not found");
      }
      maze = new char[infile.nextInt()][infile.nextInt()];
      for(int r=0; r < maze.length; r++)
      {
         String p = infile.next();
         for(int c=0; c< maze[0].length; c++)
         {
            maze[r][c]=p.charAt(c);
            if(maze[r][c]==(START))
            {
               startRow=r;
               startCol=c;
            }
         }
      }
      
   }
   public char[][] getMaze()
   {
      return maze;
   }
   public void display()
   {
      if(maze==null) 
         return;
      for(int a = 0; a<maze.length; a++)
      {
         for(int b = 0; b<maze[0].length; b++)
         {
            System.out.print(maze[a][b]);
         }
         System.out.println("");
      }
      System.out.println("");
   }
   
   public void solve(int n)
   {
      switch(n)
      {
         case 1:
            {
               markAllPaths(startRow, startCol);
               break;
            }
         case 2:
            {
               int count = markAllPathsAndCountStars(startRow, startCol);
               System.out.println("Number of steps = " + count);
               break;
            }
         case 3:
            {
               markTheCorrectPath(startRow, startCol);
               break;
            }
         case 4:         //use mazeNoPath.txt 
            {
               if( !markTheCorrectPath(startRow, startCol) )
                  System.out.println("No path exists."); 
               break;
            }
         case 5:
            {
               if( !markCorrectPathAndCountSteps(startRow, startCol, 0) )
                  System.out.println("No path exists."); 
               break;
            }
         default:
            System.out.println("File not found");   
      }
   }
   
    /*  1  almost like AreaFill*/
   public void markAllPaths(int r, int c)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
      {}
      else if(maze[r][c]==START)
      {
         markAllPaths(r+1,c);
         markAllPaths(r-1,c);
         markAllPaths(r,c+1);
         markAllPaths(r,c-1);
      }
      else if(maze[r][c]==DOT)
      {
         maze[r][c]=STEP;
         markAllPaths(r+1,c);
         markAllPaths(r-1,c);
         markAllPaths(r,c+1);
         markAllPaths(r,c-1);
      }
      
      
   }
   
    /*  2  like AreaFill's counting without a static variable */  
   public int markAllPathsAndCountStars(int r, int c)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
      {
         return 0;}
      else if(maze[r][c]==START)
      {
         return 1+ markAllPathsAndCountStars(r+1,c)+markAllPathsAndCountStars(r-1,c)+markAllPathsAndCountStars(r,c+1)+markAllPathsAndCountStars(r,c-1);
      }
      else if(maze[r][c]==DOT)
      {
         maze[r][c]=STEP;
         return 1+markAllPathsAndCountStars(r+1,c)+markAllPathsAndCountStars(r-1,c)+markAllPathsAndCountStars(r,c+1)+markAllPathsAndCountStars(r,c-1);
      }
      else
         return 0;
   }

   /*  3   recur until you find E, then mark the True path */
   public boolean markTheCorrectPath(int r, int c)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
      {
         return false;
      }
      else if(maze[r][c]==START)
      {
         return markTheCorrectPath(r+1,c)||markTheCorrectPath(r-1,c)||markTheCorrectPath(r,c+1)||markTheCorrectPath(r,c-1);
      }
      else if(maze[r][c]==DOT)
      {
         maze[r][c]=STEP;
         boolean b = markTheCorrectPath(r+1,c)||markTheCorrectPath(r-1,c)||markTheCorrectPath(r,c+1)||markTheCorrectPath(r,c-1);
         if(!b)
            maze[r][c]=DOT;
         return b;
      }
      else if(maze[r][c]==EXIT)
         return true;
      else
         return false;
   }
   /*  4   Mark only the correct path. If no path exists, say so.
           Hint:  the method above returns the boolean that you need.  */
      

   /*  5  Mark only the correct path and display the count of STEPs.
          If no path exists, say so. */
   public boolean markCorrectPathAndCountSteps(int r, int c, int count)
   {
      if(r<0||c<0||r>=maze.length||c>=maze[0].length)
      {
         return false;
      }
      else if(maze[r][c]==START)
      {
         count++;
         return markCorrectPathAndCountSteps(r+1,c,count)||markCorrectPathAndCountSteps(r-1,c,count)||markCorrectPathAndCountSteps(r,c+1,count)||markCorrectPathAndCountSteps(r,c-1,count);
      }
      else if(maze[r][c]==DOT)
      {
         maze[r][c]=STEP;
         count++;
         boolean b = markCorrectPathAndCountSteps(r+1,c,count)||markCorrectPathAndCountSteps(r-1,c,count)||markCorrectPathAndCountSteps(r,c+1,count)||markCorrectPathAndCountSteps(r,c-1,count);
         if(!b)
            maze[r][c]=DOT;
         return b;
      }
      else if(maze[r][c]==EXIT)
      {
         //count++;
         System.out.println(count);
         return true;
      }
      else
         return false;
   
   }
}
