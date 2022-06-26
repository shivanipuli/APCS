// Name:Shivani Puli and Amber Garcha
// Date:1/14/19

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.7.jar

public class Twitter_Driver
{
   private static PrintStream consolePrint;

   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out; // this preserves the standard output so we can get to it later      
   
      // PART III - Connect
      // set classpath, edit properties file
          
      TJTwitter bigBird = new TJTwitter(consolePrint);
      
   	// Part III - Tweet
      // Create and set a String called message below
   	// Uncomment this line to test, but then recomment so that the same
   	// tweet does not get sent out over and over.
   
      
      String message="Malini is cool.";
      //bigBird.tweetOut(message);
      
       
   
      // PART III - Test
      // Choose a public Twitter user's handle 
      
      Scanner scan = new Scanner(System.in);
      consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
      String twitter_handle = scan.next();
       
      // Find and print the most popular word they tweet 
      while (!twitter_handle.equals("done"))
      {
         bigBird.queryHandle(twitter_handle);
         consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.getMostPopularWord()+ ".");
         consolePrint.println("The word appears " + bigBird.getFrequencyMax() + " times.");
         consolePrint.println();
         consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
         twitter_handle = scan.next();
      }
      
      // PART IV
      consolePrint.println("What word would you like to search?");
      String word=scan.next();
      consolePrint.println("Enter Latitude");
      double lat=scan.nextDouble();
      consolePrint.println("Enter Longitude");
      double longi=scan.nextDouble();
      consolePrint.println("Enter Radius");
      int rad=scan.nextInt();
      bigBird.investigate(word,new GeoLocation(lat,longi),rad);
      
   }        
}        
      
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private int numberOfTweets; 
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
   private double averageWords;
  
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      // Connects to Twitter and performs authorizations.
      twitter = TwitterFactory.getSingleton(); 
      consolePrint = console;
      statuses = new ArrayList<Status>();
      terms = new ArrayList<String>();
   }
	
   public List<String> getTerms()
   {
      return terms;
   }
   
   public int getNumberOfTweets()
   {
      return numberOfTweets;
   }
	
   public String getMostPopularWord()
   {
      return popularWord;
   }
	
   public int getFrequencyMax()
   {
      return frequencyMax;
   }
	   
  /******************  Part III - Tweet *******************/
  /** 
   * This method tweets a given message.
   * @param String  a message you wish to Tweet out
   */
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);
   }

   
  /******************  Part III - Test *******************/
  /** 
   * This method queries the tweets of a particular user's handle.
   * @param String  the Twitter handle (username) without the @sign
   */
   @SuppressWarnings("unchecked")
   public void queryHandle(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      terms.clear();
      fetchTweets(handle);
      splitIntoWords();	
      removeCommonEnglishWords();
      sortAndRemoveEmpties();
      mostPopularWord(); 
   }
	
   /** 
    * This method fetches the most recent 2,000 tweets of a particular user's handle and 
    * stores them in an arrayList of Status objects.  Populates statuses.
    * @param String  the Twitter handle (username) without the @sign
    */
   public void fetchTweets(String handle) throws TwitterException, IOException
   {
      // Creates file for dedebugging purposes
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); 
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      numberOfTweets = statuses.size();
      fileout.println("Number of tweets = " + numberOfTweets);
   }   

   /** 
    * This method takes each status and splits them into individual words.   
    * Store the word in terms.  
    */
   public void splitIntoWords()
   {
   terms=new ArrayList<String>(); 
      for(int x=0; x<statuses.size();x++)
      {
         String[] words=statuses.get(x).getText().split(" ") ;
         for(String p : words)
         {
            p=removePunctuation(p);
            p=p.toLowerCase();
            if(!p.isEmpty())
               terms.add(p);
         }
      }
   
   
   }

   /** 
     * This method removes common ruation from each individual word.
     * This method changes everything to lower case.
     * Consider reusing code you wrote for a previous lab.     
     * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
     * @ param String  the word you wish to remove punctuation from
     * @ return String the word without any punctuation, all lower case  
     */
   public String removePunctuation( String s )
   {
      String punct="[,./;:'\"?<>\\[\\]{}|`~!$%^&*() ]";
      String copy="";
      s=s.toLowerCase();
      for(int x=0;x<s.length();x++)
      if(!punct.contains(s.substring(x,x+1)))
      copy+=s.charAt(x);
      return copy;
   	
   }

   /** 
    * This method removes common English words from the list of terms.
    * Remove all words found in commonWords.txt  from the argument list.    
    * The count will not be given in commonWords.txt. You must count the number of words in this method.  
    * This method should NOT throw an excpetion.  Use try/catch.   
    */
   @SuppressWarnings("unchecked")
   public void removeCommonEnglishWords()
   {  
   ArrayList<String> common =new ArrayList();
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("commonWords.txt"));  
      }
      catch(IOException e)
      {
         System.out.println("IOException");
      }
      while(infile.hasNext())
      {
         String t=infile.next().toLowerCase().trim();
         common.add(t);
      }
      infile.close();
      for(int x=0; x<terms.size();x++)
         if(common.contains(terms.get(x)))
         {
            terms.remove(x);
            x--;
         }   
   	
   }

   /** 
    * This method sorts the words in terms in alphabetically (and lexicographic) order.  
    * You should use your sorting code you wrote earlier this year.
    * Remove all empty strings while you are at it.  
    */
   @SuppressWarnings("unchecked")
   public void sortAndRemoveEmpties()
   {
   sort(terms);
      for(int x=0; x<terms.size();x++)
         if(terms.get(x).isEmpty())
            terms.remove(x);      
   }
   private static void sort(List<String> array)
   {
      for(int x=array.size()-1;x>0;x--)
      {
         int index=findMax(array,x);
         swap(array,x,index);
      }
   }
   @SuppressWarnings("unchecked")
    public static int findMax(List<String> array, int upper)
   {
      int index=0;
      for(int x=1;x<=upper;x++)
         if(array.get(index).compareTo(array.get(x))<0)
            index=x;
      return index;
   }
   public static void swap(List<String> array, int a, int b)
   {
      String temp=array.get(a);
      array.set(a,array.get(b));
      array.set(b,temp);
   }

   /** 
	 * This method calculates the word that appears the most times
    * Consider case - should it be case sensitive?  The choice is yours.
    * @post will popopulate the frequencyMax variable with the frequency of the most common word 
    */
   @SuppressWarnings("unchecked")
   public void mostPopularWord()
   {
    int index=count(terms.get(0));
      int max=0;
      for(int x=1;x<terms.size();x++)
      {
      if(!terms.get(x).contains("james")&&!terms.get(x).contains("man"))
      {
         int y=count(terms.get(x));
         if(y>max)
         {
            max=y;
            index= x;
         } 
      }
      }
      frequencyMax=max;
      popularWord= terms.get(index);    
   }
   
   public int count(String s)
   {
      int count =0;
      for(int x=0;x<terms.size();x++)
      {
         if(s.equalsIgnoreCase(terms.get(x)))
            count++;
      }
      return count;
   }    	
   

  /******************  Part IV *******************/
   public void investigate(String word, GeoLocation loc, int radius)
   {
      //Enter your code here
      Query query= new Query(word);
      query.setCount(100);
      //query.setGeoCode(new GeoLocation(38.9085,-77.2405),30,Query.MILES);
      query.setGeoCode(loc,radius,Query.MILES);
      query.setSince("2018-01-1");
      try
      {
      QueryResult result=twitter.search(query);
      System.out.println("Count: " + result.getTweets().size());
      if(result.getTweets().size()==100)
      System.out.println("*Maximum count = 100, enter smaller radius for more specific count.");
      Scanner scan = new Scanner(System.in);
      consolePrint.println("Enter 1 if you would like to see the tweets");
      String hi = scan.next();
      if(hi.equals("1")){
      for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());
            }
      }
      }
      catch (TwitterException e) {
      e.printStackTrace();
      }
   }
 
  /** 
   * This method determines how many people in Arlington, VA 
   * tweet about the Miami Dolphins.  Hint:  not many. :(
   */
   public void sampleInvestigate ()
   {
      Query query = new Query("Miami Dolphins");
      query.setCount(2000);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  
}