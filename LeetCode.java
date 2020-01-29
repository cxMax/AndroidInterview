package com.huajiao.guard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2020-01-29.
 */
public class LeetCode {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main() {


    }


    /**
     * 3. 无重复字符的最长子串
     * 标签：滑动窗口
     * 暴力解法时间复杂度较高，会达到 O(n^2)，故而采取滑动窗口的方法降低时间复杂度
     * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
     * 我们定义不重复子串的开始位置为 start，结束位置为 end
     * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
     * 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
     * 时间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i) + 1));
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * LeetCode 总结 - 搞定 Binary Tree 面试题
     * https://www.jianshu.com/p/5fbd07d557a3
     */
    public static class BinaryTree {
        /**
         * 515. 在每个树行中找最大值
         * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
         *
         * @param root
         * @return
         */
        public static List<Integer> LargestValuesBinaryTree(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            as(0, result, root);
            return result;
        }

        public static void as(int level, List<Integer> list, TreeNode node) {
            if (level == list.size()) {
                list.add(level, node.val);
            }
            list.set(level, Math.max(list.get(level), node.val));
            if (node.left != null) {
                as(level+1, list, node.left);
            }
            if (node.right != null) {
                as(level+1, list, node.right);
            }
        }

        /**
         * 144. 二叉树的前序遍历
         * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
         *
         * 前序遍历就是 - 中 - 左 - 右
         *
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper(root, result);
            return result;

        }

        private void helper(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            result.add(root.val);
            helper(root.left, result);
            helper(root.right, result);
        }


        /**
         * 94. 二叉树的中序遍历
         * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
         *
         *
         * 中序遍历就是  - 左 - 中 - 右
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper1(root, result);
            return result;
        }

        private void helper1(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                helper1(root.left, result);
            }
            result.add(root.val);
            if (root.right != null) {
                helper1(root.right, result);
            }
        }

        /**
         * 145. 二叉树的后序遍历
         * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
         *
         * 中序遍历就是  - 右 - 中 - 左
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper2(root, result);
            return result;
        }

        private void helper2(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                helper1(root.left, result);
            }
            if (root.right != null) {
                helper1(root.right, result);
            }
            result.add(root.val);
        }
    }


    /**
     * 225. 用队列实现栈
     * https://leetcode-cn.com/problems/implement-stack-using-queues/
     *
     */
    public class StackByQueue {

        private Queue<Integer> inQueue;
        private Queue<Integer> outQueue;

        public StackByQueue() {
            inQueue = new LinkedBlockingQueue<>();
            outQueue = new LinkedBlockingQueue<>();
        }

        public void push(int x) {
            inQueue.add(x);
            while (!outQueue.isEmpty()) {
                inQueue.add(outQueue.poll());
            }
            while (!inQueue.isEmpty()) {
                outQueue.add(inQueue.poll());
            }
        }

        public int pop() {
            return outQueue.poll();
        }

        public int top() {
            return outQueue.peek();
        }

        public boolean empty() {
            return outQueue.isEmpty();
        }
    }



}
