package com.cxmax.selftest.node;

import com.cxmax.leetcode.node.ListNode;

/**
 *
 * 203. 移除链表元素
 *
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 *
 * 输出：[1,2,3,4,5]
 *
 * Created by caixi on 2022/1/19.
 */
public class RemoveElements {

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
        while (head != null && head.val == val) {
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
