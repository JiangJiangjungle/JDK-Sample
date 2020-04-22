package com.jsj.jdk8.stream.intermediate_operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Stream中的filter方法示例
 *
 * @author jiangshenjie
 */
public class StreamFilterDemo {

    public static void main(String[] args) {
        Collection<String> stringCollection = Arrays.asList("啦啦啦", "ABC", "E", "AWE");
        //过滤出所有包含"A"的字符串
        stringCollection.stream()
                .filter( str -> str.contains("A"))
                .forEach(System.out::println);
    }
}
