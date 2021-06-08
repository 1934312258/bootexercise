package com.kevin;

import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author
 * @date 2020-5-27 16:56
 * @description todo
 **/
@Data
public class Test2 implements TestInterface {

    private static java.lang.Object Object;
    private String name;
    private Integer age;
    static Object object = null;

    @Override
    public void test1() {
        System.out.println("多个怎么解决");
    }

    private String handleArrayList(List list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(a -> {
            sb.append(a + ",");
        });
        return sb.substring(0, sb.length() - 1);
    }

    static boolean first;

    public static void main(String[] args) {
        // 约瑟夫猜想，n个人围成一圈，开始报数，杀掉第M个人
        int[] ints = {1, 2, 3};
        Node first = new Node(ints[0]);
        Node node = null;
        for (int i = 0; i < ints.length; i++) {
            if (i == 0) {
                node = first;
            } else {
                node.next = new Node(ints[i]);
                node = node.next;
            }
            if (i == ints.length - 1) {
                node.next = first;
            }
        }
        for (int i = 0; i < ints.length - 1; i++) {
            if (i == 0) {
                node = first;
            }
            for (int j = 1; j < 3; j++) {

                if (j == 2) {
                    if (node.next.next.equals(node)) {
                        node.next = null;
                    } else {
                        node.next = node.next.next;
                        node = node.next;
                    }
                } else {
                    node = node.next;
                }
            }
        }
        System.out.println(node.value);
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}


