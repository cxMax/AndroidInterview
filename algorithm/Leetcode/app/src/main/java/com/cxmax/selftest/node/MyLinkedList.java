package com.cxmax.selftest.node;

import com.cxmax.leetcode.node.ListNode;

/**
 *
 * https://leetcode-cn.com/problems/design-linked-list/
 *
 * 707. 设计链表
 *
 * Created by caixi on 2022/1/19.
 */
public class MyLinkedList {

    private ListNode head;
    private int size;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        // 先判断越界问题
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode cur = head;
        for (int i = 0; i <= size; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index >= size) {
            return;
        }
        // 还是先判断角标越界异常
        if (index < 0) {
            index = 0;
        }
        // 添加， 那么size就得++
        size++;
        // 在指定位置添加角标
        ListNode prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 开始进行插入
        ListNode toAdd = new ListNode(val);
        toAdd.next = prev.next;
        prev.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        // 还是先判断越界问题
        if (index < 0 || index >= size) {
            return;
        }
        // 不越界，先更新链表长度
        size--;
        // 移动到他前一个节点
        ListNode prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        ListNode next = prev.next;
        if (next != null) {
            prev.next = next.next;
        }

    }

}
