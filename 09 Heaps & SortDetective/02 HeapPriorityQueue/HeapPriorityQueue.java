 //Name: Shivani Puli
 //Date: 4/1/19
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      if(isEmpty())
      {
         myHeap.add(obj);
         return true;
      }
      myHeap.add(obj);
      heapUp(myHeap.size()-1);
      return true;
   }
   
   public E remove()
   {
      swap(1,myHeap.size()-1);
      E obj=myHeap.remove(myHeap.size()-1);
      heapDown(1,myHeap.size());
      return obj;
   }
   
   public E peek()
   {
      if(isEmpty())
         return null;
      return myHeap.get(1);
   }
   
   public boolean isEmpty()
   {
      return myHeap.size()==1;
   }
   
   private void heapUp(int k)
   {
      int par=k/2;
      if(myHeap.get(k).compareTo(myHeap.get(par))<0)
         swap(k,par);
      else
         return;
      if(par!=1)
         heapUp(par);
   }
   
   private void swap(int a, int b)
   {
      E obj=myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,obj);
   }
   
   private void heapDown(int k, int size)
   {
      if(k*2>=size)
         return;
      int min=k*2;
      if(k*2+1<size)
         if(myHeap.get(k*2).compareTo(myHeap.get(k*2+1))>0)
            min=k*2+1;
      if(myHeap.get(k).compareTo(myHeap.get(min))<0)
         return;
      swap(k,min);
      heapDown(min,size);
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
