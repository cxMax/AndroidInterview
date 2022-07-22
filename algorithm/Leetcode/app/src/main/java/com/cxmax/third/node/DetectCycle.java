package com.cxmax.third.node;

/**
 * 142. 环形链表 II
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * <p>
 * 输出：返回索引为 1 的链表节点
 * <p>
 * Created by caixi on 2022/7/22.
 */
public class DetectCycle {

    /**
     * 固定算法
     * 1. 双指针
     * 2. 先走一次，快指针每次走2个节点，慢指针每次走1个节点
     * 3. 重置快指针
     * 4. 第二次相遇的点，就是相交的点
     *
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
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
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;

    }
}
