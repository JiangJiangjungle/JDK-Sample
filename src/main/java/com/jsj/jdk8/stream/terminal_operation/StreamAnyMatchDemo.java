package com.jsj.jdk8.stream.terminal_operation;

import java.util.Arrays;
import java.util.Collection;

/**
 * Stream中的anyMatch方法示例
 *
 * @author jiangshenjie
 */
public class StreamAnyMatchDemo {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("啦啦啦", "ABC", "E", "AWE");
        boolean matched=strings.stream()
                .peek(System.out::println)
                .anyMatch(str->str.contains("ABC"));
        System.out.printf("Matched ? %s\n",matched);
    }
}
