import dsa.iface.IIterator;
import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;
import dsa.impl.SLinkedList;
import dsa.iface.IList;


public class Q2 {
    // ---------------------Q3----------------------
    public static <T> IList<T> getChildrenOfPos(ITree<T> t, IPosition<T> p) {
        IList<T> result = new SLinkedList<>();
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            result.insertLast(it.next().element());
        }
        return result;
    }

    // ---------------------Q4----------------------
    public static <T> IList<T> getSiblingsOfPos(ITree<T> t, IPosition<T> p) {
        IList<T> result = new SLinkedList<>();
        IPosition<T> parent = t.parent(p);
        IIterator<IPosition<T>> it = t.children(parent);
        while (it.hasNext()) {
            IPosition<T> next = it.next();
            if (!next.element().equals(p.element())) {
                result.insertLast(next.element());
            }
        }
        return result;
    }

    // ---------------------Q9----------------------
    public static <T> int getSize(ITree<T> t, IPosition<T> p) {
        if (t.isExternal(p)) {
            return 1;
        }
        IIterator<IPosition<T>> it = t.children(p);
        int count = 1;
        while (it.hasNext()) {
            count += getSize(t, it.next());
        }
        return count;
    }

    public static void main(String[] args) {
        ITree<Character> tree = Tree.createTreeB();

        /*
        Q1: What is the height of the tree?
         */
        System.out.println("Q1: " + Q1.getHeight(tree, tree.root()));

        /*
        Q2: What is the depth of the position that stores D?
         */
        IPosition<Character> dPos = Q1.find(tree, tree.root(), 'D');
        System.out.println("Q2: " + Q1.getDepthOfValue(tree, dPos));

        /*
        Q3: List the elements stored in the children of the position that stores B.
         */
        IPosition<Character> bPos = Q1.find(tree, tree.root(), 'B');
        IList<Character> childrenOfB = getChildrenOfPos(tree, bPos);
        System.out.println("Q3: " + Q1.listToString(childrenOfB));

        /*
        Q4: List the elements stored in any siblings of the position that stores D.
         */
        IList<Character> siblingsOfD = getSiblingsOfPos(tree, dPos);
        System.out.println("Q4: " + Q1.listToString(siblingsOfD));

        /*
        Q5: List the elements that are stored at external positions.
         */
        IList<Character> allLeaves = Q1.getAllLeaves(tree);
        System.out.println("Q5: " + Q1.listToString(allLeaves));

        /*
        Q6: What is the parent of the position that stores A?
         */
        IPosition<Character> aPos = Q1.find(tree, tree.root(), 'A');
        System.out.println("Q6: " + tree.parent(aPos));

        /*
        Q7: List the ancestors of the position that stores E.
         */
        IPosition<Character> ePos = Q1.find(tree, tree.root(), 'E');
        IList<Character> ancestorsOfE = Q1.getAncestorsOfPosition(tree, ePos);
        System.out.println("Q7: " + Q1.listToString(ancestorsOfE));

        /*
        Q8: What is the size of the tree?
         */
        System.out.println("Q8: " + getSize(tree, tree.root()));

        /*
        Q9: Based on the code you have used to explore the tree contents and structure,
            can you draw a diagram to show the structure of the tree?
         */
        String treeDiagram = Q1.getTreeDiagram(tree);
        System.out.println("Q9: ");
        System.out.println(treeDiagram);
    }
}
