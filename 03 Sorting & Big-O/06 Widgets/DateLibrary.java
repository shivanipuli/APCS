import java.util.*;
import java.io.*;
public class DateLibrary
{

/*
Returns true if date is a string with the proper format of a date.
Returns false if the string does not fit the above format for any reason. 
@param date is the date listed in Year- Month-Day format
*/
   public static boolean isValidFormat(String date)
   {   
      if(date.length()!=10)
         return false;
      else if((date.charAt(4)=='-')&&(date.charAt(7)=='-'))
         return false;
      
      String next = (date.substring(0,4)+date.substring(5,7)+date.substring(8)); 
      try 
      { 
   Integer.parseInt(next);
         
      } 
      catch(NumberFormatException e)  
      {   
         return false;
      }
      return true;
   }
        
   


/*
Returns the integer number of the year from date only if it is valid between 0001 and 9999.
Returns false if the format is wrong or the year is invalid. 
@param date is the date listed in Year- Month-Day format.
*/
   public static int getYear(String date)
   {
   
   // if(DateLibrary.isValidDate(date)==false)
   // return -1;
      // 
   // else
   // { 
   // String numericYear = date.substring(0,4);
      // int answer = 0;
   //    //Integer.parseInt(numericYear);
   // if(answer>=1&&answer=<9999)
   // return answer;
   // }
   // else
      return -1;
   }
   
/*
Returns the integer number of the month from date only if it is valid between 1 and 12 inclusive.
Returns false if the format is wrong or the month is invalid. 
@param date is the date listed in Year- Month-Day format
*/
   public static int getMonth(String date)
   {
      return 0;
   
   }
   
/*
Returns the integer number of the day from date only if it is valid between 1 and 31 inclusive.
Returns false if the format is wrong or the day is invalid. 
@param date is the date listed in Year- Month-Day format
*/   
   public static int getDay(String date)
   {
      return 0;
   }
   
/*
Return true if the year is a leap year.
Returns false if it is not a leap year.  
@param year is integer, numeric value of the year. 
*/   
   public static boolean isLeapYear(int year)
   {
   
      return false;
   }
/*
Return true if the date matches the date format and is a valid calendar day.
Returns false otherwise. 
@param date is the date listed in Year- Month-Day format
*/
   public static boolean isValidDate(String date)
   {
      if(!(DateLibrary.isValidFormat(date)))
         return false;
      else if(!((DateLibrary.getYear(date)>=1)&&(DateLibrary.getMonth(date)>=1)&&(DateLibrary.getDay(date))>=1))
         return false;
      else
         return true; 
   }
/*
Returns a negative number if date1 comes BEFORE date 2.
Returns a positive number if date2 comes BEFORE date1. 
Returns a 0 if date2 is the same as date1, or if either or both dates are an invalid format.
@param date is the date listed in Year- Month-Day format
*/      
   public static int compare(String date1, String date2)
   {
   
      return 0;
   }
   
   public static void main(String[] args)
   {
   
   
   
   }
}