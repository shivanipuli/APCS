// Name:Shivani Puli
// Date:9/29/18
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
      long start, end, fib; //why long?
      int[] fibNumber = {1, 5, 10, 20, 30, 40, 41, 42};
      System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
      for(int n = fibNumber[0]; n <= fibNumber[fibNumber.length - 1]; n++)
      { 
         start = System.nanoTime();
         fib = fibIterate(n);
         end = System.nanoTime();
         System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
         start = System.nanoTime();   	
         fib = fibRecur(n);      
         end = System.nanoTime();
         System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
      }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      int count1=1;
      int count2=1;
      boolean one=false;
      for(int x=2; x<n; x++)
      {
         if(one)
            count1+=count2;
         else
            count2+=count1;
         one=!one;
      }
      if(one)
         return count2;
      else
         return count1;    
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
      if(n==1||n==2)
         return 1;
      else
         return fibRecur(n-1)+fibRecur(n-2);
   
         
   }
}