package com.cxmax.third.node;

/**
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 160. 相交链表
 *
 * Created by caixi on 2022/7/22.
 */
public class GetIntersectionNode {

    /**
     * 这里还是需要构造两个指针
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            // todo caixi 2022-7-22 这里写错了，是什么，就是条件， 2点，第一点，进位的时候要判断，当前节点是否为null，而不是下一个节点
            // todo caixi 2022-7-22  第二点，一旦一个链表到头，就跟另一个链表接上
            a = a != null ? a.next : headB;
            b = b != null ? b.next : headA;
        }
        return a;

    }

}
