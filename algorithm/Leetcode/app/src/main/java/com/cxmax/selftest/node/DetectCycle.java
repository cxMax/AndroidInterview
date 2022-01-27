package com.cxmax.selftest.node;

import com.cxmax.leetcode.node.ListNode;

/**
 *
 * 142. 环形链表 II
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 *
 * Created by caixi on 2022/1/24.
 */
public class DetectCycle {

    /**
     * 固定算法， 双指针大法，
     *
     * 1. 快指针走两步，慢指针走一步
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 第一次开始走
        while(true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            // 快指针走两步
            // 慢指针走一步
            fast = fast.next.next;
            slow = slow.next;
            // 第一次相遇， 就跳出循环
            if (slow == fast) {
                break;
            }
        }
        // 重置快指针
        fast = head;
        // 第二次开始走
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        // 返回相交的节点
        return fast;
    }

}
