package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 9/6/21.
 */
public class BuildTree_Leet105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return traversal(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 前序遍历第一个节点， 就是根节点
     * @param preorder
     * @param preLeft
     * @param preRight
     * @param inorder
     * @param inLeft
     * @param inRight
     * @return
     */
    public TreeNode traversal(int[] preorder, int preLeft, int preRight,
                           int[] inorder, int inLeft, int inRight) {
        // 中止判断
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        // idx 为根据根节点的值来找中序遍历的下标
        int idx = inLeft;
        // val 为前序遍历第一个的值，也即是根节点的值
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        // 在中序遍历中，找出index
        for (int i = inLeft; i <= inRight ; i++) {
            if (inorder[i] == rootVal) {
                idx = i;
                break;
            }
        }

        // 根据 idx 来递归找左右子树
        root.left = traversal(preorder, preLeft + 1, preLeft + (idx - inLeft),
                inorder, inLeft, idx - 1);
        root.right = traversal(preorder, preLeft + (idx - inLeft) + 1, preRight,
                inorder, idx + 1, inRight);
        return root;
    }
}
