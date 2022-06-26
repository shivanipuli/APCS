// Name: Shivani Puli
// Date: 3/29/18

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      /*SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      SIZE = 9;
       double[] heap = new double[SIZE + 1];
       heap = createRandom(heap);
       display(heap);
       makeHeap(heap, SIZE);
       display(heap); 
       sort(heap);
       display(heap);
       System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      for(int x=array.length-1; x>1;x--) {
      swap(array,1,x);
      heapDown(array, 1, x);
      }
      
   
      /*if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);*/
   }
  
   public static void swap(double[] array, int a, int b)
   {
   double temp= array[a];
   array[a]=array[b];
   array[b]=temp;
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
   if(k>size-1)
   return;
   if(array[k]<array[k+1])
   swap(array,k,k+1);
   heapDown(array,k+1,size);
   }
   
   public static boolean isSorted(double[] arr)
   {
   for(int x=1; x<arr.length-1;x++)
   {
   if(arr[x]<arr[x+1])
   return false;
   }
   return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      for(int x =1; x<SIZE; x++)
      {
      int y = (int)(Math.random()*10000)+100;
      array[x]=y/100.00;
      } 
       
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
   sort(array);
   }
}

