package com.cxmax.leetcode.node;

import java.util.List;

/**
 * @describe :
 * @usage :
 * <p>
 * 707. 设计链表
 * 这个主要是通过单链表的形式来解决的。
 * LinkedList是通过双链表的形式来实现的
 *
 * 这里链表的添加 - 在指定位置，先把头节点移动到那个位置，添加就是头节点的下个位置为新增的，新增的下个节点，为头节点的下个节点
 * 链表的删除 - 就是，先移动到指定位置，把链表的下个节点指向下下个节点。
 *
 * 单链表要简单些，双链表操作要麻烦些;
 *
 * 这道题的关键就是移动指针
 * 再注意下非空判断就好
 *
 * </p>
 * Created by caixi on 8/26/21.
 */
public class MyLinkedList_Leet707 {

    ListNode head;
    int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList_Leet707() {
        head = new ListNode(0, null);
        size = 0;
    }


    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode prev = head;
        for (int i = 0; i <= index; i++) {
            prev = prev.next;
        }
        return prev.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            index = size;
        }
        if (index < 0) {
            index = 0;
        }
        size++;
        ListNode prev = head;
        // 先移动到要添加的节点位置
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        ListNode toAdded = new ListNode(val);
        toAdded.next = prev.next;
        prev.next = toAdded;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        //删除节点也是，先移动位置
        size--;
        ListNode prev = head;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        if (prev.next != null) {
            prev.next = prev.next.next;
        }
    }
}
