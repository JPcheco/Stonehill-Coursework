public class  BinarySearchTree<E extends Comparable> extends BinaryTree<E>
{
      public BinarySearchTree()  // constructor
      {
        super();
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
            if (x.compareTo(p.data)<=0)
              p= p.left;
            else
              p = p.right;
         }
         if (x.compareTo(q.data)<=0)
         {
             q.left = new Node(x);
         }
         else
         {
             q.right = new Node(x);
         }
      }
      
      public E search(E x)
      {
         if( root == null)
            return null;
         Node p = root;
         while(p != null )
             if (x.compareTo(p.data)<0)      // x < p.data
               p = p.left;
             else if (x.compareTo(p.data)>0) // x > p.data
               p = p.right;
             else
                 return p.data;              // x equal to p.data

           return null;
      }

        public boolean delete(E x)
        {
         Node p = root;
         Node parent = null;
          while (p != null)
          {
            if (x.compareTo(p.data) < 0)
            {
               parent = p;
               p = p.left;
            }
            else if (x.compareTo(p.data) > 0)
            {
               parent = p;
               p = p.right;
            }
            else
               break; //p is pointing to x
          }
          if (p == null)
            return false;

          // p has no left children
          if (p.left == null)
          {
            if (parent == null)
               root = p.right;
            else
            {
               if(x.compareTo(parent.data) < 0)
                  parent.left = p.right;
               else
                  parent.right = p.right;
            }
          }
          else   //p has a left subtree
          {            //find the immediate predecessor of p 
            Node q = p;
            Node r = p.left;   // go left from p

            while( r.right != null)  
                           //continue right as far as possible
            {
               q = r;
               r = r.right;
            }
            p.data = r.data; //put data of predecessor into node p

            if(q.right == r)  // adjust referneces
               q.right = r.left;
            else
               q.left = r.left;
          }
          return true;
   }
}