package com.cxmax.selftest.tree;

/**
 * Created by caixi on 2022/5/31.
 */
public class BSTDeleteNode {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 左右节点都不为null，找出右边节点的最小值
            TreeNode minOfRight = findMinNode(root.right);
            root.val = minOfRight.val;
            root.right = deleteNode(root.right, minOfRight.val);

        } else if (root.val < key) { // 这里感觉跟二分查找一样
            root.right = deleteNode(root.right, root.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, root.val);
        }
        return root;
    }

    /**
     * 找到最小节点，也就是左节点拉满
     * @param node
     * @return
     */
    private TreeNode findMinNode(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

}
