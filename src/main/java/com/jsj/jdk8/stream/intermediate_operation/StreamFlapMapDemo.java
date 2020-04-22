package com.jsj.jdk8.stream.intermediate_operation;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream中的map方法示例
 *
 * @author jiangshenjie
 */
public class StreamFlapMapDemo {
    public static void main(String[] args) {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1,2,3),
                Arrays.asList(2,3,4),
                Arrays.asList(7,9,16));
        inputStream
                //数据扁平化
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }
}
