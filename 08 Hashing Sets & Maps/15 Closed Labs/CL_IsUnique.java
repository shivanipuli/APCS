// Name: Shivani Puli
// Date: 03/22/19
/*Write a method isUnique that accepts a Map from strings to strings as 
a parameter and returns true if no two keys map to the same value (and 
false if any two or more keys do map to the same value). For example, 
calling your method on the following map would return true:

{Marty=Stepp, Stuart=Reges, Jessica=Miller, Amanda=Camp, Hal=Perkins}
Calling it on the following map would return false, because of two mappings 
for Perkins and Reges:

{Kendrick=Perkins, Stuart=Reges, Jessica=Miller, Bruce=Reges, Hal=Perkins}
The empty map is considered to be unique, so your method should return true 
if passed an empty map.*/

import java.util.*;
public class CL_IsUnique
{
   public static void main(String[] args)
   {
      Map<String, String> map1 = null;
      for(int c=1; c<=3; c++)
      {
         switch(c)
         {
            case 1:
               map1 = 
                  new HashMap<String, String>(){
                     { put("Marty","Stepp");
                        put("Stuart","Reges");
                        put("Jessica","Miller");
                        put("Amanda","Camp");
                        put("Hal","Perkins");}
                  };           
               break;
               
            case 2:
               map1 = 
                  new HashMap<String, String>(){
                     { put("Kendrick","Perkins");
                        put("Stuart","Reges");
                        put("Jessica","Miller");
                        put("Bruce","Reges");
                        put("Hal","Perkins");}
                  };          
               break;
            case 3:  
               map1 = 
                  new HashMap<String, String>();
            
               break;
         }
         System.out.println( isUnique(map1) );  
      }
   }

   public static boolean isUnique(Map<String, String> map) 
   {
   Map<String, String> reverse = new HashMap();
   for( String key : map.keySet() )
   {
   if(reverse.isEmpty())
   reverse.put(map.get(key),key);
   else if( reverse.containsKey(map.get(key)) )
   return false;
   reverse.put(map.get(key), key);
   }
   return true;
   }
}
 /****************************
  ----jGRASP exec: java CL_IsUnique_teacher
 
 true
 false
 true
 
  ----jGRASP: operation complete.
  ***********************/