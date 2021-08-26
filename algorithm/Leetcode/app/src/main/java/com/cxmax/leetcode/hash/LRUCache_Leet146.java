package com.cxmax.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/26/21.
 */
public class LRUCache_Leet146 {

    // 构造双链表
    public static class Node {
        int key,value;
        Node prev,next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * LruCache 要做的事， 就是添加到队头、 删除队尾的元素
     * 构造一个链表操作的辅助类
     * 他有头、尾、容量 参数
     */
    public static class DoubleList {
        Node head = new Node(0, 0); //这个是构造的 我理解为虚拟节点
        Node tail = new Node(0, 0); //这个是构造的 我理解为虚拟节点
        int size;

        /**
         * 添加到队头元素
         * @param node
         */
        public void addFirst(Node node) {
            Node headNext = head.next;
            node.prev = head;
            node.next = headNext;
            headNext.prev = node;
            head.next = node;
            size++;

        }

        /**
         * 移除最后一个元素
         * @param node
         */
        public Node removeTail(Node node) {
            Node lastNode = tail.prev;
            remove(lastNode);
            return lastNode;
        }

        public void remove(Node node) {
            Node prev = node.prev;
            prev.next = node.next;
            Node next = node.next;
            next.prev = prev;
            size--;
        }

        public int size() {
            return size;
        }

    }

    private Map<Integer, Node> map;
    private DoubleList cache;
    private int capacity;

    public LRUCache_Leet146(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * 主要就是暴露 put 、 get 方法
     * @param key
     * @param val
     */
    public void put(int key, int val) {
        Node node = new Node(key, val);
        // 想要考虑在不在缓存中
        if (map.containsKey(key)) {
            // 在缓存中，就要做两个事， 就是更新队列，移动到队头

        }

    }
}
