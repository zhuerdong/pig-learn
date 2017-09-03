package com.pig.learn.mybatis.guava;

public class testGuavaJoinerAndSpilt {

//    直接看例子：
//            1.分割
//[java] view plain copy
//  1. String str = "a, , b, c";
//  2.        List<String> componentIdsArray = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(str);
//  3.        for (String s : componentIdsArray) {
//        4.            System.out.println(s);
//        5.        }
//
//    注意：trimResults只是对结果进行trim，但是不是取出空，必须加上omitEmptyStrings才行，这样才能输出a,b,c 不加omitEmptyStrings 会输出a, ,b,c
//
//2.连接
//[java] view plain copy
//  1. public static void  testGuavaJoiner(){
//        2.         List<Integer> list = Lists.newArrayList(1,5,7);
//        3.         String S = Joiner.on(",").join(list);
//        4.         System.out.println(S);//1,5,7
//        5.     }
}
