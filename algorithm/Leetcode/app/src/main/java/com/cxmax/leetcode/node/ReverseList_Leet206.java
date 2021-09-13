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


    /**
     * 三刷 链表反转
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
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
     * 四刷反转链表
     *
     * 做错了， 是因为 返回节点，完全懵的
     *
     * @param head
     * @return
     */
    public ListNode reverseList3(ListNode head) {
        ListNode prev = null; // 新的头节点， 你要反转嘛
        ListNode cur = head;
        ListNode tmp = null;
        while(cur != null) {
            tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        return prev
    }


}
