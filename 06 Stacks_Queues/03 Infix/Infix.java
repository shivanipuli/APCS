// Name:Shivani Puli
// Date:1/14/19

import java.util.*;

public class Infix
{
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
     /*build your list of Infix expressions here  */
      ArrayList<String> infixExp=new ArrayList();
      infixExp.add("8 + 3 * 9 - 8 / 2");
      infixExp.add("3 + 4 * 5"); 
      infixExp.add("3 * 4 + 5");  
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");  
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");  
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");     
       
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      /* enter your code here  */
      String output="";
      Stack<String> stk=new Stack();
      for(String x : infixParts)
      {
         if(x.equals("("))
            stk.push(x);
         else if(x.equals(")"))
         {
            while(!stk.peek().equals("("))
               output+=stk.pop() + " ";
            stk.pop();
         }
         else if(!Postfix.isOperator(x))
            output+=x + " ";
         else
         {
            while(!stk.empty())
               if(!stk.peek().equals("(")&&isLower(x.charAt(0),stk.peek().charAt(0)))
                  output+=stk.pop() + " ";
               else
                  break;
            stk.push(x);
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
      if(c2==('*')||c2==('/'))
         return true;
      return false;
   }
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/