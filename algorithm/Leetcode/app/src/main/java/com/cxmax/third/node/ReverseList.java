package com.cxmax.third.node;

/**
 * 206. 反转链表
 * <p>
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * Created by caixi on 2022/7/21.
 */
public class ReverseList {

    /**
     * 反转链表，指向顺序发生改变，改变指向顺序，就是后指向前
     * 我感觉有点像，比较排序，
     * 自己尝试手写看看
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        // 前
        ListNode prev = null;
        // 中
        ListNode cur = head;
        // 后
        ListNode tmp = null;
        while(cur != null) {
            tmp = cur.next;
            cur.next = prev;
            // 向前进位， 一直移动到最后
            prev = cur;
            cur = tmp;

        }
        // todo caixi 2022-7-21 这里做错了，要返回反转后的链表，肯定就是prev，移动到最后了，新的表头
        return prev;

    }

}
