package com.cxmax.leetcode.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 160. 相交链表
 * </p>
 * Created by caixi on 8/25/21.
 */
public class GetIntersectionNode_Leet160 {


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        while(a != b) {
            a = (a != null) ? a.next : headB;
            b = (b != null) ? b.next : headA;
        }
        return a;
    }
}
