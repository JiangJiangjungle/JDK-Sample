package com.jsj.jdk8.stream;

import java.util.Arrays;
import java.util.Collection;

/**
 * 集合元素的排序，可以通过各类map,filter,limit,skip以后再sort以提高效率
 *
 * @author jiangshenjie
 */
public class StreamSortDemo {
    public static void main(String[] args) {
        Collection<String> stringCollection = Arrays.asList("??", "啦啦啦", "ABC", "E", "ddsad2e", "12345", "123456");
        stringCollection.stream()
                //跳过第一个字符串
                .skip(1)
                //只保留长度小于6的字符串
                .filter(s -> s.length() < 6)
                //只保留3个结果
                .limit(3)
                //默认排序
                .sorted(String::compareTo)
                //打印
                .forEach(System.out::println);
    }
}
