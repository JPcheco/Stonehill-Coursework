import java.util.*;
import java.io.*;

public class  BinaryTree<E>
{
   protected class Node
   {
      protected E  data;
      protected Node left;
      protected Node  right;

      public Node()
      {
         data = null;
          left = right = null;
      }
      
      public Node(E x)
      {
         data = x;
         left = right = null;
      }
   }

   protected Node root;

   public BinaryTree()  // constructor
   {
      root = null;
   }

   public int height()  // returns the height of a tree
   {
      return recurHeight(root);
   }
   private int recurHeight(Node root)  // helper method for height
   {
      if (root == null)
         return -1 ;
      else
      {
         int leftTree =  recurHeight(root.left);
         int rightTree = recurHeight(root.right);
         if (leftTree > rightTree)
            return leftTree + 1;
         else return rightTree + 1;
      }
   }

   public int size() // returns the number of nodes
   {
         return recurSize(root);
   }

   private int recurSize(Node root) // helper method for size()
   {
      if (root ==null)
         return 0;
      else
         return recurSize(root.left) + recurSize(root.right) + 1;
   }

   public void preorder( )// traverses root-left-right
   {
         recurPreorder(root);
   }
   
   private void recurPreorder(Node root) // helper
   {
      if( root != null)
      {

         System.out.println(root.data);
         recurPreorder (root.left);
         recurPreorder(root.right);
      }
   }

   public void inorder ()// traverses left-root-right
   {
      recurInorder(root);
   }
   
   private void recurInorder(Node root) // helper
   {
      if( root != null)
      {
         recurInorder(root.left);
         System.out.println(root.data);
         recurInorder(root.right);
      }
   }

   
   
   public void inorderREC(PrintWriter pw)
   {
      recurInorderREC(root, pw);
   }
   
   private void recurInorderREC(Node root, PrintWriter pw) // helper
   {
      if( root != null)
      {
         recurInorderREC(root.left, pw);
         pw.println(root.data);
         recurInorderREC(root.right, pw);
      }
   }

   public void postorder ()  // traverses left-right-root
   {
      recurPostorder(root);
   }

   private void recurPostorder(Node root) //helper
   {
      if( root != null)
      {
         recurPostorder (root.left);
         recurPostorder(root.right);
         System.out.println(root.data);
      }
   }

   public boolean empty()
   {
      return root == null;
   }

   public void insert(E x)
   // inserts a node in subtree of shrter height
   {
      if (root == null)
      {
         root = new Node(x);
          return;
      }
      Node p= root;
      Node q = null;
      while (p != null)
      {

         q = p;
         if (recurHeight(p.left) <= recurHeight(p.right))
            p = p.left;
         else
           p = p.right;
      }
      if (recurHeight(q.left) <= recurHeight(q.right))
      {
          q.left = new Node(x);
      }
      else
      {
          q.right = new Node(x);
      }
   }
}