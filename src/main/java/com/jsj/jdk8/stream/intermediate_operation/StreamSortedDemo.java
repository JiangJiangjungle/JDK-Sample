package com.jsj.jdk8.stream.intermediate_operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * Stream中的sort方法示例
 *
 * @author jiangshenjie
 */
public class StreamSortedDemo {
    public static void main(String[] args) {
        Collection<String> list = Arrays.asList("??", "啦啦啦", "ABC", "E", "ddsad2e", "12345", "123456");
        //Comparable的元素默认排序
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("----------------");

        //通过自定义Comparator排序
        list.stream()
                .filter(s -> s != null && s.length() > 0)
                .sorted((s1, s2) -> s2.length() - s1.length())
                .forEach(System.out::println);
    }
}
