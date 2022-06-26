// Name:Shivani Puli
// Date:9/19/18
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery_short.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
     //for (int i = 0; i < cemetery.length; i++) 
         //System.out.println(cemetery[i].getName() + "\t" + cemetery[i].myBirth() + "\t" + cemetery[i].getAge());
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /* Counts and returns the number of entries in File f.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      int k=0;
      Scanner count=null;
      try
      {
         count = new Scanner(f);
      }
      catch(IOException e)
      {
         System.out.println("no file");
      //System.exit(0);
      }
      
      while(count.hasNextLine()){
         k++;
         count.nextLine();
      }
      /*if(count.hasNext("NAME"))
         k=k-4;*/
      return k;
   }

   /* Reads the data.
      Fills the array with Person objects.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Person[] p=new Person[num];
      Scanner infile = null;
      try
      {
         infile = new Scanner(f);
      }
      catch(IOException e)
      {
         System.out.println("no file");
      //System.exit(0);
      }
      for(int x=0; x< num; x++)
      {
      
         p[x]=makeObjects(infile.nextLine()); 
      }
      return p;
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)
   {
      String name = entry.substring(0,24);
      name.trim();
      String birth=entry.substring(24,36);
      birth.trim();
      String life=entry.substring(36,40);
      life.trim();
      Person t =new Person(name,birth,life);
      return t;
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      int min=0;
      for(int x=1; x<arr.length; x++)
      {
         if(arr[x].getAge()<arr[min].getAge())
            min=x;
      }
      return min;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      int min=0;
      for(int x=1; x<arr.length; x++)
      {
         if(arr[x].getAge()>arr[min].getAge())
            min=x;
      }
      return min;
   }        
} 

class Person
{
   /* private fields  */
   private String myName;
   private double myAge;
   private String myBirth;
   private DecimalFormat df = new DecimalFormat("0.0000");
      
   /* a three-arg constructor  */
   public Person(String name, String birth, String age)
   {
      myName=name;
      myAge=calculateAge(age);
      myBirth=birth;
   }
   /* any necessary accessor methods */
   public String myBirth()
   {
      return myBirth;
   }
   public String getName()
   {
      return myName;
   }
   public double getAge()
   {
      return myAge;
   }
   public double calculateAge(String a)
   {
      double year;
      if(a.contains("d")){
         year=Double.parseDouble(a.substring(0,a.indexOf('d')));
         year/=365;
      }
      else if(a.contains("w")){
         year=Double.parseDouble(a.substring(0,a.indexOf('w')));
         year/=52;
      }
      else{
         year=Double.parseDouble(a);
      }
      String m = df.format(year);
      return Double.parseDouble(m);
   }
}