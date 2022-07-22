package com.cxmax.third.node;


/**
 * 24. 两两交换链表中的节点
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * <p>
 * 输入：head = [1,2,3,4]
 * <p>
 * 输出：[2,1,4,3]
 * <p>
 * Created by caixi on 2022/7/22.
 */
public class SwapPairs {

    /**
     * 链表，两两交换，尽可能去理解，算法
     * 1，2，3，4
     *
     * 1. 构造虚拟节点， -1位
     * 2. 构造prev和temp两个指针
     * 3. prev指向虚拟节点
     * 4. tmp 指向 3
     * 遍历交换，
     * 1. 2->1
     * 2. 1->3
     * 3. 3->2
     * 4. 往前进位，相当于i++，只是双指针的进位，prev节点和head
     *
     * 5. 最后返回头节点，
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 构造双指针
        ListNode prev = dummy;
        // 开始遍历，交换顺序
        while(prev.next != null && prev.next.next != null) {
            // todo caixi 2022-7-22 , 固定算法容易写错， 就是 2-1， 1-3， 3-2
            // 3
            ListNode tmp = head.next.next;
            // 开始交换
            // 2-1
            prev.next = head.next;
            // 1-3
            head.next.next = head;
            // 3-2
            head.next = tmp;
            // 进位
            prev = head;
            head = head.next;
        }
        return dummy.next;
    }

}
