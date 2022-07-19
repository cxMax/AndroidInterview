package com.cxmax.selftest.hash;

import com.cxmax.leetcode.hash.LRUCache_Leet146;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 146. LRU 缓存
 *
 * https://leetcode-cn.com/problems/lru-cache/
 *
 * 主要就是要提供，put和get方法
 *
 * 思路 ：
 * 1. 构造双链表数据结构
 * 2. 构造双链表的辅助类，添加头节点，删除队尾节点，
 *
 * Created by caixi on 2022/2/15.
 */
public class LRUCache {

    // 集合
    private Map<Integer, Node> map;
    // 双链表辅助操作
    private DoubleList cache;
    // 容量
    private int capacity;

    public LRUCache(int _capacity) {
        map = new HashMap<>();
        cache = new DoubleList();
        capacity = _capacity;
    }

    public void put(int key, int val) {
        Node node = new Node(key, val);
        // 先判断容器是否有值
        if (map.containsKey(key)) {
            // 先移除，在添加到队列头部
            cache.remove(node);
            cache.addFirst(node);
            // 在重新存放到map中，因为node是双链表，前后节点都发生改变了，所以要重新put一次
            map.put(key, node);
        } else {
            // 不包含在容器中
            // 先判断是否到达临界节点
            if (cache.size == capacity) {
                // 移除队尾节点
                Node lastNode = cache.removeTail();
                // map也移除记录
                map.remove(lastNode.key);
            }
            cache.addFirst(node);
            map.put(key, node);
        }
    }

    /**
     * 要做这两步操作
     *
     * 1. 获取节点
     * 2. 更新位置
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        int value = node.value;
        put(key,value);
        return value;

    }

    public static class Node {
        int key;
        int value;
        Node prev;
        Node next;

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
        // 虚拟节点，头节点
        Node head = new Node(0, 0);
        // 虚拟节点，尾节点
        Node tail = new Node(0, 0);
        int size = 0;

        public DoubleList() {
            head.next = tail;
            tail.prev = head;
            size = 0;
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
            // 记得长度++
            size++;
        }


        /**
         * 移除最后一个元素
         */
        public Node removeTail() {
            // 拿到真正的最后一个元素，并非虚拟节点
            Node lastNode = tail.prev;
            remove(lastNode);
            return lastNode;
        }

        /**
         * 移除元素
         * @param node
         */
        public void remove(Node node) {
            // 前节点
            Node prev = node.prev;
            prev.next = node.next;

            // 后节点
            Node next = node.next;
            // todo 这里要非null判断吗？
            next.prev = node.prev;

            size--;
        }

    }
}
