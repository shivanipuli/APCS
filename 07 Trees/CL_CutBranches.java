// Name: Shivani Puli     
// Date: 2/27/19

/*Write a method cutBranches that eliminates branch nodes that have only one 
child. Nodes that have two children, or none, are unchanged.  When a one-child 
node is removed, it is replaced by that child. This can lead to multiple 
replacements because the child might itself be replaced.

In the first example, "b" has one child "a".  In the new tree, "b" has been replaced
by "a".  "c" has two children.  "d" and "a" are leaves.

In the second example, "2", "4", and "8" have been replaced by their only children.  
"6" has two childen.  "9" and "3" are leaves.
*/ 
public class CL_CutBranches
{
   public static TreeNode root = null;
   public static String s = "cdba";
   public static String tj = "264839"; 

   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
      root = cutBranches();
      System.out.print( "\n" + display( root, 0) );
      System.out.println("---------------------------");
      root = null;
      root = buildTree( root, tj );
      System.out.print( display( root, 0) );
      root = cutBranches();
      System.out.print( "\n"+display( root, 0) );
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
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); 
      return toRet;
   }

   public static TreeNode cutBranches() 
   {
      return cutBranches(root);
   }

   private static TreeNode cutBranches(TreeNode root) 
   {
      if(root==null)
         return null;
      else if(getChildren(root).length()==0)
         return root;
      else if(getChildren(root).length()==2)
      {
         root.setLeft(cutBranches(root.getLeft()));
         root.setRight(cutBranches(root.getRight()));
         return root;
      }
      else if(getChildren(root).contains("L"))
      {
         root=cutBranches(root.getLeft());
         return root;
      }
      else
      {
         root=cutBranches(root.getRight());
         return root;
      }
   }
   
   private static String getChildren(TreeNode root)
   {
      String toRet="";
      if(root==null)
         return "";
      if(root.getLeft()!=null)
         toRet+="L";
      if(root.getRight()!=null)
         toRet+="R";
      return toRet;
   }
}
/********************************************
     ----jGRASP exec: java CL_CutBranches_teacher
 	d
 c
 	b
 		a
 
 	d
 c
 	a
 ---------------------------
 			9
 		8
 	6
 		4
 			3
 2
 
 	9
 6
 	3
 
  ----jGRASP: operation complete.
  **************************************************/
  
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
