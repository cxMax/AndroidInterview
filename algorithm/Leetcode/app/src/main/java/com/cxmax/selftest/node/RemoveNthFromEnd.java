package com.cxmax.selftest.node;

import com.cxmax.leetcode.node.ListNode;

/**
 *
 * 19. 删除链表的倒数第 N 个结点
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 输入：head = [1,2,3,4,5], n = 2
 *
 * 输出：[1,2,3,5]
 *
 * Created by caixi on 2022/1/20.
 */
public class RemoveNthFromEnd {

    /**
     *
     * 这道题的解法，也是双指针，
     *
     * 1. 构造虚拟节点
     * 2. 快指针先走n+1步，
     * 3. 慢指针和快指针同时走，直到快指针为null
     * 4. 这个时候删除慢指针
     *
     * 固定算法，别问为什么，是，就画图
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        // 虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 快、慢指针
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 开始删除
        slow.next = slow.next.next;
        return dummy.next;
    }

}
