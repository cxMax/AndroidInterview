package com.cxmax.third.node;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/design-linked-list/
 * <p>
 * 707. 设计链表
 * <p>
 * Created by caixi on 2022/7/21.
 */
public class MyLinkedList {

    private ListNode head;
    private int size;

    /**
     * 其实这道题，我会联想到让你手写LruCache
     *
     * 这道题，就是考察单链表的添加和删除
     * 每一个数字就是一个node
     * 1. 添加元素该怎么写？ 增加一个ToAddNode，
     * 2. 删除元素该怎么写？ 指向下一个node
     * 3. 这道题，因为是手搓一个数据结构，因此，需要构造一个头节点和一个size记录大小
     * 4. 注意异常判断
     *
     *
     */
    public MyLinkedList() {
        head = new ListNode(0);
        size = 0;
    }

    public int get(int index) {
        // todo caixi 2022-7-21 这道题是理解透了， 出问题出现在异常判断，
        // todo caixi 2022-7-21 第一个异常判断，是这里，get的时候，不能等于size大小；还有个就是小于0也不行
        if (index >= size || index < 0) {
            return -1;
        }
        ListNode prev = head;
        // 这里都是返回的前一位
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 这个才是当前位
        return prev.next.val;

    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);

    }

    public void addAtIndex(int index, int val) {
        // 异常判断
        // todo caixi 2022-7-21 第二个做错了，也是不能超过size
        if (index > size) {
            return;
        }
        // todo caixi 2022-7-21 如果是小于0，要置为0
        if (index < 0) {
            index = 0;
        }
        size++;
        // 开始添加，在指定位置的元素节点
        ListNode prev = head;
        // 先移动到指定位置的前一位，在添加
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 这里构造节点，添加进去
        ListNode toAdd = new ListNode(val);
        toAdd.next = prev.next;
        prev.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        // 异常判断
        // todo caixi 2022-7-21 第三个做错了也是，删除，不能是size和小于0的index
        if (index >= size || index < 0) {
            return;
        }
        size--;
        // 这里写移除
        ListNode prev = head;
        // 这里是前一位
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 当前位置
        ListNode cur = prev.next;
        if (cur != null) {
            prev.next = cur.next;
        }
    }

}
