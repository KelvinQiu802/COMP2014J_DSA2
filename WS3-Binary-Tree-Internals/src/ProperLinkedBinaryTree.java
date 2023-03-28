import dsa.iface.IPosition;
import dsa.impl.AbstractBinaryTree;

public class ProperLinkedBinaryTree<T> extends AbstractBinaryTree<T> {

    /**
     * Constructor - create an empty tree
     */
    public ProperLinkedBinaryTree() {
        // Part 1
        root = newPosition(null, null);
    }

    /**
     * Expand an external node - Store a value in the external node - Create two
     * external nodes as children, making {@code n} an internal node
     *
     * @param p The position (node) to expand. An exception will be thrown if it is not
     *          external.
     * @param e The element to be stored in position {@code p}.
     */
    public void expandExternal(IPosition<T> p, T e) {
        // Part 2
        if (isInternal(p)) {
            throw new RuntimeException("Can not expand an internal node.");
        }
        if (p instanceof BTPosition node) {
            node.element = e;
            node.left = newPosition(null, node);
            node.right = newPosition(null, node);
            size += 2;
        }
    }

    /**
     * Remove a node from the tree
     *
     * @param p The position (node )to be removed. An exception will be thrown if it cannot
     *          be removed (i.e. if it has two internal children).
     * @return The element that was stored in the removed position.
     */
    public T remove(IPosition<T> p) {
        // Part 3
        if (!(p instanceof BTPosition node)) {
            throw new RuntimeException("p must be an instance of BTPosition.");
        }
        if (isInternal(node.left) && isInternal(node.right)) {
            throw new RuntimeException("Can not remove node which has two internal children.");
        }
        T removedValue = node.element;
        if (isRoot(node)) {
            BTPosition nextRoot = isExternal(node.right) ? node.left : node.right;
            nextRoot.parent = null;
            node.left = null;
            node.right = null;
            root = nextRoot;
        } else if (isExternal(node.left)) {
            BTPosition parent = node.parent;
            BTPosition right = node.right;
            right.parent = parent;
            if (parent.left == node) {
                parent.left = right;
            } else {
                parent.right = right;
            }
        } else {
            BTPosition parent = node.parent;
            BTPosition left = node.left;
            left.parent = parent;
            if (parent.left == node) {
                parent.left = left;
            } else {
                parent.right = left;
            }
        }
        return removedValue;
    }
}
