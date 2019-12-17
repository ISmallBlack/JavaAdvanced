package com.algorithms.wordbreak;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/12<br>
 * <br>
 */
public class Solution2 {

    /**
     * 算法思路是这样的：强调下，在思考算法之前一定要把题目的因果关系搞清楚以及需要明确结果成立的条件，此外找出条件与结果的规律也很重要。
     * 本例中，可以这么理解，字典实际上就是源字符串被分割而成的。我们只需要根据字典中的每个字符串的长度 依顺序截取源字符串进行比较即可。
     * 由此算法如下：
     * 对给定的字典进行遍历，每次遍历都用当前的字典元素与源字符串截取等长的字符串进行比较，
     * 截取子字符串的开始位置从0开始，如果结果返回true，那么就一定会让这两者相等的时候，
     * 当相等的时候就让开始截取的位置向前移动前一个已匹配的元素的长度，如此递归遍历，直到将所有字典遍历完毕。
     *
     * @param s
     * @param dict
     * @param start
     * @return
     */
    public static Set<String> wordBreakHelper(String s, Set<String> dict, int start) {
        if (start == s.length())
            return null;
        Set<String> all = new HashSet<>();
        List<String> words = new LinkedList<>();
        for (String a : dict) {
            int len = a.length();
            int end = start + len;
            if (end > s.length()) {
                continue;
            }
            if (s.substring(start, start + len).equals(a)) {
                words.add(a);
                Set<String> subset = wordBreakHelper(s.substring(end), dict, 0);
                if (CollectionUtil.isNotEmpty(subset)){
                    words.addAll(subset);
                }
            }
            if(words.stream().map(StrUtil::cleanBlank).collect(Collectors.joining()).equals(s)){
                all.add(words.stream().collect(Collectors.joining(" ")));
                words.clear();
            }
        }
        return all;
    }

    public static void main(String[] args) {
        Set<String> list = new Solution2().wordBreakHelper("catsanddog",new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog")),0);
        System.out.println(Arrays.toString(list.toArray()));
    }

}
