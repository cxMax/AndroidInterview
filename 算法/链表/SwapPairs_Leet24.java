package com.android.test1.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 24. 两两交换链表中的节点
 * 需要用到双指针， 和虚拟节点的技巧。
 * 交换顺序，我觉得这么理解，【1，2，3，4】 -》 【2，1，3，4】 -》 【2，3，1，4】 -》 移位 -》 【2，1，3，4】
 * </p>
 * Created by caixi on 7/16/21.
 */
public class SwapPairs_Leet24 {


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
     * 双指针和虚拟节点
     * [1,2,3,4]
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        // 构造虚拟节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode tmp = head.next.next;
            // 先 2->1
            prev.next = head.next;
            // 再 1->3
            head.next.next = head;
            // 再 3->2
            head.next = tmp;
            // 交换完毕，双指针进位
            prev = head;
            head = head.next;
        }
        return dummy.next;

    }
}
