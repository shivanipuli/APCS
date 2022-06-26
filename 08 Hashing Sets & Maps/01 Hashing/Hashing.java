 // Name: Shivani Puli
 // Date: 3/06/19

/* 
   Assignment:  This hashing program results in collisions.
   You are to implement three different collision schemes: linear 
   probing, rehashing, and chaining.  Then implement a search 
   algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while( itemNumber != -1 )
      {
         String key = "Item" + itemNumber;
         int index = table.indexOf(key); 
         if( index >= 0)    //found it
            System.out.println(key + " found  at index " + index);
         else
            System.out.println(key + " not found!");
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));                           
      } 
      System.exit(0);
   }
}

/*********************************************/
interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}
/***************************************************/ 

class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
  
   public HashtableLinearProbe(int size)//constructor
   {
      array=new Object[size];                    
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index]==null)  //empty
      {
         //insert it
         array[index]=obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int linearProbe(int index)
   {     
      for(int x=index; x<array.length;x++)
         if(array[x]==null)
            return x;
      for(int x=0;x<index;x++)
         if(array[x]==null)
            return x;
      return 0;
   }
   
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      int firstInd=index-1;
      if(index==0)
         firstInd=array.length-1;
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a linear probe manner
         {
            System.out.println("Looking at index " + index);
            if(index==firstInd)
               return -1;
            else if(index>=array.length-1)
               index=0;
            else
               index++;
         }
      }
      //not found
      return -1;
   }
}

/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant;  
   
   public HashtableRehash(int size) //constructor
   {
      array=new Object[size];
      constant=2;
      ArrayList<Integer> factors=factor(size);
      ArrayList<Integer> f=factor(constant);
      while(notRelPrime(factors,f))
      {
         constant++;
         f=factor(constant);
      }                
   }
   public boolean notRelPrime(ArrayList<Integer> a, ArrayList<Integer> b)
   {
      for(int y=0;y<b.size();y++)
         if(a.contains(b.get(y)))
            return false;
      return true;
   }
   public ArrayList<Integer> factor(int num)
   {
      ArrayList<Integer> fact=new ArrayList();
      for(int x=2;x<=num;x++)
         if(num%x==0)
            fact.add(x);
      return fact;
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index]==null)  //empty
      {
         //insert it
         array[index]=obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int rehash(int index)
   {
      return (index+constant)%array.length;
   }
   
   public  int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      int firstInd=index;
      do
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            index=rehash(index);
            System.out.println("Looking at index " + index);
         }
      } while(array[index] != null && index!=firstInd);
      return -1;
   }
}

/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   
   public HashtableChaining(int size)
   {
      //instantiate the array
      array=new LinkedList[size];  
      //instantiate the LinkedLists
      for(int x=0; x<size;x++)
         array[x]=new LinkedList<Integer>();                    
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         if((array[index].getFirst()).equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            for(int x=0;x<array[index].size();x++)
               if((array[index].get(x)).equals(obj))
                  return index;
         }
         
      }
      //not found
      return -1;
   }
}