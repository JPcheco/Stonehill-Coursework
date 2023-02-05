// Jacob Pacheco
// CSC 211
// Assingment 7.2.1

import java.lang.*;

public class AlmostCompleteTree<E>
{
   private E[] tree;
   private int size;
   
   public AlmostCompleteTree(E[] x, int s) // constructor
   {
      tree = x;
      size = s;
   }
   
   // This is not a binary search tree so data is not sorted here
   public void inOrder(int root) // does inorder traversal
   { 
      if(root < size)
      {
         inOrder(2*root+1);
         System.out.print(tree[root]+ " ");
         inOrder(2*root+2);
      }
   }
   
   public void preOrder(int root) // does preorder traversal
   { 
      if(root < size)
      {
         System.out.print(tree[root]+ " ");
         preOrder(2*root+1);
         preOrder(2*root+2);
      }
   }
   
   public void postOrder(int root) // does postorder traversal
   { 
      if(root < size)
      {
         postOrder(2*root+1);
         postOrder(2*root+2);
         System.out.print(tree[root]+ " ");
      }
   }
   
   public static void main(String[] args)
   {
      Integer[] a = {90, 80, 70, 60, 58, 40, 65, 30, 18, 50, 42, 12, 15, 20, 7, 1, 12, 16, 4};
      
      AlmostCompleteTree<Integer> tree = new AlmostCompleteTree<Integer>(a,a.length);
      
      System.out.println("Inorder traversal is");
      tree.inOrder(0); // the root is at index 0 
      System.out.println();
      
      System.out.println("Preorder traversal is");
      tree.preOrder(0); 
      System.out.println();
      
      System.out.println("Postorder traversal is");
      tree.postOrder(0);
   }
}