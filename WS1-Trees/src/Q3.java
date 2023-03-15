import dsa.iface.IIterator;
import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;
import dsa.impl.SLinkedList;
import dsa.iface.IList;


public class Q3 {
    // --------------------Q1---------------------
    public static <T> IList<T> getInternalValues(ITree<T> t) {
        IList<T> result = new SLinkedList<>();
        getInternalValues(t, t.root(), result);
        return result;
    }

    private static <T> void getInternalValues(ITree<T> t, IPosition<T> p, IList<T> lst) {
        if (t.isInternal(p)) {
            lst.insertLast(p.element());
        }
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            getInternalValues(t, it.next(), lst);
        }
    }

    public static void main(String[] args) {
        ITree<String> tree = Tree.createTreeC();

        /*
        Q1: Which is stored in the root position?
         */
        System.out.println("Q1: " + Q1.getRootValue(tree));

        /*
        Q2: What are stored in the internal positions?
         */
        IList<String> internalValues = getInternalValues(tree);
        System.out.println("Q2: " + Q1.listToString(internalValues));

        /*
        Q3: How many descendants does the position that stores “cs016/” have?
         */
        IPosition<String> aPos = Q1.find(tree, tree.root(), "cs016/");
        System.out.println("Q3: " + Q1.getDescendantsOfPosition(tree, aPos).size());

        /*
        Q4: How many ancestors does the position that stores “cs016/” have?
         */
        System.out.println("Q4: " + Q1.getAncestorsOfPosition(tree, aPos).size());

        /*
        Q5: What are the siblings of the position that stores “homeworks/”?
         */
        IPosition<String> bPos = Q1.find(tree, tree.root(), "homeworks/");
        IList<String> siblings = Q2.getSiblingsOfPos(tree, bPos);
        System.out.println("Q5: " + Q1.listToString(siblings));

        /*
        Q6: Which positions are in the subtree rooted at the position that stores “projects/”?
         */
        IPosition<String> cPos = Q1.find(tree, tree.root(), "projects/");
        IList<String> descendants = Q1.getDescendantsOfPosition(tree, cPos);
        System.out.println("Q6: " + Q1.listToString(descendants));

        /*
        Q7: What is the depth of position that stores “papers/”?
         */
        IPosition<String> dPos = Q1.find(tree, tree.root(), "papers/");
        System.out.println("Q7: " + Q1.getDepthOfValue(tree, dPos));

        /*
        Q8: What is the height of the tree?
         */
        System.out.println("Q8: " + Q1.getHeight(tree, tree.root()));

        /*
        Q9: Based on the code you have used to explore the tree contents and structure,
            can you draw a diagram to show the structure of the tree?
         */
        String treeDiagram = Q1.getTreeDiagram(tree);
        System.out.println("Q9: ");
        System.out.println(treeDiagram);
    }
}
