package com.cxmax.third.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * 429. N 叉树的层序遍历
 *
 * 和二叉树一样， 只是是node节点，左右孩子变成了list
 *
 * Created by caixi on 2022/7/31.
 */
public class LevelOrderN {
    class Node {
        int val;
        List<Node> children;

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }

        public Node() {
        }
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int len = queue.size();
            // 有几个节点
            for (int i = 0; i < len; i++) {
                Node tmp = queue.poll();
                list.add(tmp.val);
                if (tmp.children != null && tmp.children.size() > 0) {
                    for (Node node : tmp.children) {
                        queue.offer(node);
                    }
                }
            }
            result.add(list);
        }
        return result;

    }
}
