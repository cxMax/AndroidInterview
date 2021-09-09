package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 9/9/21.
 */
public class DeleteNode_Leet450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 根节点 == key
        if (root.val == key) {
            // 被删除的左节点为null， 返回右节点
            if (root.left == null) {
                return root.right;
            }
            // 被删除的右节点为null， 返回左节点
            if (root.right == null) {
                return root.left;
            }
            // 如果左右节点都不为null， 则找出最小值
            TreeNode minOfRight = findMinNode(root.right);
            root.val = minOfRight.val;
            root.right = deleteNode(root.right, minOfRight.val);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    /**
     * 找到最小节点，也就是左节点拉满
     * @param node
     * @return
     */
    private TreeNode findMinNode(TreeNode node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}
