package com.cxmax.leetcode.node;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 8/26/21.
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 * 这道题注意也是两个指针来做，主要是指针偏移，当前指针 和 前一个指针， 如果当前指针满足条件，就移除。 然后一步一步往前移动
 *
 */
public class RemoveElements_Leet203 {

    /**
     * 不添加虚拟节点的方式
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        // 第一步，先确定头节点
        while(head != null && head.val == val) {
            head = head.next;
        }

        // 这里要确定一次，head是否为null
        if (head == null || head.next == null) {
            return head;
        }

        // 这里开始做移除元素的操作
        ListNode prev = head;
        ListNode cur = head.next;
        while(prev != null && cur != null) {
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
