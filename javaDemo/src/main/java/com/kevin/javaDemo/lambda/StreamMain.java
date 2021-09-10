package com.kevin.javaDemo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaowenjian
 * @since 2021/7/2 13:20
 */
public class StreamMain {

    public static void main(String[] args) {
        List<Long> list = Arrays.asList(1L,2L,3L);
        String str = list.stream().map(a->a.toString()).collect(Collectors.joining(","));

        System.out.println();
    }
}
