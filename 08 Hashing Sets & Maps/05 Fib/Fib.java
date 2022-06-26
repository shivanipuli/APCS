// Name: Shivani Puli
// Date: 3/15/19

import java.util.*;

interface Fibber
{
   public abstract int fib(int n);
}

public class Fib
{
   public static final int FIBsubN = 42;
   public static void main(String[] args)
   {
      System.out.println("Recursive");
      calculate(new Fib1(), FIBsubN);
      System.out.println("Iterative, stored in an array");
      calculate(new Fib2(FIBsubN + 1), FIBsubN);
      System.out.println("Recursive, stored in an arrayList");
      calculate(new Fib3(), FIBsubN);
      System.out.println("Recursive, stored in a hashMap");
      calculate(new Fib4(), FIBsubN);		
   }
      
   public static void calculate(Fibber fibber, int n)
   {
      long start = System.nanoTime();
      int f = fibber.fib(n);
      long finish = System.nanoTime();
      long time = finish - start;
      
      System.out.print("fib(" + n + ") = " + f);
      System.out.println(" (" + time + "nanoseconds)");		
      System.out.println();		
   }
}
    
class Fib1 implements Fibber
{
   public Fib1()    
   {
   }
   
   public int fib(int n)
   {
      if(n == 1 || n == 2)
         return 1;
      else
         return fib(n - 1) + fib(n - 2);
   }
}
   
class Fib2 implements Fibber
{
   private int[] array;
   
   public Fib2(int n)
   {
      array=new int[n];
      array[1]=1;
      array[2]=1;
      for(int x=3;x<n;x++)
         array[x]=array[x-1]+array[x-2];
   }
   
   public int fib(int n)
   {
      return array[n];
   }
   
   public int[] getArray()   //nice to have
   {
      return array;
   }
}
   
class Fib3 implements Fibber
{
   private ArrayList<Integer> myFibList;
   
   public Fib3()
   {
      myFibList=new ArrayList();
      myFibList.add(-1);
      myFibList.add(1,1);
      myFibList.add(2,1);
   }
   
   public int fib(int n)
   {
      if(myFibList.size()>n)
         return myFibList.get(n);
      myFibList.add(n,fib(n-1) + fib(n-2));
      return myFibList.get(n);
   }
   
   public ArrayList<Integer> getArrayList()   //nice to have
   {
      return myFibList;
   }
}

class Fib4 implements Fibber
{
   private Map<Integer, Integer> myFibMap;
   
   public Fib4()
   {
      myFibMap=new HashMap<Integer, Integer>();
      myFibMap.put(1,1);
      myFibMap.put(2,1);
   }
   
   public int fib(int n)
   {
      if(myFibMap.containsKey(n))
         return myFibMap.get(n);
      else
         myFibMap.put(n,fib(n-1)+fib(n-2));
      return myFibMap.get(n);
   }
   
   public Map<Integer, Integer> getMap()  //nice to have
   {
      return myFibMap;
   }
}
	
   
   
   /*
    Recursive
    fib(42) = 267914296 (3276558048 nanoseconds)
    
    Iterative, stored in an array
    fib(42) = 267914296 (4988 nanoseconds)
    
    Recursive, stored in an arrayList
    fib(42) = 267914296 (64025 nanoseconds)
    
    Recursive, stored in a hashMap
    fib(42) = 267914296 (177793 nanoseconds)
    
   	*/
