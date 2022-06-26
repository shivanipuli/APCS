//name:Shivani Puli    date:10/24/18
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
public class Searches_Driver
{
   private static int amount=1325;
   public static void main(String[] args) throws Exception
   {
      String[] apple = input("declaration.txt");
      Arrays.sort(apple);  
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("Enter a word: ");
         String target = sc.next();     
         if(target.equals("-1") )
            break;
         Searches.reset();
         int found = Searches.linear(apple, target);
         if(found == -1)
            System.out.println(target + " was not found by the linear search.");
         else
            System.out.println("Linear Search found it at location "+found+" in " + Searches.getLinearCount()+" comparisons.");
         int found2 = Searches.binary(apple, target);
         if(found2 == -1)
            System.out.println(target + " was not found by the binary search.");
         else
            System.out.println("Binary Search found it at location "+found2+" in " + Searches.getBinaryCount()+" comparisons.");
      }
   }
   public static String[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      String[] array = new String[amount];
      for (int k =0; k<amount; k++)    
         array[k] = infile.next();
      infile.close();
      return array;
   }
}
/////////////////////////////////////////////////////////
class Searches
{
   private static int linearCount = 0;
   private static int binaryCount = 0;      
   public static int getLinearCount()
   {
      return linearCount;
   }
   public static int getBinaryCount()
   {
      return binaryCount;
   }
   public static void reset()
   {
      linearCount = 0;
      binaryCount = 0;
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static int linear(Comparable[] array, Comparable target)
   { 
      for(int x=0; x<array.length; x++){
         linearCount++;
         if(array[x].compareTo(target)==0)
            return x;
      }
      return -1;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static int binary(Comparable[] array, Comparable target)
   {
      return binaryhelper(array,target,0,array.length);
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {
      binaryCount++;
      int middle=(start+end)/2;
      if(start==end-1)
         return -1;
      if(array[middle].compareTo(target)==0)
         return middle;
      else if(array[middle].compareTo(target)<0)
         return binaryhelper(array,target,middle+1,end);
      else
         return binaryhelper(array,target,start,middle-1);
   }
}