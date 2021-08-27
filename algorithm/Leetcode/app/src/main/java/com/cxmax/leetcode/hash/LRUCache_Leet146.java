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
        Node head = new Node(0, 0); //这个理解为第一个节点的前置节点。
        Node tail = new Node(0, 0); //这个理解为最后一个节点的后置节点
        int size;

        public DoubleList() {
            size = 0;
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 添加到队头元素
         * @param node
         */
        public void addFirst(Node node) {
            Node headNext = head.next; // 原来的第一个节点
            head.next = node;
            headNext.prev = node;

            node.prev = head;
            node.next = headNext;
            size++;

        }

        /**
         * 移除最后一个元素
         */
        public Node removeTail() {
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
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node); //这里因为node变了，所以要重新设置一次
        } else {
            // 如果不在缓存中, 要判断队列是否溢出，溢出的话要移除最后一个
            if (cache.size() == capacity) {
                Node lastNode = cache.removeTail();
                map.remove(lastNode.key);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public int get(int key) {
        //先要判断缓存中，有没有这个值
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).value;
        // 更新位置
        put(key, val);
        return val;
    }
}
