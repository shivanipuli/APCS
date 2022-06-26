// Name:Shivani Puli
// Date:2/25/19

import java.util.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberOfWidgets = 10;
   public static void main( String[] args ) 
   {
      // Day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
   		
   	// Day two
   	// Your assignment the second day is to finish the BSTobject class.  
   	// Specifically, prompt the user for a string, put the characters into 
      // a BST, call toString on this tree, and print the size of the tree.
   	System.out.print("Enter a String: ");
      Scanner sc=new Scanner(System.in);
      String input=sc.next();
      tree=build(tree, input);
      System.out.println(tree.toString());
      System.out.println("Size: " + tree.size());
      
      // Day two, Widgets			
   	// Next, fill your BST with 10 Widget objects from widgets.txt.  Display 
      // the tree. Then prompt the user to enter cubits and hands.  If the tree 
      // contains that Widget, delete it, of course restoring the BST order. 
      // Display the new tree and its size. If the tree does not contain that 
      // Widget, print "Not found".*/
   
   	// Day three -- AVL tree  -----------------------
   }
  
   /* Build the tree for Strings, Day 1
    */
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
      tree=new BSTobject();
      for(int pos = 0;pos<str.length();pos++)
         tree.add(str.substring(pos,pos+1));
      return tree;
              
   }

   
   /* Build the tree for Widgets, Day 2
    
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
        System.out.println("File not found.");
      }        
      
      for(int i = 0; i < numberOfWidgets; i++)   
      {
      tree.add(infile.nextLine());
      }
   } */
}

interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );             //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );          //returns the object, not a Node<E>
   public int size();
   public String toString();
}

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
   // Declare 2 fields 
   private Node root;
   private int size;
   // Create a default constructor
   public BSTobject()
   {
      root=null;
      size=0;
   }
   
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
   
   //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t==null)
         t=new Node(obj);
      else if(t.getValue().compareTo(obj)>=0)
         t.setLeft(add(t.getLeft(),obj));
      else
         t.setRight(add(t.getRight(),obj));
      return t;     
   }
   
   /* Implement the interface here.  Use TreeNode as an example,
    * but root is a field. You need add, contains, isEmpty, 
    * delete, size, and toString.  
    */
   public boolean contains( E element )
   {
      return contains(root, element);
   }
   
   public boolean contains(Node<E> head, E obj)
   {
      if(head==null)
         return false;
      if(head.getValue().compareTo(obj)==0)
         return true;
      if(head.getValue().compareTo(obj)>0)
         return contains(head.getLeft(),obj);
      return contains(head.getRight(),obj);
   }
   
   public boolean isEmpty()
   {
      return size==0;
   }
   
   public E delete( E element )          //returns the object, not a Node<E>
   {
      root=delete(root,element);
      size--;
      return element;
   }
   
   public Node delete(Node<E> current, E target)
   {
      Node root = current;  //don't lose the root!
      Node parent = null;
      while(current !=null)
      {
         int compare = target.compareTo(current.getValue());
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
               E max=max(current.getLeft());
               current.setValue(max);
               delete(current.getLeft(), max);
            }
         }
      
      }
      return root;  //never reached
   }
   
   public String countKids(Node t)
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
   
   public E max(Node t)
   {
      if(t==null)
         return null;
      Comparable max=(Comparable)(t.getValue());
      Comparable x=(Comparable)(max(t.getRight()));
      if(x==null)
         return (E)(max);
      if(max.compareTo(x)>0)
         return (E)(max);
      else
         return (E)(x);
   }
   
   public int size()
   {
      return size;
   }
   
   public String toString()
   {
      return display(root, 0);
   }
   
   public String display(Node<E> head, int level)
   {
      String ret="";
      if(head==null)
         return ret;
      ret+=display(head.getRight(), level+1);
      for(int x=0; x<level; x++)
         ret+="\t";
      ret+=head.getValue() + "\n";
   
      ret+=display(head.getLeft(),level+1);
      return ret;
   }


    
   /* Private inner class 
    */  
   private class Node<E>
   {
      // 3 fields 
      private E value;
      private Node left, right;
      // 2 constructors, one-arg and three-arg
      public Node(E x)
      {
         value=x;
      }
      public Node(E x, Node l, Node r)
      {
         value=x;
         left=l;
         right=r;
      }
      public void setValue(E x) 
      {
         value=x;
      }
      public void setLeft(Node x)
      {
         left=x;
      }
      public void setRight(Node x)
      {
         right=x;
      }
      public E getValue()
      {
         return value;
      }
      public Node getLeft()
      {
         return left;
      }
      public Node getRight()
      {
         return right;
      }
   
      //methods--Use TreeNode as an example. See Quick Reference Guide.
   }
}
