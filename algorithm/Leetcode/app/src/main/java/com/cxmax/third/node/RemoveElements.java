package com.cxmax.third.node;

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
 * Created by caixi on 2022/7/21.
 */
public class RemoveElements {


    /**
     * 移除链表元素
     *
     * 1. 需要先判断头节点是否就相等了
     * 2. 再判断异常情况，就是头节点，是否移动到最后了
     * 3. 双指针， 跟数组一样， 移动到了，就指向下一个节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val) {
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
