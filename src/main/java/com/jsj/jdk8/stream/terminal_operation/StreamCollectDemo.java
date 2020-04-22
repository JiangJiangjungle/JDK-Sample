package com.jsj.jdk8.stream.terminal_operation;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream中的collect方法示例
 *
 * @author jiangshenjie
 */
public class StreamCollectDemo {
    public static void main(String[] args) {
        Stream<List<String>> inputStream = Stream.of(
                Arrays.asList("eee","abce","jjsu"),
                Arrays.asList("123","dswde2","24jdsf")
        );
        //collect方法通常搭配Collectors使用
        List<String> result = inputStream.flatMap(Collection::stream)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
