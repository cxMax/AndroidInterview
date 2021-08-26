package com.cxmax.leetcode.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 19.删除链表的倒数第 N 个结点
 * 这个主要是不知道链表长度， 需要用到双指针法
 *
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * </p>
 * Created by caixi on 7/16/21.
 */
public class RemoveNthFromEnd_Leet19 {


    /**
     * 双指针来解决这个问题
     *
     * 这里还有个技巧，需要构造一个虚拟指针,指向头节点
     *
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 快指针先走n步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // 双指针一起前进，走向最后
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 这个时候已经走到最后了，移除指针
        slow.next = slow.next.next;
        return dummy.next;
    }


}
