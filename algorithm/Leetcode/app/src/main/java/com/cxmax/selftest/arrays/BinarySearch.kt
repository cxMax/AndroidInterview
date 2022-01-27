package com.cxmax.selftest.arrays

/**
 *
 *  https://leetcode-cn.com/problems/binary-search/
 *
 *  704.二分查找
 *
 * Created by caixi on 2021/12/27.
 */
object BinarySearch {

    fun test() {
        val array = arrayOf(0, 1, 2, 3, 4)
        binarySearch(array, 3)
    }

    /**
     * 采用左闭右闭的写法
     * @param array Array<Int>
     * @return Int
     */
    fun binarySearch(array: Array<Int>?, target: Int): Int {
        if (array == null || array.size == 0) {
            return -1
        }
        var left = 0
        var right = array.size - 1
        while (left <= right) {
            val middle = left + ((right - left) / 2)
            if (array[middle] < target) {
                left = middle + 1
            } else if (array[middle] > target) {
                right = middle - 1
            } else {
                return middle
            }
        }
        return -1
    }
}