// Name:Shivani Puli
// Date:1/14/19

import java.util.*;

public class Infix_Extension
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp=new ArrayList();
      infixExp.add("9 % 4 * 5"); 
      infixExp.add("24 / 12");  
      infixExp.add("-24 / -12");
      infixExp.add("12 / 0");  
      infixExp.add("2.5 * 2");  
      infixExp.add("-2.5 * 2");
      infixExp.add("( 10 + 5) - 15 * 3"); 
      infixExp.add("( 10 + 5) - 15 * 3"); 
      infixExp.add("3 ? 2"); 
      infixExp.add("3 + 2]");  
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf);  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      String output="";
      Stack<String> stk=new Stack();
      String open="([{";
      String close=")]}";
      for(String x : infixParts)
      {
         if(open.contains(x))
            stk.push(x);
         else if(close.contains(x))
         {
            while(!stk.empty())
               if(!open.contains(stk.peek()))
                  output+=stk.pop() + " ";
               else
                  break;
            if(open.indexOf(stk.peek())==close.indexOf(x))
               stk.pop();
            else
            {
               output="ERROR: String does not have matching parentheses";
               break;
               }
         }
         else if(isNumber(x))
            output+=x + " ";
         else if(Postfix.isOperator(x))
         {
            while(!stk.empty())
               if(!stk.peek().equals("(")&&!isLower(stk.peek().charAt(0),x.charAt(0)))
                  output+=stk.pop() + " ";
               else
                  break;
            stk.push(x);
         }
         else
         {
            output="ERROR: String contains an invalid character";
            stk=new Stack();
         }
      }
      while(!stk.empty())
         output+=stk.pop() + " ";
      return output;
      
   }
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if(c1==('+')||c1==('-'))
         return true;
      if(c2==('*')||c2==('/')||c2==('%'))
         return true;
      return false;
   }
   public static boolean isNumber(String t)
   {
   String legal="1234567890.-";
   for(int r = 0;r <t.length();r++)
   if(!legal.contains(t.substring(r,r+1)))
   return false;
   return true;
   }
}
	
/********************************************

 Infix  -->   Postfix
9 % 4 * 5       9 4 % 5 *        
24 / 12       24 12 /        
-24 / -12       -24 -12 /        
12 / 0       12 0 /        
2.5 * 2       2.5 2 *        
-2.5 * 2       -2.5 2 *        
( 10 + 5) - 15 * 3       10 5 + 15 3 * -         
3 ? 2       ERROR: String contains an invalid character       
(3 + 2       ERROR: String does not have matching parentheses       
3 + 2]       ERROR: String does not have matching parentheses
 
***********************************************/