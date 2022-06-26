// Name: Shivani Puli
// Date: 3/22/19
/*Finish the CL_ActorsGrades application to 
   1) put some data into a map, 
   2) display the initial data, 
   3) reverse the mapping, and 
   4) display the reversed map.  
   
  Here is the data:
        Jack Nicholson  	A-
        Humphrey Bogart 	A+
        Audrey Hepburn	   A
        Meryl Streep	      A-
        Jimmy Stewart	   A
      */
   
import java.util.*;
public class CL_ActorsGrades
{
   public static void main(String[] args)
   {
      Map<String, String> actorsGrade = input();
      display(actorsGrade);
      System.out.println();
      Map<String, ArrayList<String>> gradeActors = reverse(actorsGrade);
      display2(gradeActors);
   }
   
   public static Map<String, String> input()
   {
   Map<String, String> map=new TreeMap<String, String>();
   map.put("Jack Nicholson","A-");
   map.put("Humphrey Bogart","A+");
   map.put("Audrey Hepburn","A-");
   map.put("Meryl Streep","A-");
   map.put("Jimmy Stewart","A");
   return map;
   }
   
   public static void display( Map<String, String> actorsGrade )
   {
   for( String key : actorsGrade.keySet() )
   System.out.println(key + " (" + actorsGrade.get(key) + ")");
   }
   
   public static Map<String, ArrayList<String>> reverse( Map<String, String> actorsGrade)
   {
   Map<String, ArrayList<String>> reverse = new TreeMap<String, ArrayList<String>>();
   for( String name : actorsGrade.keySet() )
   {
   if(reverse.isEmpty())
   {
   ArrayList<String> names = new ArrayList();
   names.add(name);
   reverse.put(actorsGrade.get(name),names);
   }
   else if(reverse.containsKey(actorsGrade.get(name)) )
   {
   ArrayList<String> names=reverse.get(actorsGrade.get(name));
   names.add(name);
   reverse.put(actorsGrade.get(name) , names);
   }
   else
   {
   ArrayList<String> names = new ArrayList();
   names.add(name);
   reverse.put(actorsGrade.get(name),names);
   }
   }
   return reverse;
   }
   
   public static void display2( Map<String, ArrayList<String>> gradeActors )
   {
   for(String grade : gradeActors.keySet() )
   System.out.println(grade + ": " + gradeActors.get(grade).toString());
   }
}

/**********************
    Audrey Hepburn (A)
    Humphrey Bogart (A+)
    Jack Nicholson (A-)
    Jimmy Stewart (A)
    Meryl Streep (A-)
    
    A: [Audrey Hepburn, Jimmy Stewart]
    A+: [Humphrey Bogart]
    A-: [Jack Nicholson, Meryl Streep]
    
*********************/