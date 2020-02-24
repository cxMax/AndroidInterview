package com.huajiao.yuewan.gift.backpack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 2020-01-29.
 */
public class LeetCode {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main() {


    }


    /**
     * 3. 无重复字符的最长子串
     * 标签：滑动窗口
     * 暴力解法时间复杂度较高，会达到 O(n^2)，故而采取滑动窗口的方法降低时间复杂度
     * 定义一个 map 数据结构存储 (k, v)，其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复
     * 我们定义不重复子串的开始位置为 start，结束位置为 end
     * 随着 end 不断遍历向后，会遇到与 [start, end] 区间内字符相同的情况，此时将字符作为 key 值，获取其 value 值，并更新 start，此时 [start, end] 区间内不存在重复字符
     * 无论是否更新 start，都会更新其 map 数据结构和结果 ans。
     * 时间复杂度：O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int max = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i) + 1));
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * LeetCode 总结 - 搞定 Binary Tree 面试题
     * https://www.jianshu.com/p/5fbd07d557a3
     */
    public static class BinaryTree {
        /**
         * 515. 在每个树行中找最大值
         * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
         *
         * @param root
         * @return
         */
        public static List<Integer> LargestValuesBinaryTree(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            as(0, result, root);
            return result;
        }

        public static void as(int level, List<Integer> list, TreeNode node) {
            if (level == list.size()) {
                list.add(level, node.val);
            }
            list.set(level, Math.max(list.get(level), node.val));
            if (node.left != null) {
                as(level+1, list, node.left);
            }
            if (node.right != null) {
                as(level+1, list, node.right);
            }
        }

        /**
         * 144. 二叉树的前序遍历
         * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
         *
         * 前序遍历就是 - 中 - 左 - 右
         *
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper(root, result);
            return result;

        }

        private void helper(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            result.add(root.val);
            helper(root.left, result);
            helper(root.right, result);
        }


        /**
         * 94. 二叉树的中序遍历
         * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
         *
         *
         * 中序遍历就是  - 左 - 中 - 右
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper1(root, result);
            return result;
        }

        private void helper1(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                helper1(root.left, result);
            }
            result.add(root.val);
            if (root.right != null) {
                helper1(root.right, result);
            }
        }

        /**
         * 145. 二叉树的后序遍历
         * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
         *
         * 中序遍历就是  - 右 - 中 - 左
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            helper2(root, result);
            return result;
        }

        private void helper2(TreeNode root, List<Integer> result) {
            if (root == null) {
                return;
            }
            if (root.left != null) {
                helper1(root.left, result);
            }
            if (root.right != null) {
                helper1(root.right, result);
            }
            result.add(root.val);
        }
    }


    /**
     * 225. 用队列实现栈
     * https://leetcode-cn.com/problems/implement-stack-using-queues/
     *
     */
    public class StackByQueue {

        private Queue<Integer> inQueue;
        private Queue<Integer> outQueue;

        public StackByQueue() {
            inQueue = new LinkedBlockingQueue<>();
            outQueue = new LinkedBlockingQueue<>();
        }

        public void push(int x) {
            inQueue.add(x);
            while (!outQueue.isEmpty()) {
                inQueue.add(outQueue.poll());
            }
            while (!inQueue.isEmpty()) {
                outQueue.add(inQueue.poll());
            }
        }

        public int pop() {
            return outQueue.poll();
        }

        public int top() {
            return outQueue.peek();
        }

        public boolean empty() {
            return outQueue.isEmpty();
        }
    }

    /**
     * 比较排序
     */
    public static class ComparableSortArray {

        /*
         * 冒泡排序 : $O(n^2)$
         *
         * reference : http://www.cnblogs.com/skywang12345/p/3596232.html
         */
        public static void bubbleSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }

            int i, j;
            int n = array.length;
            for (i = n - 1; i > 0; i--) {
                // 将a[0...i]中最大的数据放在末尾
                for (j = 0; j < i; j++) {
                    if (array[j] > array[j + 1]) {
                        // 交换a[j]和a[j+1]
                        int tmp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = tmp;
                    }
                }
            }
        }

        /*
         * 快速排序 :
         *
         * reference : https://www.cnblogs.com/xiaoming0601/p/5862860.html
         */
        public static void quickSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            quickSort(array, 0, array.length - 1);
        }

        private static void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int mid = getMiddle(array, low, high);
                quickSort(array, low, mid - 1);
                quickSort(array, mid + 1, high);
            }
        }

        private static int getMiddle(int[] array, int low, int high) {
            int temp = array[low];
            while (low < high) {
                while (low < high && array[high] >= temp) {
                    high--;
                }
                array[low] = array[high];
                while (low < high && array[low] <= temp) {
                    low++;
                }
                array[high] = array[low];
            }
            array[low] = temp;
            return low;
        }

        /*
         * 插入排序 : 平均O(n^2),最好O(n),最坏O(n^2);空间复杂度O(1);稳定;简单
         *
         * reference : https://www.cnblogs.com/zengzhihua/p/4456730.html
         */
        public static void insertSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int temp;
            for (int i = 1; i < array.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (array[j] < array[j - 1]) {
                        temp = array[j - 1];
                        array[j - 1] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }

        /*
         * 希尔排序 (缩小增量排序) : $[O(n),O(n^2)]$;空间复杂度O(1);不稳定;较复杂
         *
         * reference :
         * http://www.cnblogs.com/skywang12345/p/3597597.html
         */
        public static void shellSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            int length = array.length;
            // gap为步长，每次减为原来的一半。
            for (int gap = length / 2; gap > 0; gap /= 2) {
                // 共gap个组，对每一组都执行直接插入排序
                for (int i = 0; i < gap; i++) {
                    groupSort(array, length, i, gap);
                }
            }
        }

        public static void groupSort(int[] array, int length, int start, int gap) {
            for (int j = start + gap; j < length; j += gap) {
                // 如果a[j] < a[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移。
                if (array[j] < array[j - gap]) {
                    int tmp = array[j];
                    int k = j - gap;
                    while (k >= 0 && array[k] > tmp) {
                        array[k + gap] = array[k];
                        k -= gap;
                    }
                    array[k + gap] = tmp;
                }
            }
        }

        /*
         * 选择排序 : $O(n^2)$
         *
         * reference : http://www.cnblogs.com/shen-hua/p/5424059.html
         */
        public static void selectSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            for (int i = 0; i < array.length - 1; i++) {
                int k = i;
                for (int j = k + 1; j < array.length; j++) {
                    if (array[j] < array[k]) {
                        k = j;
                    }
                }
                if (i != k) {
                    int temp = array[i];
                    array[i] = array[k];
                    array[k] = temp;
                }
            }
        }

        /*
         * 归并排序 : $O(nlogn)$
         *
         * reference : https://www.cnblogs.com/shudonghe/p/3302888.html
         */
        public static void mergeSort(int[] array) {
            if (array == null || array.length <= 1) {
                return;
            }
            sort(array, 0, array.length - 1);
        }

        private static void sort(int[] array, int left, int right) {
            if (left >= right) {
                return;
            }

            int mid = (left + right) / 2;
            sort(array, left, mid);
            sort(array, mid + 1, right);

            merge(array, left, mid, right);
        }

        private static void merge(int[] array, int left, int mid, int right) {
            int[] temp = new int[array.length];
            int r1 = mid + 1;
            int tIndex = left;
            int cIndex = left;

            while (left <= mid && r1 <= right) {
                if (array[left] <= array[r1]) {
                    temp[tIndex++] = array[left++];
                } else {
                    temp[tIndex++] = array[r1++];
                }
            }

            while (left <= mid) {
                temp[tIndex++] = array[left++];
            }

            while(r1 <= right) {
                temp[tIndex++] = array[r1++];
            }

            while(cIndex <= right) {
                array[cIndex] = temp[cIndex];
                cIndex++;
            }
        }

        /**
         * 堆排序
         */
        private static void heapSort(int[] arr) {
            // 将待排序的序列构建成一个大顶堆
            for (int i = arr.length / 2; i >= 0; i--){
                heapAdjust(arr, i, arr.length);
            }

            // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
            for (int i = arr.length - 1; i > 0; i--) {
                swap(arr, 0, i); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
                heapAdjust(arr, 0, i); // 交换之后，需要重新检查堆是否符合大顶堆，不符合则要调整
            }
        }

        /**
         * 构建堆的过程
         * @param arr 需要排序的数组
         * @param i 需要构建堆的根节点的序号
         * @param n 数组的长度
         */
        private static void heapAdjust(int[] arr, int i, int n) {
            int child;
            int father;
            for (father = arr[i]; leftChild(i) < n; i = child) {
                child = leftChild(i);

                // 如果左子树小于右子树，则需要比较右子树和父节点
                if (child != n - 1 && arr[child] < arr[child + 1]) {
                    child++; // 序号增1，指向右子树
                }

                // 如果父节点小于孩子结点，则需要交换
                if (father < arr[child]) {
                    arr[i] = arr[child];
                } else {
                    break; // 大顶堆结构未被破坏，不需要调整
                }
            }
            arr[i] = father;
        }

        // 获取到左孩子结点
        private static int leftChild(int i) {
            return 2 * i + 1;
        }

        // 交换元素位置
        private static void swap(int[] arr, int index1, int index2) {
            int tmp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = tmp;

        }

        public static void main(String[] args) {
            int i;
            int[] a = {20, 40, 30, 10, 60, 50};
            System.out.printf("before sort:");
            for (i = 0; i < a.length; i++)
                System.out.printf("%d ", a[i]);
            System.out.printf("\n");

//        bubbleSort(a);
//        quickSort(a);
//        insertSort(a);
//        shellSort(a);
//        selectSort(a);
//            mergeSort(a);
            heapSort(a);

            System.out.printf("after  sort:");
            for (i = 0; i < a.length; i++)
                System.out.printf("%d ", a[i]);
            System.out.printf("\n");
        }
    }

    /**
     * 非比较排序
     */
    public static class UnComparableSortArray {

        /*
         * 记数排序 :  计数排序需要占用大量空间，它仅适用于数据比较集中的情况。比如 [0~100]，[10000~19999] 这样的数据
         *
         * 需要三个数组
         *
         * reference : https://www.cnblogs.com/zer0Black/p/6169858.html
         */
        public static void countSort(int[] array) {
            if (array == null || array.length == 0) {
                return;
            }

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            //找出数组中的最大最小值
            for (int i = 0; i < array.length; i++) {
                max = Math.max(max, array[i]);
                min = Math.min(min, array[i]);
            }

            int[] help = new int[max];

            //找出每个数字出现的次数
            for (int i = 0; i < array.length; i++) {
                int mapPos = array[i] - min;
                help[mapPos]++;
            }

            int index = 0;
            for (int i = 0; i < help.length; i++) {
                while (help[i]-- > 0) {
                    array[index++] = i + min;
                }
            }

        }

        /*
         * 桶排序 :
         *
         * reference : http://www.cnblogs.com/skywang12345/p/3602737.html
         */
        public static void bucketSort(int[] arr, int max) {
            int[] buckets;

            if (arr == null || arr.length == 0 || max < 1)
                return;

            // 创建一个容量为max的数组buckets，并且将buckets中的所有数据都初始化为0。
            buckets = new int[max];

            // 1. 计数
            for (int i = 0; i < arr.length; i++)
                buckets[arr[i]]++;

            // 2. 排序
            for (int i = 0, j = 0; i < max; i++) {
                while ((buckets[i]--) > 0) {
                    arr[j++] = i;
                }
            }

            buckets = null;

        }

        /*
         * 基数排序 :
         *
         * reference :
         * https://www.cnblogs.com/Braveliu/archive/2013/01/21/2870201.html
         * https://www.cnblogs.com/haozhengfei/p/29ba40edbf659f2dbc6b429c2818c594.html
         */
        public static void radixSort(int[] arr) {
            if (arr == null || arr.length == 0) {
                return;
            }

            int length = arr.length;
            int divisor = 1;// 定义每一轮的除数，1,10,100...
            int[][] bucket = new int[10][length];// 定义了10个桶，以防每一位都一样全部放入一个桶中
            int[] count = new int[10];// 统计每个桶中实际存放的元素个数
            int digit;// 获取元素中对应位上的数字，即装入那个桶
            for (int i = 1; i <= 3; i++) {// 经过4次装通操作，排序完成
                for (int temp : arr) {// 计算入桶
                    digit = (temp / divisor) % 10;
                    bucket[digit][count[digit]++] = temp;
                }
                int k = 0;// 被排序数组的下标
                for (int b = 0; b < 10; b++) {// 从0到9号桶按照顺序取出
                    if (count[b] == 0)// 如果这个桶中没有元素放入，那么跳过
                        continue;
                    for (int w = 0; w < count[b]; w++) {
                        arr[k++] = bucket[b][w];
                    }
                    count[b] = 0;// 桶中的元素已经全部取出，计数器归零
                }
                divisor *= 10;
            }
        }

        public static void main(String[] args) {
            int i;
            int[] a = {20, 40, 30, 10, 60, 50};
            System.out.printf("before sort:");
            for (i = 0; i < a.length; i++)
                System.out.printf("%d ", a[i]);
            System.out.printf("\n");

//        countSort(a);
//        bucketSort(a, 100);
            radixSort(a);

            System.out.printf("after  sort:");
            for (i = 0; i < a.length; i++)
                System.out.printf("%d ", a[i]);
            System.out.printf("\n");
        }
    }

}
