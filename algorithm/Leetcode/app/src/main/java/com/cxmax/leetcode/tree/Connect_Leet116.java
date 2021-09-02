package com.cxmax.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     116. 填充每个节点的下一个右侧节点指针
 *     给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
 * </p>
 * Created by caixi on 9/2/21.
 */
public class Connect_Leet116 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    /**
     * 这道题我大概领悟了， 核心就是bfs层序遍历， 然后每层节点进行单链表相连
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root != null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            //先要把第一个节点弹出， 所以下次遍历也要从1开始
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            for (int i = 1; i < len; i++) {
                Node next = queue.poll();
                if (next.left != null) {
                    queue.offer(next.left);
                }
                if (next.right != null) {
                    queue.offer(next.right);
                }
                cur.next = next;
                cur = next;
            }
        }
        return root;
    }
}
