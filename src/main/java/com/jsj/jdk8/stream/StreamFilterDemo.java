package com.jsj.jdk8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合中元素的过滤
 *
 * @author jiangshenjie
 */
public class StreamFilterDemo {

    /**
     * 获取集合中所有满足特定条件的字符串
     */
    public static void main(String[] args) {
        Collection<String> stringCollection = Arrays.asList("啦啦啦", "ABC", "E");
        //Intermediate操作,只保留所有包含"A"的字符串
        Predicate<String> predicate = str -> str.contains("A");
        //Terminal操作,聚合符合条件的元素
        List<String> result = stringCollection.stream().filter(predicate).collect(Collectors.toList());
        //打印
        result.forEach(System.out::println);
    }
}
