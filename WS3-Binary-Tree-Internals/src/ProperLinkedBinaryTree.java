import dsa.iface.IPosition;
import dsa.impl.AbstractBinaryTree;

public class ProperLinkedBinaryTree<T> extends AbstractBinaryTree<T> {

	/**
	 * Constructor - create an empty tree
	 */
	public ProperLinkedBinaryTree() {
		// Part 1
	}

	/**
	 * Expand an external node - Store a value in the external node - Create two
	 * external nodes as children, making {@code n} an internal node
	 * 
	 * @param p
	 *            The position (node) to expand. An exception will be thrown if it is not
	 *            external.
	 * @param e
	 *            The element to be stored in position {@code p}.
	 */
	public void expandExternal(IPosition<T> p, T e) {
		// Part 2
	}

	/**
	 * Remove a node from the tree
	 * 
	 * @param p
	 *            The position (node )to be removed. An exception will be thrown if it cannot
	 *            be removed (i.e. if it has two internal children).
	 * @return The element that was stored in the removed position.
	 */
	public T remove(IPosition<T> p) {
		// Part 3
		
		return null; // <-- this is just so the class compiles: remove it from your code
	}
}
