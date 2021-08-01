package com.android.test1.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 142. 环形链表 II
 * 判断链表有环 双指针法， 指针走两次。
 * 第一次，是fast 走 2步， slow走1步，相遇，重置fast指针的位置
 * 第二次，是fast、slow各走1步，这个时候， fast重新走head走， slow接着往前走。相遇，这个点即环的入口
 *
 * </p>
 * Created by caixi on 7/19/21.
 */
public class DetectCycle_Leet142 {

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
     * 双指针大法
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(true) {
            // 为null， 就表示无环
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 重置fast位置
        fast = head;
        // slow继续往前走， 再次相遇即环入口
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
