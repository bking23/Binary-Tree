/*
 * Program: Binary Search Trees
 * 
 * NAME: BENJAMIN KING 
 * 
 * Date: 10/11/19
 * 
 */
import java.util.Scanner;
public class ClientBST {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BinaryTree<Integer> bst = new BinaryTree<Integer>();
		Integer num;
		System.out.print("Enter integers(999 to stop): ");
		num = getInt(input);
		while (num != 999) {
			bst.insert(num);
			num = getInt(input);
		}
		System.out.println("Tree Height: " + bst.treeHeight());
		System.out.print("Enter value to search for: ");
		num = getInt(input);
		if(bst.search(num))
			System.out.println(num + " was found in this tree");
		else
			System.out.println(num + " was NOT found in this tree");
		System.out.print("Inorder traversal: ");
		bst.inOrderTraversal();
		System.out.print("\nPreorder traversal: ");
		bst.preOrderTraversal();
		System.out.print("\nPostorder traversal: ");
		bst.postOrderTraversal();
		System.out.print("\nEnter value to be deleted from tree: ");
		num = input.nextInt();
		bst.delete(num);
		System.out.print("\nInorder traversal after removing " + num + ": ");
		bst.inOrderTraversal();
		System.out.print("\nPreorder traversal after removing " + num + ": ");
		bst.preOrderTraversal();
		System.out.print("\nPostorder traversal after removing " + num + ": ");
		bst.postOrderTraversal();
		System.out.println();
		int a = bst.treeLeavesCount();
		System.out.print("Tree leaves: " + a);
	} 
	public static int getInt(Scanner input) {
		while(!input.hasNextInt()) {
			System.out.print("Not an int try again: ");
			input.next();
		}
		return input.nextInt();
	}
}