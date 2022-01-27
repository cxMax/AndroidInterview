package com.cxmax.selftest.arrays

/**
 *
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * 35.搜索插入位置
 *
 * todo 这个比较有实用性， 一般比如，排行榜插入顺序的问题，但是要注意有序不重复
 *
 * Created by caixi on 2021/12/27.
 */
object SearchInsert {

    fun test() {
        val array = arrayOf(0, 1, 2, 4 ,5)
        searchInsert(array, 3)
    }

    fun searchInsert(array: Array<Int>?, target: Int): Int {
        if (array == null || array.size == 0) {
            return -1
        }
        var left = 0
        var right = array.size - 1
        while(left <= right) {
            var middle = left + ((right - left) / 2)
            if (array[middle] < target) {
                left = middle + 1
            } else if (array[middle] > target) {
                right = middle - 1
            } else {
                return middle
            }
        }
        return left
    }
}