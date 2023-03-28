public class ProperTreeTest {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    public static void test1() {
        System.out.println("========== TEST 1 ==========");
        ProperLinkedBinaryTree<String> tree = createTestTree();
        System.out.println(tree);
        tree.remove(tree.root());
        System.out.println(tree);
    }

    public static void test2() {
        System.out.println("========== TEST 2 ==========");
        ProperLinkedBinaryTree<String> tree = createTestTree();
        System.out.println(tree);
        try {
            tree.remove(tree.right(tree.root()));
            System.out.println(tree);
        } catch (RuntimeException e) {
            System.out.println("Expected Result! No updated tree printed.");
        }
    }

    public static void test3() {
        System.out.println("========== TEST 3 ==========");
        ProperLinkedBinaryTree<String> tree = createTestTree();
        System.out.println(tree);
        tree.remove(tree.left(tree.right(tree.root())));
        System.out.println(tree);

    }

    public static ProperLinkedBinaryTree<String> createTestTree() {
        ProperLinkedBinaryTree<String> tree = new ProperLinkedBinaryTree<String>();
        tree.expandExternal(tree.root(), "A");
        tree.expandExternal(tree.right(tree.root()), "B");
        tree.expandExternal(tree.left(tree.right(tree.root())), "C");
        tree.expandExternal(tree.right(tree.right(tree.root())), "D");
        return tree;
    }
}
