package com.pig.learn.mybatis.guava;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 感觉这个非常有用
 */
public class CollectionFilter {

    private  static Predicate<Integer> predicate = new Predicate<Integer>() {
        @Override
        public boolean apply(Integer input) {
            return input > 1;
        }
    };

    //集合的过滤，必须经过转换
    public static void listFilter(){
        List<Integer> elements = Lists.newArrayList(1, 2, 3, 4);
        Collection<Integer> result = Collections2.filter(elements, predicate);
        List<Integer> elementsAfterFilter = Lists.newArrayList(result);
        System.out.println(elementsAfterFilter);
    }

    public static void setFilter(){
        Set<Integer> sets = Sets.newHashSet(1,2,3,4,5);
        Set<Integer> setsAfterFilter = Sets.filter(sets,predicate);
        System.out.println(setsAfterFilter);
    }

    public static void mapsFilter(){
        Map<Integer,String> map = Maps.newHashMap();
        map.put(1,"erdong1");
        map.put(2,"erdong2");
        map.put(3,"erdong3");

        Map<Integer,String> mapAfterFilter = Maps.filterKeys(map,predicate);
        System.out.println(mapAfterFilter);
    }
    public static void main(String[] args) {
        //listFilter();
        //setFilter();
        mapsFilter();
    }
}
