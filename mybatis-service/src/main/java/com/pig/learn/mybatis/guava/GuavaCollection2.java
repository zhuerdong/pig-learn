package com.pig.learn.mybatis.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhuerdong
 * @Date: Created in 下午1:39 17/6/19
 * @Description:java GuavaCollection2 根据条件过滤集合
 */
public class GuavaCollection2 {

    public static void main(String[] args) {
        //1.过滤器：利用Collection2.filter()方法过滤集合中不符合条件的元素。譬如过滤一个List里面小于10的元素：
        List<Integer> myList = Lists.newArrayList(1,2,3,5);
        Collection<Integer> filterCollection = Collections2.filter(myList, new Predicate<Integer>(){
            @Override
            public boolean apply(Integer input){
                return input >= 10;
            }
        });

        //当然，你可以自己写一个循环来实现这个功能，但是这样不能保证之后小于10的元素不被放入集合。
        // filter的强大之处在于返回的filterCollection仍然有排斥小于10的元素的特性，
        // 如果调filterCollection.add(9)就会得到一个IllegalArgumentException.


        //转换器：利用Collections.transform方法来转换集合中的元素。譬如把一个Set里面所有元素都转化成带格式的String来产生新的Collection
        Set<Integer> mySet = Sets.newHashSet(1,2,3,4,5);

        Collections2.transform(mySet, new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return "1234";
            }
        });
        //应当注意：每次调用返回都是新的对象，同时操作过程不是线程安全的。

        //集合按照多个条件过滤
        //Predicate  返回为true 的保留， 返回为false的过滤掉
        //Predicates.and(predicate1, predicate2)  predicate1 与 predicate2 返回都为true的保留
        //Predicates.or(predicate1, predicate2)   predicate1 与 predicate2 有一个返回true 则保留
        //保留age不为26的Integer
        Predicate<Integer> predicate1 = new Predicate<Integer>() {
            public boolean apply(Integer integer) {
                if(integer != 26){
                    return true;
                }
                return false;
            }
        };

        //保留age不为24的Integer
        Predicate<Integer> predicate2 = new Predicate<Integer>() {
            public boolean apply(Integer integer) {
                if(integer != 3){
                    return true;
                }
                return false;
            }
        };

        List<Integer> userIds = Lists.newArrayList(1, 2, 3, 4);
        Collection<Integer> result = Collections2.filter(userIds,
                Predicates.and(predicate1, predicate2));
        System.out.println(result);
    }
}