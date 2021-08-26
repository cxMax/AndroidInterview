package com.android.test1.hash;

import com.android.test1.node.RemoveNthFromEnd_Leet19;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe :
 * @usage :
 * <p>
 *     手写实现LRUCache，
 * </p>
 * Created by caixi on 8/26/21.
 */
public class LRUCache_Leet146 {

    private static class ListNode {
        int key;
        int value;
        ListNode prev;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    private class DoubleList {
        ListNode head = new ListNode(0, 0);
        ListNode tail = new ListNode(0, 0);
        int size;

        private DoubleList() {
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        private void addFirst(ListNode n) {
            ListNode headNext = head.next;
            head.next = n;
            headNext.prev = n;
            n.prev = head;
            n.next = headNext;
            size++;
        }

        private void remove(ListNode n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            size--;
        }

        private ListNode removeLast() {
            ListNode last = tail.prev;
            remove(last);
            return last;
        }

        private int size() {
            return size;
        }

    }

    private Map<Integer, ListNode> map;
    private DoubleList cache;
    private int capacity;

    public LRUCache_Leet146(int capacity) {
        this.map = new HashMap<>(capacity);
        this.cache = new DoubleList();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key).value;
        put(key, val);
        return val;

    }

    public void put(int key, int value) {
        ListNode node = new ListNode(key, value);
        // 如果在缓存中存在， 就先移除，并且把当前位置插入表头
        if (map.containsKey(key)) {
            cache.remove(map.get(key));
            cache.addFirst(node);
            map.put(key, node);
        } else {
            if (cache.size() == capacity) {
                ListNode last = cache.removeLast();
                map.remove(last.key);
            }
            cache.addFirst(node);
            map.put(key,node);
        }
    }

}

