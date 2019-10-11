/*
 * Program: Binary Search Trees
 * 
 * NAME: BENJAMIN KING 
 * 
 * Date: 10/11/19
 * 
 */
import java.util.Stack;
public class BinaryTree<T> implements BinaryTreeADT<T> {
	//public abstract boolean search(T item);
	//public abstract void insert(T item);
	//public abstract void delete(T item);
	protected class BinaryTreeNode<T> {
		public T info;
		public BinaryTreeNode<T> lLink;
		public BinaryTreeNode<T> rLink;
		public BinaryTreeNode() {
			info = null;
			lLink = null;
			rLink = null;
		}
		public BinaryTreeNode(T item) {
			info = item;
			lLink = null;
			rLink = null;
		}
		public BinaryTreeNode(T item, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
			info = item;
			lLink = left;
			rLink = right;
		}
		public Object clone() {
			BinaryTreeNode<T> copy = null;
			try {
				copy = (BinaryTreeNode<T>) super.clone();
			}
			catch (CloneNotSupportedException e) {
				return null;
			}
			return copy;
		}
		public String toString() {
			return info.toString();
		} 	
	}
	protected BinaryTreeNode<T> root;
	public BinaryTree() {
		root = null;
	}
	public Object clone() {
		BinaryTree<T> copy = null;
		try {
			copy = (BinaryTree<T>) super.clone();
		}
		catch (CloneNotSupportedException e) {
			return null;
		}
		if (root != null)
			copy.root = copyTree(root);
		return copy;
	}
	private BinaryTreeNode<T> copyTree(BinaryTreeNode<T> otherTreeRoot) {
		BinaryTreeNode<T> temp;
		if (otherTreeRoot == null)
			temp = null;
		else {
			temp = (BinaryTreeNode<T>) otherTreeRoot.clone();
			temp.lLink = copyTree(otherTreeRoot.lLink);
			temp.rLink = copyTree(otherTreeRoot.rLink);
		}
		return temp;
	}
	public boolean isEmpty() {
		return (root == null);
	}
	public void inOrderTraversal() {
		inOrder(root);
	}
	private void inOrder(BinaryTreeNode<T> t) {
		BinaryTreeNode<T> curr, pre;
		if (t == null)
			return;
		curr = t;
		while(curr != null) {
			if (curr.lLink == null) {
				System.out.print(curr.info + " ");
				curr = curr.rLink;
			}
			else {
				pre = curr.lLink;
				while(pre.rLink != null && pre.rLink != curr) 
					pre = pre.rLink;
				if (pre.rLink == null) {
					pre.rLink = curr;
					curr = curr.lLink;
				}
				else {
					pre.rLink = null;
					System.out.print(curr.info + " ");
					curr = curr.rLink;
				}
			}
		}
	}
	public void preOrderTraversal() {
		preOrder(root);
	}
	private void preOrder(BinaryTreeNode<T> t) {
		if (t == null)
			return;
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		stack.push(t);
		while (stack.empty() == false) {
			BinaryTreeNode<T> node = stack.peek();
			System.out.print(node.info + " ");
			stack.pop();
			if (node.rLink != null)
				stack.push(node.rLink);
			if (node.lLink != null)
				stack.push(node.lLink);
		}
	}
	public void postOrderTraversal() {
		postOrder(root);
	}
	private void postOrder(BinaryTreeNode<T> t) {
		Stack s1 = new Stack<T>();
		Stack s2 = new Stack<T>();

		if (t == null)
			return;
		s1.push(t);
		while (!s1.isEmpty()) {
			BinaryTreeNode<T> temp = (BinaryTreeNode<T>)s1.pop();
			s2.push(temp);
			if (temp.lLink != null)
				s1.push(temp.lLink);
			if (temp.rLink != null)
				s1.push(temp.rLink);
		}
		while(!s2.isEmpty()) {
			BinaryTreeNode<T> temp = (BinaryTreeNode<T>)s2.pop();
			System.out.print(temp.info + " ");
		}

	}
	public int treeHeight() {
		return height(root);
	}
	private int height(BinaryTreeNode<T> t) {
		if (t == null)
			return 0;
		else if (t.lLink == null && t.rLink == null)
			return 0;
		else
			return 1 + Math.max(height(t.lLink), height(t.rLink));
	}
	public void destroyTree() {
		root = null;
	}
	public boolean isBinarySearchTree() {
		return isBST(root);
	}
	public boolean isBST(BinaryTreeNode<T> tree) {
		if (tree == null)
			return true;
		Comparable<T> temp = (Comparable<T>) tree.lLink;
		Comparable<T> temp1 = (Comparable<T>) tree.rLink;

		return temp.compareTo(tree.info) <= 0 && temp1.compareTo(tree.info) >= 0 && isBST(tree.lLink) && isBST(tree.rLink);
	}
	public boolean similarTrees(BinaryTreeNode<T> otherTree) {
		return similar(root, otherTree);
	}
	public boolean similar(BinaryTreeNode<T> tree1, BinaryTreeNode<T> tree2) {
		if (tree1 == null && tree2 == null) 
			return true; 
		if (tree1 != null && tree2 != null)  
			return (tree1.info == tree2.info && similar(tree1.lLink, tree2.lLink) && similar(tree1.rLink, tree2.rLink)); 
		return false; 
	}
	public int treeLeavesCount() {
		return leavesCount(root);
	}
	public int leavesCount(BinaryTreeNode<T> t) {
		if (t == null)
			return 0;
		Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
		int count = 0;
		stack.push(t);
		while(!stack.isEmpty()) {
			BinaryTreeNode temp = stack.peek();
			stack.pop();
			if (temp.lLink != null)
				stack.push(temp.lLink);
			if (temp.rLink != null)
				stack.push(temp.rLink);
			if (temp.lLink == null && temp.rLink == null)
				count++;
		}
		return count;
	}
	public int treeNodeCount() {
		return nodeCount(root);
	}
	public int nodeCount(BinaryTreeNode<T> t) {
		if(t == null)
			return 0;
		return nodeCount(t.lLink) + 1 + nodeCount(t.rLink);
	}
	public boolean search(T item) {
		return recSearch(root, item);
	}
	public boolean recSearch(BinaryTreeNode<T> tree, T item) {
		if (tree == null)
			return false;
		else {
			Comparable<T> temp = (Comparable<T>) tree.info;
			if (temp.compareTo(item) == 0)
				return true;
			if (temp.compareTo(item) > 0)
				return recSearch(tree.lLink, item);
			else
				return recSearch(tree.rLink, item);
		}
	}
	public void insert(T item) {
		root = recInsert(root, item);
	}
	public BinaryTreeNode<T> recInsert(BinaryTreeNode<T> tree, T item) {
		BinaryTreeNode<T> curr = tree;
		BinaryTreeNode<T> node = null;
		if (tree == null) {
			return new BinaryTreeNode<T>(item);
		}
		while (curr != null) {
			node = curr;
			Comparable<T> temp = (Comparable<T>) curr.info;
			if (temp.compareTo(item) > 0) {
				curr = curr.lLink;
			}
			else {
				curr = curr.rLink;
			}
		}
		Comparable<T> temp2 = (Comparable<T>) node.info;
		if (temp2.compareTo(item) > 0) {
			node.lLink = new BinaryTreeNode<T>(item);
		}
		else{
			node.rLink = new BinaryTreeNode<T>(item);
		}
		return tree;
	}
	public void delete(T item) {
		root = recDelete(root, item);
	}
	public BinaryTreeNode<T> recDelete(BinaryTreeNode<T> tree, T item) { 
		while(tree.info != item) {
			if (tree == null)
				return null;
			Comparable<T> temp = (Comparable<T>) tree.info;
			if (temp.compareTo(item) < 0)
				tree = tree.rLink;
			else if (temp.compareTo(item) > 0)
				tree = tree.lLink;
			else if (tree.lLink != null & tree.rLink != null) {
				tree.info = findMin(tree.rLink).info;
				tree.rLink = removeMin(tree.rLink);
			}
		}
		if (root.lLink != null)
			tree = tree.lLink;
		else if (root.rLink != null)
			tree = tree.rLink;
		return tree;			
	}
	protected BinaryTreeNode<T> findMin(BinaryTreeNode<T> tree) {
		if (tree != null)
			while(tree.lLink != null)
				tree = tree.lLink;
		return tree;
	}
	protected BinaryTreeNode<T> removeMin(BinaryTreeNode<T> tree) {
		if (tree == null) {
			System.err.print("Cannot delete from an empty tree.");
			return null;
		}
		else if (tree.lLink != null) {
			tree.lLink = removeMin(tree.lLink);
			return tree;
		}
		else
			return tree.rLink;
	}
}
