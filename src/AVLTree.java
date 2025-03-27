// Hussein's AVL Tree
// 2 April 2017
// Hussein Suleman
// reference: kukuruku.co/post/avl-trees/
// modified by Ziyanda Mthethwa

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * The AVLTree class implements an AVL tree, a self-balancing binary search tree.
 * It supports insertion, searching, and balancing operations to maintain the tree's height.
 * 
 * @author Hussein Suleman
 * @modifiedBy Ziyanda Mthethwa
 * @date 2025-03-19
 */
public class AVLTree
{
   private BinaryTreeNode<Statement> root;
   private  int  countSearch = 0;
   private  int  countInsert = 0;

   /**
     * Calculates the height of a given node.
     * 
     * @param node The node whose height is to be calculated.
     * @return The height of the node, or -1 if the node is null.
     */
   public int height ( BinaryTreeNode<Statement> node )
   {
      if (node != null)
         return node.height;
      return -1;
   }
   /**
     * Calculates the balance factor of a given node.
     * 
     * @param node The node whose balance factor is to be calculated.
     * @return The balance factor of the node.
     */
   public int balanceFactor ( BinaryTreeNode<Statement> node )
   {
      return height (node.right) - height (node.left);
   }
   /**
     * Updates the height of a given node based on its children.
     * 
     * @param node The node whose height is to be updated.
     */
   public void fixHeight ( BinaryTreeNode<Statement> node )
   {
      node.height = Math.max (height (node.left), height (node.right)) + 1;
   }
    /**
     * Performs a right rotation on a given node.
     * 
     * @param p The node to rotate.
     * @return The new root of the subtree after rotation.
     */
   public BinaryTreeNode<Statement> rotateRight ( BinaryTreeNode<Statement> p )
   {
      BinaryTreeNode<Statement> q = p.left;
      p.left = q.right;
      q.right = p;
      fixHeight (p);
      fixHeight (q);
      return q;
   }
/**
     * Performs a left rotation on a given node.
     * 
     * @param q The node to rotate.
     * @return The new root of the subtree after rotation.
     */
   public BinaryTreeNode<Statement> rotateLeft ( BinaryTreeNode<Statement> q )
   {
      BinaryTreeNode<Statement> p = q.right;
      q.right = p.left;
      p.left = q;
      fixHeight (q);
      fixHeight (p);
      return p;
   }
   /**
     * Balances a given node to maintain the AVL tree property.
     * 
     * @param p The node to balance.
     * @return The balanced node.
     */
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

    /**
     * Inserts a new statement into the AVL tree.
     * 
     * @param d The statement to insert.
     */
   public void insert ( Statement d )
   {
      root = insert (d, root);
   }
   /**
     * Recursively inserts a new statement into the AVL tree.
     * 
     * @param d The statement to insert.
     * @param node The current node in the tree.
     * @return The updated node after insertion.
     */
   public BinaryTreeNode<Statement> insert ( Statement d, BinaryTreeNode<Statement> node )
   {countInsert++;
      if (node == null){
         return new BinaryTreeNode<Statement> (d, null, null);
      }
         countInsert++;
      if (d.compareTo (node.data) <= 0){
         node.left = insert (d, node.left);
      }else{
         countInsert++;
         node.right = insert (d, node.right);   
         
      }
      return balance (node);
   }
   
 /**
     * Searches for a statement in the AVL tree.
     * 
     * @param d The statement to search for.
     * @return The node containing the statement, or null if not found.
     */
  
   public BinaryTreeNode<Statement> find (Statement d )
   {countSearch++;
   if (root == null) {
      System.out.println("Tree is empty");
      return null;
   }
   
   else {
      return find (d, root);}
   }
/**
     * Recursively searches for a statement in the AVL tree.
     * 
     * @param d The statement to search for.
     * @param node The current node in the tree.
     * @return The node containing the statement, or null if not found.
     */
   public BinaryTreeNode<Statement> find ( Statement d, BinaryTreeNode<Statement> node )
   {//
if (node == null){
   return null;

}
countSearch++;
   if (d.compareTo (node.data) == 0) 
      return node;
   countSearch++;
   if (d.compareTo (node.data) < 0)
      return (node.left == null) ? null : find (d, node.left);
   return (node.right == null) ? null : find (d, node.right);
   }

    // Existing methods and fields
    /**
     * Loads statements from a file and inserts them into the AVL tree.
     * 
     * @param filename The name of the file to load.
     * @return The number of statements successfully loaded.
     * @throws FileNotFoundException If the file is not found.
     */

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
                try{
               } catch (NumberFormatException e) {
                  System.err.println("Error parsing confidence score: " + parts[2]);
                  continue; // Skip invalid entries
              }
                this.insert(new Statement(term, statement, confidenceScore));
                count++;
            }
        }

        scanner.close();
        return count;
    }
    /*
     * 
     */
    public void resetCounters() {
      countSearch = 0;
      countInsert = 0;
  }
  
/**
 * new method to print operation counts
 * 
 */
public void printOperationsCounts(){
   System.out.println("Total insert comparisons: "+ countInsert);
   System.out.println("Total search comparisons: "+ countSearch);
}
public int getInsertCount(){
   return countInsert;
}
public int getSearchCount(){
   return countSearch;
}
}


   
   

