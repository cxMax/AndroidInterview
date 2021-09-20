package com.cxmax.leetcode.node;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/26/21.
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 这道题注意也是两个指针来做，主要是指针偏移，当前指针 和 前一个指针， 如果当前指针满足条件，就移除。 然后一步一步往前移动
 */
public class RemoveElements_Leet203 {

    /**
     * 不添加虚拟节点的方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 第一步，先确定头节点
        while (head != null && head.val == val) {
            head = head.next;
        }

        // 这里要确定一次，head是否为null
        if (head == null || head.next == null) {
            return head;
        }

        // 这里开始做移除元素的操作
        ListNode prev = head;
        ListNode cur = head.next;
        while (prev != null && cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    /**
     * 删除一个节点中 包含 val的节点
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {
        // 先判断头节点是否满足，如果满足的话，需要往后移动
        while(head != null && head.val == val) {
            head = head.next;
        }

        // 这里做一个非空判断，头节点是否为null
        if (head == null || head.next == null) {
            return head;
        }

        // 这里再去遍历移除节点
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    public ListNode removeElement2(ListNode head, int val) {
        if (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while(cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
