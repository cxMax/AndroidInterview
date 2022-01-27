package com.cxmax.selftest.node;

import com.cxmax.leetcode.node.ListNode;

/**
 *
 * 24. 两两交换链表中的节点
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * 输入：head = [1,2,3,4]
 *
 * 输出：[2,1,4,3]
 *
 * Created by caixi on 2022/1/20.
 */
public class SwapPairs {

    /**
     * 比较技巧的解法， 双指针 + 虚拟节点
     * 在实际做题中，固定的算法
     *
     *
     * 1. 先 2->1, 2134
     * 2. 再 1->3, 2314
     * 3. 在 3->2, 3214
     * 4. 在进位
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // 先构造虚拟节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        // 循环条件，从虚拟节点开始的下一位，下下位不能为null， 就跟上面写的固定算法一致
        while(prev.next != null && prev.next.next != null) {
            // 3
            ListNode tmp = head.next.next;
            // 先2->1
            prev.next = head.next;
            // 在1->3
            head.next.next = head;
            // 在3->2
            head.next = tmp;
            // 进位
            prev = head;
            head = head.next;
        }
        // 返回头节点
        return dummy.next;
    }
}
