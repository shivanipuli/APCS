// Name:Shivani Puli
// Date:12/11/18

import java.io.*;
import java.util.*;

/**
 * Use for-each loops or iterators.
 * Do not use traditional for-loops.
 */  
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }

   /**
    * @return ArrayList containing all values in the int array
    */     
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> myList = new ArrayList<>();
      for ( int num : rawNumbers )
         myList.add( num );
      return myList;
   }
 
   /**
    * @return ArrayList containing all values in the String array
    */    
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList= new ArrayList<String>();
      for( String str : rawWords)
         myList.add(str);
      return myList;
   }

   /**
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return number of negative values in ArrayList a
    */    
   public static int countNeg(ArrayList<Integer> a)
   {
      int count=0;
      ListIterator<Integer> li=a.listIterator();
      while(li.hasNext())
         if(li.next()<0)
            count++;
      return count;      
   }
  
   /**
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return average of all values in the ArrayList a
    */    
   public static double average(ArrayList<Integer> a)
   {
      double sum=0;
      ListIterator<Integer> li=a.listIterator();
      while(li.hasNext())
         sum+=li.next();
      sum/=a.size();
      return sum;
   }

   /**
    * Changes all negative values in ArrayList to 0.
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return ArrayList with negative values turned to 0 
    */    
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ArrayList<Integer> copy=a;
      ListIterator<Integer> li=copy.listIterator();
      while(li.hasNext())
         if(li.next()<0)
         {
            li.remove();
            li.add(0);
         }
      return copy;
   }
  
   /**
    * Deletes all zero values in ArrayList.
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return ArrayList with no zero values. 
    */   
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      ArrayList<Integer> p=a;
      ListIterator<Integer> li=p.listIterator();
      while(li.hasNext())
         if(li.next()==0)
            li.remove();
      return p;
   }

   /**
    * Removes duplicates from list.
    * Precondition:  Arraylist a is not empty; contains String objects
    * @return ArrayList without duplicate movie titles. 
    */   
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> copy=a;
      ListIterator<String> li=copy.listIterator();
      while(li.hasNext())
      {
         String temp=li.next();
         li.remove();
         if(!copy.contains(temp))
            li.add(temp);
      }
      return copy;
   }
   
}

