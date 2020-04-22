package com.jsj.jdk8.stream.intermediate_operation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream中的peek方法示例
 *
 * @author jiangshenjie
 */
public class StreamPeekDemo {
    public static void main(String[] args) {
        Integer[] intArray= new Integer[]{1,2,3,4,5,6};
        List<Integer> integerList = new ArrayList<>(8);
        Stream.of(intArray)
                .peek(integerList::add)
                .forEach(System.out::println);
    }
}
