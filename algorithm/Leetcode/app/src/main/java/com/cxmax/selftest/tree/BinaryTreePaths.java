package com.cxmax.selftest.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * 257. 二叉树的所有路径
 *
 * Created by caixi on 2022/4/13.
 */
public class BinaryTreePaths {

    /**
     * 这里使用递归来做
     * 前序遍历
     * 回溯算法
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 所有的节点
        List<Integer> paths = new ArrayList<>();
        traversal(root, paths, result);
        return result;
    }

    /**
     * 前序遍历，根、左、右
     * @param root 根节点
     * @param paths 所有的节点
     * @param res 路径
     */
    private void traversal(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        // 最后写终止条件， 即、左右节点为空的时候
        if (root.left == null && root.right == null) {
            // 这里应该是遍历结果到result里面
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        // 回溯哦、递归，主要还是前序遍历来做
        if (root.left != null) {
            traversal(root.left, paths, res);
            paths.remove(paths.size() - 1); // 这个就是回溯，表示
        }
        if (root.right != null) {
            traversal(root.right, paths, res);
            paths.remove(paths.size() - 1);
        }
    }

}
