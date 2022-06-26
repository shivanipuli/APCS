// Name:Shivani Puli
// Date:1/19/18

import java.io.*;
import java.util.*;

public class SongQueue
{
   private static Scanner keyboard;  //use this global Scanner for this lab only
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      keyboard = new Scanner(System.in);
      songQueue = readPlayList();
      printSongList();
      
      String prompt = "Add song (A), Play song (P), Delete song (D), Quit (Q):  ";
      String str = "";
      do{      
         System.out.print(prompt);
         str = keyboard.nextLine().toUpperCase();
         processRequest( str );
         System.out.println();
      }while(!str.equals("Q"));
   }
   
   public static Queue<String> readPlayList() throws IOException
   {
      Scanner infile = new Scanner (new File("songs.txt"));  
      Queue<String> q= new LinkedList();
      do
      {
         String t=infile.nextLine();
      
         t=t.substring(0,t.indexOf('-')-1);
         q.add(t);
      }
      while(infile.hasNextLine());
      infile.close();
      return q;
       
   }
   
   public static void processRequest(String str)
   {
      if(str.equals("A"))
      {
         System.out.print("Song to add? ");
         songQueue.add(keyboard.nextLine());
         printSongList();
      }
      else if(str.equals("P"))
      {
         if(songQueue.isEmpty())
            System.out.println("Empty queue!");
         else
            System.out.println("Now playing: " + songQueue.remove());
         printSongList();
      }
      else if(str.equals("D"))
      {
         System.out.print("Delete which song (exact match)? ");
         String f=keyboard.nextLine();
         if(songQueue.contains(f))
            while(songQueue.contains(f))
               songQueue.remove(f);
         else
            System.out.println("Error:  Song not in list.");
         printSongList();
      }
      else if(str.equals("Q"))
      {
         System.out.println();
         System.out.println("No more music today!");
      }
   }

   public static void printSongList()
   {
      String t="Your music queue: [";
      if(!getQueue().isEmpty())
      {
         for(String m : getQueue())
            t+=m + ", ";
         t=t.substring(0,t.length()-2);
      }
      t+="]";
      System.out.println(t);
   }
   
   public static Queue<String> getQueue()
   {
      return songQueue;
   }
}

/*********************************************

 Your music queue: [Really Love, Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Really Love
 Your music queue: [Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Uptown Funk
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 Delete which song (exact match)?  Alright
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Traveller]
 Delete which song (exact match)?  xxx
 Error:  Song not in list.
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  a
 Song to add? Girl Crush
 Your music queue: [Thinking Out Loud, Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Thinking Out Loud
 Your music queue: [Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Traveller
 Your music queue: [Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Girl Crush
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Empty queue!
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  q
 
 No more music today!

**********************************************/