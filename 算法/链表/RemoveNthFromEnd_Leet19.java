package com.android.test1.node;

/**
 * @describe :
 * @usage :
 * <p>
 * 19.删除链表的倒数第 N 个结点
 * 这个主要是不知道链表长度， 需要用到双指针法
 * </p>
 * Created by caixi on 7/16/21.
 */
public class RemoveNthFromEnd_Leet19 {

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
     * 双指针来解决这个问题
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 构造虚拟节点
        ListNode dummy = new ListNode(-1, head);
        // 构造快、慢指针
        ListNode slow = dummy;
        ListNode fast = dummy;
        // 让快指针先走n + 1步， 因为是从虚拟节点开始走的
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        // 开始遍历, 找到要删除慢节点的位置
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }


}
