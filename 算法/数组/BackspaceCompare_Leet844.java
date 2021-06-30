package com.android.test1;

/**
 * @describe :
 * @usage :
 * <p>
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * <p>
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </p>
 * Created by caixi on 6/29/21.
 */
public class BackspaceCompare_Leet844 {


    /**
     * 使用栈来解决的方法
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    public String build(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char target = str.charAt(i);
            if (target != '#') {
                sb.append(target);
            } else {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 使用双指针做法
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare1(String s, String t) {

    }

}
