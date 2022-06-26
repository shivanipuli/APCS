// Name:Shivani Puli
// Date:11/15/18

import java.io.*;
import java.util.*;

/**
 * Program takes a text file then creates an index (by line numbers)
 * for all the words in the file.
 * Writes the index into output file.
 * Program prompts user for file names.
 */  
public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}

class DocumentIndex extends ArrayList<IndexEntry>
{
  //ArrayList<IndexEntry> list;
    //constructors
   public DocumentIndex()
   {
      super(0);
   }
   public DocumentIndex(int x)
   {
      super(x);
   }
   /**
    * Calls foundOrInserted, which returns an index.
    * At that position, updates that IndexEntry's 
    * list of line numbers with num.   
    */
   public void addWord(String word, int num)
   {
      int index=foundOrInserted(word);
      if(this.size()<=index)
      {
         IndexEntry p=new IndexEntry(word);
         p.add(num);
         this.add(index, p);
      }
      else if(!this.get(index).getWord().equalsIgnoreCase(word))
      {
         IndexEntry p=new IndexEntry(word);
         p.add(num);
         this.add(index, p);
      }
      else
         this.get(index).add(num);
   }
      
   /**
    * Extracts all words from str, skipping 
    * punctuation and whitespace.
    * For each word calls addWord(word, num).
    * Use split with punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()" 
    */
   public void addAllWords(String str, int num) 
   {
      String[] m=str.split("[,./;:'\"?<>\\[\\]{}|`~!@#$%^&*() ]");
      for(String x : m)
         if(!x.isEmpty())
         {
            addWord(x,num);
         }
   }
      
   /** 
    * Traverses this DocumentIndex comparing word to the words in the 
    * IndexEntry objects in list, looking for correct position of word. 
    * If an IndexEntry with word is not already in that position, the 
    * method creates and inserts a new IndexEntry at that position. 
    * @return position of either the found or the inserted IndexEntry.
    */
   private int foundOrInserted(String word)
   {
      IndexEntry p=new IndexEntry(word);
      if(this.size()==0)
         return 0;
      return binarySearch(p, 0, this.size());
   }
   
   private int linearSearch(IndexEntry word)
   {
      for(int x=0; x<this.size(); x++)
         if(this.get(x).compareTo(word)>=0)
            return x;
      return this.size();
   }
   
   private int binarySearch(IndexEntry word,int low,int high)
   {
      int middle=(low+high)/2;
      if(low==this.size())
         return low;
      if(low>=high-1)
      {
         if(this.get(low).compareTo(word)>=0)
            return low;
         else
            return low+1;
      }
      else if(this.get(middle).compareTo(word)==0)
         return middle;
      else if(this.get(middle).compareTo(word)>0)
         return binarySearch(word,low,middle-1);
      else
         return binarySearch(word,middle+1,high);
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
   //fields
   String word;
   ArrayList<Integer> numsList;
   
   //constructors
   public IndexEntry(String m)
   {
      word=m.toUpperCase();
      numsList=new ArrayList();
   }
   
   public int compareTo(IndexEntry g)
   {
      return word.compareTo(g.getWord());
   }
   /** 
    * Appends num to numList but only if not already in list. 
    */
   public void add(int num)
   {
      if(!numsList.contains(num))
      {
         numsList.add(num);
      }
   }
      
   public String getWord()
   {
      return word;
   }
      
   public String toString()
   {
      String t=word + " ";
      for(int x : numsList)
         t+= x + ", ";
      t=t.substring(0,t.length()-2);
      return t;
   }
}

