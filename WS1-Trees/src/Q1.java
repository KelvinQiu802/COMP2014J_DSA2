import dsa.iface.IIterator;
import dsa.iface.IPosition;
import dsa.iface.ITree;
import dsa.impl.Tree;

public class Q1 {
    public static <T> T getRootValue(ITree<T> t) {
        return t.root().element();
    }

    public static <T> String getChildrenOfRootPosition(ITree<T> t) {
        IIterator<IPosition<T>> it = t.children(t.root());
        StringBuilder result = new StringBuilder();
        while (it.hasNext()) {
            result.append(it.next().element());
            result.append(" ");
        }
        return result.toString();
    }

    public static <T> int getDepthOfValue(ITree<T> t, IPosition<T> p) {
        if (t.parent(p) == null) {
            return 0;
        }
        return 1 + getDepthOfValue(t, t.parent(p));
    }

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
    }
}
