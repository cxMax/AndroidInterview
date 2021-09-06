package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 *     257. 二叉树的所有路径
 *     给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 * </p>
 * Created by caixi on 9/6/21.
 */
public class BinaryTreePaths_Leet257 {


    /**
     * 使用前序遍历来做
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, result);
        return result;

    }

    /**
     * 前序遍历来做
     * @param root
     * @param paths 节点
     * @param res 路径
     */
    private void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        // 左右节点都为null时中止
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        // 递归，前序遍历
        if (root.left != null) {
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1); // 回溯
        }
        if (root.right != null) {
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1); // 回溯
        }
    }

}
