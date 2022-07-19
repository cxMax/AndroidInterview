package com.cxmax.selftest.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 501. 二叉搜索树中的众数
 * 如果是树的话，用map来做， treemap key有序
 * https://leetcode.cn/problems/find-mode-in-binary-search-tree/solution/
 * <p>
 * Created by caixi on 2022/5/27.
 */
public class FindMode {

    /**
     * 1. 二叉搜索树的遍历，就肯定是中序遍历，因为是有序的（这个是重点）
     * 2. 出现频率最高的，那就是map
     *
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 迭代法，统一的代码模版
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev = null;
        // 这里要统计count和maxcount
        int count = 0;
        int maxCount = 0;
        List<Integer> result = new ArrayList<>();
        // 其实这些都是固定写法
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // 左节点
                stack.push(cur);
                cur = cur.left;
            } else {
                // 右节点
                cur = stack.pop();
                /*
                 * 中间开始写逻辑，其他都是固定模板
                 *
                 * */
                if (prev == null) {
                    count = 1;
                } else if (prev.val == cur.val) {
                    count++;
                } else {
                    count = 1;
                }
                // 如果是一个频次
                if (count == maxCount) {
                    result.add(cur.val);
                }

                if (count > maxCount) {
                    maxCount = count;
                    result.clear();
                    result.add(cur.val);
                }

                // prev 为根节点， cur为右节点
                prev = cur;
                cur = cur.right;
            }
        }
        // 输出结果
        int[] ret = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }
}
