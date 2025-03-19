// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/
// modified by Ziyanda Mthethwa

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AVLTree
{
   private BinaryTreeNode<Statement> root;
   public int height ( BinaryTreeNode<Statement> node )
   {
      if (node != null)
         return node.height;
      return -1;
   }
   
   public int balanceFactor ( BinaryTreeNode<Statement> node )
   {
      return height (node.right) - height (node.left);
   }
   
   public void fixHeight ( BinaryTreeNode<Statement> node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }
   
   public BinaryTreeNode<Statement> rotateRight ( BinaryTreeNode<Statement> p )
   {
      BinaryTreeNode<Statement> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }

   public BinaryTreeNode<Statement> rotateLeft ( BinaryTreeNode<Statement> q )
   {
      BinaryTreeNode<Statement> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }
   
   public BinaryTreeNode<Statement> balance ( BinaryTreeNode<Statement> p )
   {
      fixHeight (p);
      if (balanceFactor (p) == 2)
      {
         if (balanceFactor (p.right) < 0)
            p.right = rotateRight (p.right);
         return rotateLeft (p);
      }
      if (balanceFactor (p) == -2)
      {
         if (balanceFactor (p.left) > 0)
            p.left = rotateLeft (p.left);
         return rotateRight (p);
      }
      return p;
   }

   public void insert ( Statement d )
   {
      root = insert (d, root);
   }
   public BinaryTreeNode<Statement> insert ( Statement d, BinaryTreeNode<Statement> node )
   {
      if (node == null)
         return new BinaryTreeNode<Statement> (d, null, null);
      if (d.compareTo (node.data) <= 0)
         node.left = insert (d, node.left);
      else
         node.right = insert (d, node.right);
      return balance (node);
   }
   
 
  
   public BinaryTreeNode<Statement> find (Statement d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }
   public BinaryTreeNode<Statement> find ( Statement d, BinaryTreeNode<Statement> node )
   {
      if (d.compareTo (node.data) == 0) 
         return node;
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
   }

    // Existing methods and fields

    public int loadFromFile(String filename) throws FileNotFoundException {
        int count = 0;
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            String[] parts = data.split("\t", 3);
            if (parts.length == 3) {
                String term = parts[0].trim();
                String statement = parts[1].trim();
                double confidenceScore = Double.parseDouble(parts[2].trim());
                this.insert(new Statement(term, statement, confidenceScore));
                count++;
            }
        }

        scanner.close();
        return count;
    }



}


   
   

