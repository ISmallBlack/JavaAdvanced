package com.algorithms.wordbreak;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        List<String> list = new Solution().wordBreak("catsanddog",new HashSet<>(Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(Arrays.toString(list.toArray()));
    }
    private Map<String, Set<String>> map = new HashMap<String, Set<String>>();
    public List<String> wordBreak(String s, Set<String> dict) {
        Set<String> sentences = toSentence(s, dict);
        return setToList(sentences);
    }

    /**
     * 实现思路：
     * 1.从头遍历字符串 如果发现遍历的子串 属于字典  则截取剩余字符串 进行递归
     * @param s
     * @param dict
     * @return
     */
    private Set<String> toSentence(String s, Set<String> dict){
        if(! map.containsKey(s)){
            Set<String> temp = new HashSet<String>();
            map.put(s,temp);
        }else{
        	return map.get(s);
        }
        int length = s.length();
        Set<String> mySet = map.get(s);
        if(dict.contains(s)){
            mySet.add(s);
        }
        for(int i = 1; i < length; i++){
            String head = s.substring(0,i);
            if(!dict.contains(head)){
                continue;
            }
            Set<String> tail = null;
            if(! map.containsKey(s.substring(i))){
                tail = toSentence(s.substring(i),dict);
            }else{
                tail = map.get(s.substring(i));
            }
            if(tail.size() == 0){
            	continue;
            }
 
            Iterator<String> tailIte = tail.iterator();
            while(tailIte.hasNext()){
            	mySet.add(head + " " + tailIte.next());
            }
            
        }
        
        return mySet;
        
    }
    
    private List<String> setToList(Set<String> set){
        List<String> list = new ArrayList<String>();
        Iterator<String> ite = set.iterator();
        while(ite.hasNext()){
            list.add(ite.next());
        }
        return list;
    }
}
