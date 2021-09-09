package com.cxmax.leetcode.tree;

/**
 * @describe :
 * @usage :
 * <p>
 *     108. 将有序数组转换为二叉搜索树
 *     给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * </p>
 * Created by caixi on 9/9/21.
 */
public class SortedArrayToBST_Leet108 {


    /**
     * 这题还是有兴趣， 有序数组 转化为 搜索二叉树
     * 这个题， 我觉得用递归更好理解一些
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return traversal(nums, 0, nums.length -1);
    }

    /**
     * 这个递归的写法， 感觉类似与二分查找
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private TreeNode traversal(int[] nums, int left, int right) {
        if(left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        // 中间的根节点
        TreeNode root = new TreeNode(nums[mid]);
        root.left = traversal(nums, left, mid - 1);
        root.right = traversal(nums, mid + 1, right);
        return root;
    }

}
