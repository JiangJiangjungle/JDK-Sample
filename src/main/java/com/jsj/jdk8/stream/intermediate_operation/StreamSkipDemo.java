package com.jsj.jdk8.stream.intermediate_operation;

import java.util.Arrays;
import java.util.Collection;

/**
 * Stream中的skip方法示例
 *
 * @author jiangshenjie
 */
public class StreamSkipDemo {
    public static void main(String[] args) {
        Collection<String> list = Arrays.asList("123", "12345", "ABC", "E", "123", "12345");
        list.stream()
                .skip(2)
                .forEach(System.out::println);
    }
}
