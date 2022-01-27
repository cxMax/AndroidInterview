package com.cxmax.selftest.node;

import com.cxmax.leetcode.node.ListNode;

/**
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 160. 相交链表
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *
 * Created by caixi on 2022/1/24.
 */
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a != b) {
            a = a != null ? a.next : headB;
            b = b != null ? b.next : headA;
        }
        return a;
    }
}
