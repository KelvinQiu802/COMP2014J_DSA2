import dsa.iface.IIterator;
import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;

import java.util.ArrayList;
import java.util.List;

public class Q1 {
    // ---------------Q1--------------------
    public static <T> T getRootValue(ITree<T> t) {
        return t.root().element();
    }

    // ---------------Q2--------------------
    public static <T> String getChildrenOfRootPosition(ITree<T> t) {
        IIterator<IPosition<T>> it = t.children(t.root());
        StringBuilder result = new StringBuilder();
        while (it.hasNext()) {
            result.append(it.next().element());
            result.append(" ");
        }
        return result.toString();
    }

    // ---------------Q3--------------------
    public static <T> int getDepthOfValue(ITree<T> t, IPosition<T> p) {
        if (t.parent(p) == null) {
            return 0;
        }
        return 1 + getDepthOfValue(t, t.parent(p));
    }

    // ---------------Q4--------------------
    public static <T> int getHeight(ITree<T> t, IPosition<T> p) {
        if (t.isExternal(p)) {
            return 0;
        }
        int highest = 0;
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            int childHeight = getHeight(t, it.next());
            highest = Math.max(highest, childHeight);
        }
        return highest + 1;
    }

    // ---------------Q5--------------------
    public static <T> ArrayList<T> getAncestorsOfPosition(ITree<T> t, IPosition<T> p) {
        ArrayList<T> result = new ArrayList<>();
        return getAncestorsOfPosition(t, p, result);
    }

    private static <T> ArrayList<T> getAncestorsOfPosition(ITree<T> t, IPosition<T> p, ArrayList<T> lst) {
        if (t.parent(p) == null) {
            return lst;
        }
        lst.add(t.parent(p).element());
        return getAncestorsOfPosition(t, t.parent(p), lst);
    }

    // ---------------Q6--------------------
    public static <T> ArrayList<T> getDescendantsOfPosition(ITree<T> t, IPosition<T> p) {
        ArrayList<T> result = new ArrayList<>();
        getDescendants(t, p, result);
        return result;
    }

    private static <T> void getDescendants(ITree<T> t, IPosition<T> p, ArrayList<T> lst) {
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            IPosition<T> nextPos = it.next();
            lst.add(nextPos.element());
            getDescendants(t, nextPos, lst);
        }
    }

    // ---------------Q7--------------------
    public static <T> ArrayList<T> getAllLeaves(ITree<T> t) {
        ArrayList<T> result = new ArrayList<>();
        addAllLeavesToLst(t, t.root(), result);
        return result;
    }

    private static <T> void addAllLeavesToLst(ITree<T> t, IPosition<T> p, ArrayList<T> lst) {
        if (t.isExternal(p)) {
            lst.add(p.element());
        }
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            addAllLeavesToLst(t, it.next(), lst);
        }
    }

    // ---------------Q8--------------------
    //Return true iff position p is the parent of q, otherwise false.
    public static <T> boolean isEdge(ITree<T> t, IPosition<T> p, IPosition<T> q) {
        return t.parent(q) == p;
    }

    // ---------------Q9--------------------
    public static <T> ArrayList<T> getPath(ITree<T> t, IPosition<T> start, IPosition<T> end) {
        ArrayList<T> result = new ArrayList<>();
        addPathToLst(t, start, end, result);
        return result;
    }

    private static <T> void addPathToLst(ITree<T> t, IPosition<T> start, IPosition<T> end, ArrayList<T> lst) {
        if (t.parent(end) == start) {
            lst.add(0, end.element());
            return;
        }
        lst.add(0, end.element());
        addPathToLst(t, start, t.parent(end), lst);
    }

    // ---------------Q10--------------------
    public static <T> String getTreeDiagram(ITree<T> t) {
        StringBuilder diagram = new StringBuilder();
        buildTreeString(t, t.root(), "", "", diagram);
        return diagram.toString();
    }

    // This solution is inspired by:
    // https://stackoverflow.com/a/8948691
    public static <T> void buildTreeString(ITree<T> t, IPosition<T> p, String prefix, String childrenPrefix, StringBuilder diagram) {
        diagram.append(prefix);
        diagram.append(p.element());
        diagram.append("\n");
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            IPosition<T> nextPos = it.next();
            if (it.hasNext()) {
                buildTreeString(t, nextPos, childrenPrefix + "????????? ", childrenPrefix + "???   ", diagram);
            } else {
                buildTreeString(t, nextPos, childrenPrefix + "????????? ", childrenPrefix + "    ", diagram);
            }
        }
    }

    // ---------------Shared--------------------
    public static <T> IPosition<T> find(ITree<T> t, IPosition<T> p, T k) {
        if (p.element().equals(k)) {
            return p;
        }
        IIterator<IPosition<T>> it = t.children(p);
        while (it.hasNext()) {
            IPosition<T> childPosition = find(t, it.next(), k);
            if (childPosition != null) {
                return childPosition;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ITree<Character> tree = Tree.createTreeA();

        /*
        Q1: What is stored at the root of the tree?
         */
        Character rootValue = getRootValue(tree);
        System.out.println("Q1.a: " + rootValue);

        /*
        Q2: What are stored in the children of the root position?
         */
        String childrenOfRootPosition = getChildrenOfRootPosition(tree);
        System.out.println("Q1.b: " + childrenOfRootPosition);

        /*
        Q3: What is the depth of the position that stores ???L????
         */
        IPosition<Character> lPos = find(tree, tree.root(), 'L');
        System.out.printf("Q3: %d\n", getDepthOfValue(tree, lPos));

        /*
        Q4: What is the height of the tree?
         */
        System.out.printf("Q4: %d\n", getHeight(tree, tree.root()));

        /*
        Q5: List the elements stored in any ancestors of the position that stores G.
         */
        IPosition<Character> gPos = find(tree, tree.root(), 'G');
        List<Character> gAncestors = getAncestorsOfPosition(tree, gPos);
        System.out.printf("Q5: %s\n", gAncestors);

        /*
        Q6: List the elements stored in any descendants of the position that stores B.
         */
        IPosition<Character> bPos = find(tree, tree.root(), 'B');
        List<Character> bDescendants = getDescendantsOfPosition(tree, bPos);
        System.out.printf("Q6: %s\n", bDescendants);

        /*
        Q7: List the elements that are stored at leaf (external) positions.
         */
        List<Character> allLeaves = getAllLeaves(tree);
        System.out.printf("Q7: %s\n", allLeaves);

        /*
        Q8: Is (N,L) an edge? Same as: Is N is the parent of L?
         */
        IPosition<Character> nPos = find(tree, tree.root(), 'N');
        System.out.printf("Q8: %s\n", isEdge(tree, nPos, lPos));

        /*
        Q9: List the elements stored in the positions that are in the path from D to N.
         */
        IPosition<Character> dPos = find(tree, tree.root(), 'D');
        System.out.printf("Q9: %s\n", getPath(tree, dPos, nPos));

        /*
        Q10: Based on the code you have used to explore the tree contents and structure,
             can you draw a diagram to show the structure of the tree?
             This solution is inspired by:
             https://stackoverflow.com/a/8948691
         */
        String treeDiagram = getTreeDiagram(tree);
        System.out.println("Q10: ");
        System.out.println(treeDiagram);
    }
}
