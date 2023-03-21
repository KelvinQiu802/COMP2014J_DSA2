package dsa.impl;


import dsa.iface.IBinarySearchTree;
import dsa.iface.IPosition;

public class BinarySearchTree<T extends Comparable<T>> extends ProperLinkedBinaryTree<T> implements IBinarySearchTree<T> {

   protected IPosition<T> find( IPosition<T> node, T value ) {
      
	   // 1. Return the node if it is external.
	   // 2. Compare the element of the node with 'value'.
	   // 3. If the value is less than the node's element, recursively call this method to search the left sub-tree.
	   // 4. If the value is greater than the node's element, recursively call this method to search the right sub-tree.
	   // 5. If the value is equal to the node's element, we have found it! Return this node.
      return null; // <-- This is so that the class will compile. Remove it when writing your code.
   }

   public void insert( T value ) {
	   // 1. Use the 'find' 'method to find the external node where this should be inserted (beginning at the root)
	   // 2. Expand the external node that is found, to insert the element.
	   //       You can use the expandExternal(INode<T>, T e) method from ProperLinkedBinaryTree for this.
   }

   public void remove( T value ) {
	   // 1. Use the 'find' method (starting at the root) to find the node that contains the value.
	   // 2. If an internal node with at least one external child was returned you can remove it using the remove(INode<T> n) method from ProperLinkedBinaryTree
	   // 3. If the node has two internal children, find the node with the next biggest value, swap the value and delete that node.
   }

   public boolean contains( T value ) {
	   // 1. Use the 'find' method to find the node that contains the value (if it is in the tree).
	   // 2. If 'find' returned an internal node, return true. Otherwise return false.
      return true; // <-- This is so that the class will compile. Remove it when writing your code.
   }
}