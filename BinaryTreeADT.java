/*
 * Program: Binary Search Trees
 * 
 * NAME: BENJAMIN KING 
 * 
 * Date: 10/11/19
 * 
 */
public interface BinaryTreeADT<T> extends Cloneable {
    public Object clone(); //Returns a clone of this BT.
    public boolean isEmpty();//Method to determine whether the BT is empty.
    public void inOrderTraversal();//Method to do an inorder traversal of a BT.
    public void preOrderTraversal();//Method to do a preorder traversal of a BT.
    public void postOrderTraversal();//Method to do a postorder traversal of a BT.
    public int treeHeight(); //Method to determine the height of a BT.
    public int treeNodeCount();//Method to find the number of nodes in a binary tree.
    public int treeLeavesCount();//Method to determine the number of leaves in a BT.
    public void destroyTree(); //Method to destroy a BT.
    public boolean search(T item); //Method to determine whether item is in a BT.
    public void insert(T item); //Method to insert item in a BT.
    public void delete(T item);//Method to delete item from a BT.
}