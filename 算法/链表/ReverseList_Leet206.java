package com.android.test1.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 206. 反转链表
 * </p>
 * Created by caixi on 7/16/21.
 */
public class ReverseList_Leet206 {

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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
            // 后面一个节点
            tmp = cur.next;
            // 反转当前节点
            cur.next = prev;
            // 指针移动， 当前节点变为前一个节点
            prev = cur;
            // 指针移动到后面一个节点
            cur = tmp;

        }
        return prev;
    }

    /**
     * 递归的方式来做
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode tmp = null;
        tmp  = cur.next;
        cur.next = prev;
        prev = cur;
        cur = tmp;
        return reverse(prev, cur);
    }

}
