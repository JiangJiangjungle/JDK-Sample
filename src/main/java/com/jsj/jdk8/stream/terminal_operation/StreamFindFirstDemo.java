package com.jsj.jdk8.stream.terminal_operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Stream中的anyMatch方法示例
 */
public class StreamFindFirstDemo {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("啦啦啦", "ABC", "E", "AWE");
        Optional<String> firstElement = strings.stream()
                .findFirst();
        System.out.printf("First Element is [%s]\n", firstElement.get());
    }
}
