// Name: Shivani Puli
// Date: 03/22/19
/*Write a method counts that accepts a List of integers and a Set of 
integers as parameters, and returns a map from each value in the set 
to the number of occurrences of that value in the list. For example, 
if your method is passed the following list and set as parameters:

list: [4, -2, 3, 9, 4, 17, 5, 29, 14, 87, 4, -2, 100]
set: [-2, 4, 29]
Then your method should return the map {-2=2, 4=3, 29=1}, because there 
are two occurrences of -2, three occurrences of 4, and one occurrence of 29.*/

import java.util.*;
public class CL_Counts
{
   public static void main(String[] args)
   {
      List<Integer> list = null;
      Set<Integer> set = null;
      for(int c=1; c<=4; c++)
      {
         switch(c)
         {
            case 1:
               list=  new ArrayList<Integer>(
                         Arrays.asList(4,-2,3,9,4,17,5,29,14,87,4,-2,100));
               set = new HashSet<Integer>(
                         Arrays.asList(-2,4,29));
               break;
            case 2: 
               list=  new ArrayList<Integer>(
                         Arrays.asList(4));
               set = new HashSet<Integer>(Arrays.asList(-2,4,29));
               break;
            case 3:  
               list=  new ArrayList<Integer>(
                           Arrays.asList(4,-2,3,9,4,17,5,29,14,87,4,-2,100));
               set = new HashSet<Integer>(Arrays.asList(0,50,-1));  
               break;
            case 4:
               list=  new ArrayList<Integer>(
                           Arrays.asList(4,-2,3,9,4,17,5,29,14,87,4,-2,100));
               set = new HashSet<Integer>(Arrays.asList(4)); 
               break;
         }
      
         Map<Integer, Integer> map = counts(list, set);  
         System.out.println( map );  
      }
   }

   public static Map<Integer, Integer> counts(List<Integer> list, Set<Integer> set) 
   {
   Map<Integer, Integer> frequency= new TreeMap();
   for(Integer s : set)
   {
   int count=0;
   for(Integer freq : list)
   if(freq==s)
   count++;
   if(count!=0)
   frequency.put(s,count);
   }
   return frequency;
   }
}

/*******************************
  ----jGRASP exec: java CL_Counts_teacher
 
 {-2=2, 4=3, 29=1}
 {4=1}
 {}
 {4=3}
 
  ----jGRASP: operation complete.
  **************************/