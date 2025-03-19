// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTreeNode<T>
{
   T data;
   BinaryTreeNode<T> left;
   BinaryTreeNode<T> right;
   int height;
   
   public BinaryTreeNode ( T d, BinaryTreeNode<T> l, BinaryTreeNode<T> r )
   {
      data = d;
      left = l;
      right = r;
      height = 0;
   }
   
   BinaryTreeNode<T> getLeft () { return left; }
   BinaryTreeNode<T> getRight () { return right; }
}
