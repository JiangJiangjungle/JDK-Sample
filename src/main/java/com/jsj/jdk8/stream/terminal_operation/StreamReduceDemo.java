package com.jsj.jdk8.stream.terminal_operation;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Stream中的reduce方法示例
 *
 * @author jiangshenjie
 */
public class StreamReduceDemo {
    public static void main(String[] args) {
        Integer[] intArray = new Integer[]{1, 2, 3, 4, 5, 6};
        //通过reduce方法进行简单累加
        int sum1 = Stream.of(intArray).reduce(0, (tmp_sum, num) -> tmp_sum += num);
        System.out.printf("sum1 = %d\n", sum1);
        //考虑到溢出问题，通过reduce方法进行类型转换和累加
        long sum2 = Stream.of(intArray)
                .reduce(0L, (tmp_sum, num) -> tmp_sum += num, (tmp_sum1, tmp_sum2) -> 0L);
        System.out.printf("sum2 = %d\n", sum2);
    }
}
