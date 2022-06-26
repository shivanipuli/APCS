// Name: Shivani Puli
// Date: 2/15/19

import java.util.*;

/* Practice with a Binary Search Tree. Uses TreeNode.
 * Prompt the user for an input string.  Build a BST 
 * from the letters. Display it as a sideways tree.  
 * Prompt the user for a target and delete that node, 
 * if it exists. Show the updated tree.
 */
public class BinarySearchTreeDelete
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
                           //Case 1a:     ECSBPWANR 
                           //Case 1b:     N
                           //Case 2a:     SNTPOR    
                           //Case 2b:     HBRNVJSZIK  
                           //case 2c:     NFAKG
                           //case 2d:     NSPQX
                           //Case 3.a:    DBNACFSEJHM 
                           //Case 3.b:    DBNACFSEJH 
                           //on the handout: HDJAGKFEOLTMNSU
      TreeNode root = buildTree( null, s );
      System.out.println( display(root, 0) );
      System.out.print("Delete? ");
      String target = sc.next();
      if( contains( root, target ) )
      {
         root = delete( root, target );
         System.out.println("\n" + target+" deleted.");
      }
      else
         System.out.println("\n" + target+" not found");
      System.out.println( display(root, 0) );
   }
   
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
	
   /* Recursive algorithm to build a BST:  if the node is 
    * null, insert the new node.  Else, if the item is less, 
    * set the left node and recur to the left.  Else, if the 
    * item is greater, set the right node and recur to the right.   
	 */
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"") <= 0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   
   private static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current != null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   
   public static TreeNode delete(TreeNode current, String target)
   {
      TreeNode root = current;  //don't lose the root!
      TreeNode parent = null;
      while(current !=null)
      {
         int compare = target.compareTo((String)current.getValue());
        // ------->  your code goes here
         if(compare<0)
         {
            parent=current;
            current=current.getLeft();
         }
         else if(compare>0)
         {
            parent=current;
            current=current.getRight();
         }
         else
         {
            String children=countKids(current);
            if(children.isEmpty())
            {
               if(parent==null)
                  root=null;
               else if(parent.getRight()==null)
                  parent.setLeft(null);
               else
                  parent.setRight(null);
               current=null;
            }
            else if(children.length()==1)
            {
               if(parent==null)
                  if(children.equals("L"))
                     root=current.getLeft();
                  else
                     root=current.getRight();
               else if(parent.getLeft().getValue().equals(current.getValue()))
                  if(children.equals("L"))
                     parent.setLeft(current.getLeft());
                  else
                     parent.setLeft(current.getRight());
               else
                  if(children.equals("L"))
                     parent.setRight(current.getLeft());
                  else
                     parent.setRight(current.getRight());
               current=null;
            }
            else
            {
               String max=max(current.getLeft());
               current.setValue(max);
               delete(current.getLeft(), max);
            }
         }
      
      }
      return root;  //never reached
   }
   public static String countKids(TreeNode t)
   {
      String count="";
      if(t==null)
         return count;
      if(t.getLeft()!=null)
         count+="L";
      if(t.getRight()!=null)
         count+="R";
      return count;
   }
   
   public static String max(TreeNode t)
   {
      if(t==null)
         return "";
      Comparable max=(Comparable)(t.getValue());
      Comparable x=(Comparable)(max(t.getRight()));
      if(x==null)
         return max + "";
      if(max.compareTo(x)>0)
         return max + "";
      else
         return x + "";
   }
   
}