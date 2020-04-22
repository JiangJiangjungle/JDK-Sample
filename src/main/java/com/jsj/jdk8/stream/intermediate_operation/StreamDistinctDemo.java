package com.jsj.jdk8.stream.intermediate_operation;

import java.util.Arrays;
import java.util.Collection;

/**
 * Stream中的distinct方法示例
 *
 * @author jiangshenjie
 */
public class StreamDistinctDemo {
    public static void main(String[] args) {
        Collection<String> stringCollection = Arrays.asList("123", "12345", "ABC", "E", "123", "12345");
        stringCollection.stream()
                .distinct()
                .forEach(System.out::println);
    }
}
