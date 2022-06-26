// Name: Shivani Puli
// Date: 1/08/19

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
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
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn ="";
      if(t==null)
         return "";     
         
      toReturn += inorderTraverse(t.getLeft()); 		//recurse left
      toReturn +=t.getValue() + " ";   		 					//inorder visit
      toReturn += inorderTraverse(t.getRight());		//recurse right
   
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      if(t==null)
         return "";
      String toReturn ="";
      toReturn+= postorderTraverse(t.getLeft());
      toReturn+= postorderTraverse(t.getRight());
      toReturn+= t.getValue() + " ";
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      if(t==null)
         return 0;
      return 1+countNodes(t.getLeft()) + countNodes(t.getRight());
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t==null)
         return 0;
      else if(isLeaf(t))
         return 1;
      return countLeaves(t.getLeft()) + countLeaves(t.getRight());
   }
   public static boolean isLeaf(TreeNode t)
   {
      if(t==null)
         return true;
      if(t.getLeft()!=null)
         return false;
      if(t.getRight()!=null)
         return false;
      return true;
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)
   {
      if(t==null)
         return 0;
      else if(isLeaf(t))
         return 0;
      else if(isLeaf(t.getLeft())&&isLeaf(t.getRight()))
         return 0;
      else 
         return 1+countGrandParents(t.getLeft()) + countGrandParents(t.getRight());
   }
   
   public static int countOnlys(TreeNode t)
   {
      if(isLeaf(t))
         return 0;
      if(t.getLeft()==null && t.getRight()!=null)
         return 1 + countOnlys(t.getRight());
      else if(t.getLeft()!=null && t.getRight()==null)
         return 1+ countOnlys(t.getLeft());
      return countOnlys(t.getLeft()) + countOnlys(t.getRight());
   }
   
   /* Returns the max of the heights on both sides of the tree
	 */
   public static int height(TreeNode t)
   {
      if(t==null)
         return -1;
      return 1 + Math.max(height(t.getLeft()),height(t.getRight()));
      
   }
   
   /* Returns the max of sum of heights on both sides of tree
	 */   
   public static int longestPath(TreeNode t)
   {
      if(t==null)
         return 0;
      return 2+height(t.getLeft()) + height(t.getRight());
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t==null)
         return null;
      Comparable min = (Comparable)(t.getValue());
      if(t.getRight()!=null)
      {
         Comparable right=(Comparable)(min(t.getRight()));
         if(min.compareTo(right)>0)
            min=right;
      }
      if(t.getLeft()!=null)
      {
         Comparable left=(Comparable)(min(t.getLeft()));
         if(min.compareTo(left)>0)
            min=left;
      }
      return min;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t==null)
         return null;
      Comparable max = (Comparable)(t.getValue());
      if(t.getRight()!=null)
      {
         Comparable right=(Comparable)(max(t.getRight()));
         if(max.compareTo(right)<0)
            max=right;
      }
      if(t.getLeft()!=null)
      {
         Comparable left=(Comparable)(max(t.getLeft()));
         if(max.compareTo(left)<0)
            max=left;
      }
      return max;
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      String print="";
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      q.add(t);
      while(!q.isEmpty())
      {
         TreeNode current=q.remove();
         if(current!=null)
         {
            print+=current.getValue() + "";
            q.add(current.getLeft());
            q.add(current.getRight());
         }
      }
      return print;
      
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
