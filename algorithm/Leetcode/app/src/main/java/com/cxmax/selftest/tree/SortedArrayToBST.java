package com.cxmax.selftest.tree;

/**
 * 108. 将有序数组转换为二叉搜索树
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Created by caixi on 2022/6/1.
 */
public class SortedArrayToBST {

    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0,  nums.length - 1);
    }

    /**
     * 感觉和二分查找写法 很类似
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode traversal(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = traversal(nums, left, mid - 1);
        root.right = traversal(nums, mid + 1, right);
        return root;
    }

}
