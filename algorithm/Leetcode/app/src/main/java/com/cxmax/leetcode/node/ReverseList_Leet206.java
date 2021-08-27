package com.cxmax.leetcode.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 206. 反转链表
 *
 * 这道题这么来看 - 前、中、后序， 反转
 *
 * </p>
 * Created by caixi on 7/16/21.
 */
public class ReverseList_Leet206 {


    /**
     * 双指针法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur != null) {
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }


    /**
     * 重复做题，加深印象 repeat 1
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur != null) {
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }
        return prev;
    }


}
