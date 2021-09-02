package com.cxmax.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @describe :
 * @usage :
 * <p>
 *     429. N 叉树的层序遍历
 * </p>
 * Created by caixi on 9/2/21.
 */
public class LevelOrderN_Leet429 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    List<List<Integer>> result = new ArrayList<>();
    /**
     * 还是用bfs来做
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        bfs(root);
        return result;
    }


    public void bfs(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            List<Integer> item = new ArrayList<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node tmp = queue.poll();
                item.add(tmp.val);
                if (tmp.children != null && tmp.children.size() > 0) {
                    for (Node child : tmp.children) {
                        queue.offer(child);
                    }
                }
            }
            result.add(item);
        }
    }
}
