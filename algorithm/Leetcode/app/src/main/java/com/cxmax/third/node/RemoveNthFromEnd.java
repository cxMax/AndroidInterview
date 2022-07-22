package com.cxmax.third.node;


/**
 *
 * 19. 删除链表的倒数第 N 个结点
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 输入：head = [1,2,3,4,5], n = 2
 *
 * 输出：[1,2,3,5]
 *
 * Created by caixi on 2022/7/22.
 */
public class RemoveNthFromEnd {

    /**
     *
     * 这道题还是固定算法，就两个点，
     *
     * 1. 虚拟头节点， 都是从 -1位开始移动
     * 2. 双指针，
     * 3. 快指针先走n + 1步
     * 4. 快、慢指针都进步
     * 5. 快指针到头的时候，慢指针删除下一位即可
     *
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 双指针
        ListNode slow = dummy;
        ListNode fast = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
    }

}
