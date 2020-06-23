
/**
 *
 * @author dangvu.vn
 */
public class AVLTree {

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;
        int depth;

        TreeNode(int val) {
            this.val = val;
            depth = 1;
        }
    }

    TreeNode root;

    int height = 1;

    public void insert(int val) {
        root = findAndInsert(root, val);
    }
    
    public void delete(int val) {
        root = findAndDelete(root, val);
    }
    
    private TreeNode findAndInsert(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = findAndInsert(root.left, val);
        } else if (root.val < val) {
            root.right = findAndInsert(root.right, val);
        } else {
            return root;
        }

        root.depth = getDepth(root);
        int bf = getBalanceFactor(root);

        if (Math.abs(bf) > 1) {
            root = balanceTree(root, bf, val);
        }

        return root;
    }

    private TreeNode balanceTree(TreeNode root, int bf) {
        if (bf < -1 && getBalanceFactor(root.left) > 0) { // R-L
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        
        if (bf < -1 && getBalanceFactor(root.right) <= 0) {// R-R
            return leftRotate(root);
        }
        
        if (bf > 1 && getBalanceFactor(root.left) >= 0) {
            return rightRotate(root);
        }
        
        if (bf > 1 && getBalanceFactor(root.right) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        return root;
    }
    
    private TreeNode balanceTree(TreeNode root, int bf, int val) {
        if (bf < -1 && val < root.right.val) { // R-L
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        if (bf < -1 && val > root.right.val) { // R-R
            return leftRotate(root);
        }

        if (bf > 1 && val < root.left.val) { // L-L
            return rightRotate(root);
        }

        if (bf > 1 && val > root.left.val) { // L-R
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        return root;
    }

    private int getDepth(TreeNode node) {
        int maxDepth = Math.max(
                node.left == null
                        ? 0 : node.left.depth,
                node.right == null
                        ? 0 : node.right.depth
        ) + 1;

        if (maxDepth > height) {
            height = maxDepth;
        }
        return maxDepth;
    }

    private TreeNode leftRotate(TreeNode node) {
        TreeNode newHead = node.right;

        TreeNode ordiginalNode = node;
        ordiginalNode.right = null;

        TreeNode newHeadCurrentLeft = newHead.left;

        ordiginalNode.right = newHeadCurrentLeft;
        newHead.left = ordiginalNode;

        ordiginalNode.depth = getDepth(ordiginalNode);
        newHead.depth = getDepth(newHead);

        return newHead;
    }

    private TreeNode rightRotate(TreeNode root) {
        TreeNode ordiginalRoot = root;
        TreeNode newRoot = root.left;

        TreeNode newRootRight = newRoot.right;

        ordiginalRoot.left = newRootRight;
        newRoot.right = ordiginalRoot;

        ordiginalRoot.depth = getDepth(ordiginalRoot);
        newRoot.depth = getDepth(newRoot);

        return newRoot;
    }


    private TreeNode findAndDelete(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val > val) {
            root.left = findAndDelete(root.left, val);
        } else if (root.val < val) {
            root.right = findAndDelete(root.right, val);
        } else {
            if (root.left == null || root.right == null) {

                if (root.left == null) {
                    root = root.right;
                } else {
                    root = root.left;
                }

            } else {
                TreeNode rootLeft = root.left;

                int gs = 0;
                while (rootLeft != null) {
                    gs = rootLeft.val;
                    rootLeft = rootLeft.right;
                }

                bstDelete(root.left, gs);
                root.val = gs;
            }
        }
        if (root != null) {
            root.depth = getDepth(root);
            int bf = getBalanceFactor(root);

            if (Math.abs(bf) > 1) {
                root = balanceTree(root, bf);
            }
        }
        return root;
    }

    private TreeNode bstDelete(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val) {
            root.left = bstDelete(root.left, val);
        } else if (root.val > val) {
            root.right = bstDelete(root.right, val);
        } else {
            if (root.left == null) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return root;

    }

    private int getBalanceFactor(TreeNode node) {
        int leftDepth = (node.left == null) ? 0 : node.left.depth;
        int rightDepth = (node.right == null) ? 0 : node.right.depth;

        return leftDepth - rightDepth;
    }

//    int getDisplayArrayLength() {
//        int length = 0;
//        for (int i = 0; i < height; i += 1) {
//            length += Math.pow(2, i);
//        }
//        return length;
//    }

//    int[] results;
//
//    int[] display() {
//        results = new int[getDisplayArrayLength()];
//        fill(root, 0);
//        return results;
//    }
//
//    void fill(TreeNode root, int index) {
//        if (root == null) {
//            return;
//        }
//
//        results[index] = root.val;
//        fill(root.left, index * 2 + 1);
//        fill(root.right, index * 2 + 2);
//    }
//
//    static void print(int arr[]) {
//        System.out.print("[");
//        for (int a : arr) {
//            System.out.print(a + " ");
//        }
//        System.out.println("]");
//    }
//
//    public static void main(String[] args) {
//        AVLTree mTree = new AVLTree();
//        print(mTree.display());
//        mTree.insert(6);
//        print(mTree.display());
//        mTree.insert(4);
//        print(mTree.display());
//        mTree.insert(15);
//        print(mTree.display());
//        mTree.insert(8);
//        print(mTree.display());
//        mTree.insert(7);
//        print(mTree.display());
//        mTree.insert(9);
//        print(mTree.display());
//        mTree.insert(10);
//        print(mTree.display());
//        mTree.delete(7);
//        print(mTree.display());
//    }
}
