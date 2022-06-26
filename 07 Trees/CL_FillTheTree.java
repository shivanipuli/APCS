// Name: Shivani Puli
// Date: 2/27/19

/*Write a method fillTheTree. 
The method should add nodes until the binary tree is a "full" tree. 
A "full" binary tree is one where all leaves are at the same level. 
Another way of thinking of it is that you are adding dummy nodes to 
the tree until every path from the root to a leaf is the same length. 
A "full" tree's shape is exactly triangular and every branch node has 
exactly two children. Each node you add to the tree should store the 
value 0.  A null tree should return a node with the value of 0.

The expected output has been pasted at the end of this code. */

public class CL_FillTheTree
{
   public static TreeNode root = null;
   public static String A = "651";
   public static String B = "65129436";
   public static String C = "";
   
   public static void main(String[] args)
   {
      System.out.println("Tree A");
      root = buildTree( root, A );
      System.out.print( display( root, 0) );
      System.out.println("---------------------------");
      root = fillTheTree();
      System.out.print( "\n"+display( root, 0) );
      System.out.println("===========================");
      
      System.out.println("Tree B");
      root = null;                     //start all over
      root = buildTree( root, B );
      System.out.print( display( root, 0) );
      System.out.println("---------------------------");
      root = fillTheTree();
      System.out.print( "\n"+display( root, 0) ); 
      System.out.println("===========================");
      
      System.out.println("Tree C");
      root = null;                    //start all over
      root = buildTree( root, C );
      System.out.print( display( root, 0) );
      System.out.println("---------------------------");
      root = fillTheTree();
      System.out.println("---------------------------");
      System.out.print( "\n"+display( root, 0) );
      System.out.println("===========================");     
   }
   
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
   public static TreeNode insert(TreeNode t, String s)
   {
      if(t == null)
         return new TreeNode(s, null, null);
      else
      {
         if(s.compareTo(""+t.getValue()) <= 0) 
            t.setLeft(insert(t.getLeft(), s));
         else
            t.setRight(insert(t.getRight(), s));
      }
      return t;
   }

   public static String display(TreeNode t, int level)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toReturn += "\t";
      toReturn += t.getValue() + "\n";
      toReturn += display(t.getLeft(), level + 1); //recurse left
      return toReturn;
   }
   
   public static int height(TreeNode t)
   {
      if(t==null) 
         return 0;
      int left=0;
      int right=0;
      if(t.getLeft()!=null) 
         left = 1 + height(t.getLeft());
      if(t.getRight()!=null) 
         right = 1 + height(t.getRight());
      return Math.max(left, right);
   }
 
   public static TreeNode fillTheTree() 
   {
      return fillTheTree(root,height(root));  
     
   }

   private static TreeNode fillTheTree(TreeNode root, int height) 
   {
      if(root==null)
         root=new TreeNode("0");
      if(height<1)
         return root;
      else
      {
         root.setLeft(fillTheTree(root.getLeft(),height-1));
         root.setRight(fillTheTree(root.getRight(),height-1));
         return root;
      }
   }
}
 
 /**********************************************
      ----jGRASP exec: java CL_FillTheTree_teacher
 Tree A
 6
 	5
 		1
 ---------------------------
 
 		0
 	0
 		0
 6
 		0
 	5
 		1
 ===========================
 Tree B
 	9
 6
 		6
 	5
 				4
 					3
 			2
 		1
 ---------------------------
 
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 		0
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 	9
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 		0
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 6
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 		6
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 	5
 					0
 				4
 					3
 			2
 					0
 				0
 					0
 		1
 					0
 				0
 					0
 			0
 					0
 				0
 					0
 ===========================
 Tree C
 ---------------------------
 ---------------------------
 
 0
 ===========================
 
  ----jGRASP: operation complete.  
  ***************************************/
  
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
