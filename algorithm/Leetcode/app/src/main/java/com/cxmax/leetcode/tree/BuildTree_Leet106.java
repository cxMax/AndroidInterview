package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     106. 从中序与后序遍历序列构造二叉树
 *     根据一棵树的中序遍历与后序遍历构造二叉树。
 * </p>
 * Created by caixi on 9/6/21.
 */
public class BuildTree_Leet106 {

    /**
     * 按照步骤写两次， 感觉也是模板公式
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return traversal(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    // 这个必须用递归来做
    private TreeNode traversal(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        // 先判断中止条件
        if (inRight - inLeft < 1) {
            return null;
        }
        // 如果只有一个元素
        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }
        // 找root， 就是后序的最后一个元素
        int rootVal = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootVal);
        // 在中序，该节点的位置，需要找出来
        int rootIndex = 0;
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }
        // 根据rootIndex划分左右子树
        // 左边， 就是中序、后序，左左
        root.left = traversal(inorder,inLeft, rootIndex, postorder, postLeft, postLeft + (rootIndex - inLeft));
        root.right = traversal(inorder, rootIndex + 1, inRight, postorder, postLeft + (rootIndex - inLeft), postRight - 1);
        return root;
    }

}
