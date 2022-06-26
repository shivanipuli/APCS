// Name:Shivani Puli     
// Date:11/9/18
import java.util.*;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Extension  ****/
      
      System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   	 	
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   
   /* enter your code here */
  public static ListNode copyNode(ListNode arg)
  {
  if(arg == null)
  return null;
  return new ListNode(arg.getValue(), arg.getNext());
  }
  
  public static ListNode copyList(ListNode arg)
  {
  if(arg==null)
  return null;
  return new ListNode(arg.getValue(), copyList(arg.getNext()));
  }
   // returns the value of the first node, or null if the list is empty 
   public static Object first(ListNode head)
   { 
      if(head.getValue()==null)
      return null;
      return head.getValue(); 
      } 

// returns the value of the second node, or null if the list is empty or if there is only one node.  // hint:  second could call the first of rest. 
   public static Object second(ListNode head) 
   {
      ListNode copy=head;
      copy=copy.getNext();
      if(copy==null)
         return null;
      return copy.getValue();
   }

//returns a reference to the last node in the list, or null if the list is empty.
   public static ListNode pointerToLast(ListNode head) 
   {
      if(head==null)
         return null;
      while(head.getNext()!=null)
      {
         head=head.getNext();
      }
      return head;
   }
//returns a copy of the last node (not just its value!).  copyofLast can be recursive.  
   public static ListNode copyOfLast(ListNode head) 
   {
      if(head==null)
         return null;
      while(head.getNext()!=null)
      {
         head=head.getNext();
      }
      ListNode copy=new ListNode(head.getValue(),null);
      return copy;
   }

//returns a reference to a list whose first node's value is specified by the argument, and the 
//first node's next links to the original list. 
   public static ListNode insertFirst(ListNode head, Object arg) 
   {
      return new ListNode(arg,head);
   }
//returns a reference to a list whose last node's value is specified by the argument, such 
//that this last node has been appended to the original list and had its next is set to null 
   public static ListNode insertLast(ListNode head, Object arg) 
   {
      if(head==null)
         return new ListNode(arg,null);
      ListNode copy=new ListNode(head.getValue(), null);
      head=head.getNext();
      copy.setNext(insertLast(head,arg));
      return copy;
   }   
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 First = computer
 Second = science
 Pointer to Last = hello at ListNode@18767ad
 Copy of Last =    hello at ListNode@a7bdcd
 Insert what? abc
 [abc, computer, science, java, coffee, nonsense, boo, foo, hello, abc]
 

  **********************************************/
