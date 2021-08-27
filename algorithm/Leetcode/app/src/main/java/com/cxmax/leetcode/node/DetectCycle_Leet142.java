package com.cxmax.leetcode.node;


/**
 * @describe :
 * @usage :
 * <p>
 * * 142. 环形链表 II
 * * 判断链表有环 双指针法， 指针走两次。
 * * 第一次，是fast 走 2步， slow走1步，相遇，重置fast指针的位置
 * * 第二次，是fast、slow各走1步，这个时候， fast重新走head走， slow接着往前走。相遇，这个点即环的入口
 * </p>
 * Created by caixi on 8/25/21.
 */
public class DetectCycle_Leet142 {

    /**
     * 双指针大法
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 第一次循环， 快指针走两步，慢指针走一步
        while(true) {
            if(fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 第一次相遇、重置
        fast = head;
        // 第二次遍历，快慢指针各走一步
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }


    public ListNode detectCycle1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

}
