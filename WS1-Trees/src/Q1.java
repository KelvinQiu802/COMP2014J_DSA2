import dsa.iface.IIterator;
import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;

import java.util.ArrayList;

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
        Q3: What is the depth of the position that stores “L”?
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
        ArrayList<Character> gAncestors = getAncestorsOfPosition(tree, gPos);
        System.out.printf("Q5: %s\n", gAncestors);

        /*
        Q6: List the elements stored in any descendants of the position that stores B.
         */
        IPosition<Character> bPos = find(tree, tree.root(), 'B');
        ArrayList<Character> bDescendants = getDescendantsOfPosition(tree, bPos);
        System.out.printf("Q6: %s\n", bDescendants);

        /*
        Q7: List the elements that are stored at leaf (external) positions.
         */
        ArrayList<Character> allLeaves = getAllLeaves(tree);
        System.out.printf("Q7: %s\n", allLeaves);
    }
}
