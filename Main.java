public class Main
{
	public static void main(String[] args) {
		AVL tree = new AVL();

    int[] values = {10, 20, 30, 40, 50, 25};
    for (int value : values) {
      tree.insert(value);
    }

    tree.display();
    System.out.println("Tree is balanced: " + tree.isBalanced());

    tree.delete(30);
    System.out.println("\nAfter deleting 30:");
    tree.display();
    System.out.println("Tree is balanced: " + tree.isBalanced());
	}
}
