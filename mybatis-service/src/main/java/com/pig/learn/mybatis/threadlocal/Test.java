package com.pig.learn.mybatis.threadlocal;

public class Test {

    static {
        System.out.println(1);
        System.out.println(2);

    }

    public static void main(String[] args) {
        Test test = new Test();
    }
}
