import java.util.*;  
class ArrayList01Demo{  
   public static void main(String args[]){ 
      int[] arrayPrim=new int[3];         //default primitive int 0
      String[] arrayObj = new String[3];  //default array of null
      ArrayList list=new ArrayList();     //default size 0
      list.add(0);                        //resize to 10, Integer 0, 9 nulls
                                          //int 0 is converted into or "wrapped" in Integer Class
                                             //Wrapper class - automatic - Autoboxing
      list.add("hello");                  //Integer and String objects in same ArrayList
      list.set(0,"hi");                   //set index 0 to "hi", Integer to String
      System.out.println(list.get(0));    //get() method returns element at 0
      //System.out.println(list.get(2));  //OutOfBoundsException - index may not surpass size-1 
      
      list.add(3);
      list.add(7);
     // System.out.println(list.get(2)+list.get(3));  //can't add Objects together, no unboxing
      
      //for-loop to add Integer
      for(int i=0;i<25;i++){
         list.add(5);                     //resize at 10 to 15, then to 22, then to 33
      }                                   //note the growth rate,+size/2
      
      //for-loop to transverse array
      for(int i=0;i<list.size();i++){     //size() method returns length of ArrayLists
         System.out.println(list.get(i));
      }
      
      //enhanced for-each loop to transverse array
         //Which data Type would you use?  
         //Can't use for-each loop when Type isn't specified at declaration of ArrayList
      list.remove("hi");                  //remove(value) to remove by value
      list.remove(0);                     //remove(index) to remove by index
      //for(Integer x: list) System.out.println(x);   //still can't since each element is Object class
      
      ArrayList<Integer> listInt = new ArrayList(11); //set Type to Integer, initial size = 11
      listInt.add(3);
      listInt.add(11);
      System.out.println(listInt.get(0)+listInt.get(1)); //unboxing - auto convert back to int
      
      for(int i=0;i<5;i++){     //size() method returns length of ArrayLists
         listInt.add((int)(Math.random()*10));   //populate with rand # from 0-9
      }
      
      Collections.shuffle(listInt);          
      Collections.sort(listInt);
      Collections.swap(listInt,1,2);
      listInt.add(3);
      Collections.replaceAll(listInt,3, 55);
      listInt.add(7);
      listInt.removeAll(list);
      
      Iterator itr=list.iterator();       //transverse ArrayList with iterator      
      while(itr.hasNext()){  
         System.out.println(itr.next());  
      } 
   }  
}
