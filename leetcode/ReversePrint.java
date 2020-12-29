package com.trailblazer.easyshare;

import java.util.Stack;

/**
 * @describe :
 * @usage :
 * <p>
 * 从尾到头打印链表， 反转链表打印顺序
 * </p>
 * Created by caixi on 12/25/20.
 */
public class ReversePrint {

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);
        node1.next = node2;
        node2.next = node3;
    }

    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        if (stack.isEmpty()) {
            return new int[0];
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop().val;
        }
        return result;
    }
}
