package com.pig.learn.mybatis.guava;


//1.将List集合转换为map

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.List;
import java.util.Map;

//guava list to map
public class GuavaMap {


    public static void main(String[] args) {
        tranFromMapValue();
    }

    public static void tranFromMapValue(){
        MyObject myObject1 = new MyObject(1, "pig1");
        MyObject myObject2 = new MyObject(2, "pig2");
        MyObject myObject3 = new MyObject(3, "pig3");
        MyObject myObject4 = new MyObject(4, "pig4");
        MyObject myObject5 = new MyObject(5, "pig5");
        MyObject myObject6 = new MyObject(6, "pig6");
        MyObject myObject7 = new MyObject(7, "pig7");

        List<MyObject> myList = Lists.newArrayList(myObject1, myObject2,
                myObject3, myObject4, myObject5, myObject6, myObject7);

        Map<Integer,MyObject> map = Maps.uniqueIndex(myList, new Function<MyObject, Integer>() {
            @Override
            public Integer apply(MyObject myObject) {
                return myObject.getId();
            }
        });

        Map<Integer,String> mapValue = Maps.transformValues(map, new Function<MyObject, String>() {
            @Override
            public String apply(MyObject myObject) {
                return myObject.getName();
            }
        });

        System.out.println(mapValue);
        //{1=pig1, 2=pig2, 3=pig3, 4=pig4, 5=pig5, 6=pig6, 7=pig7}
    }
    /**
     * 一个key对应一个list
     */
    public static void listToMultimap(){
        MyObject myObject1 = new MyObject(1, "pig1");
        MyObject myObject2 = new MyObject(1, "pig2");
        MyObject myObject3 = new MyObject(1, "pig3");
        MyObject myObject4 = new MyObject(2, "pig4");
        MyObject myObject5 = new MyObject(2, "pig5");
        MyObject myObject6 = new MyObject(3, "pig6");
        MyObject myObject7 = new MyObject(3, "pig7");

        List<MyObject> myList = Lists.newArrayList(myObject1, myObject2,
                myObject3, myObject4, myObject5, myObject6, myObject7);

        Multimap<Integer, MyObject> multimap = Multimaps.index(myList, new Function<MyObject, Integer>() {
            @Override
            public Integer apply(MyObject myObject) {
                return myObject.getId();
            }
        });

        System.out.println(multimap);
//        {1=[MyObject{id=1, name='pig1'}, MyObject{id=1, name='pig2'}, MyObject{id=1, name='pig3'}],
//            2=[MyObject{id=2, name='pig4'}, MyObject{id=2, name='pig5'}], 3=[MyObject{id=3, name='pig6'}, MyObject{id=3, name='pig7'}]}

    }

    /**
     * 一个key 对应一个对象
     */
    public static void listToUniqueIndexMap(){
        MyObject myObject1 = new MyObject(1, "pig1");
        MyObject myObject2 = new MyObject(2, "pig2");
        MyObject myObject3 = new MyObject(3, "pig3");
        MyObject myObject4 = new MyObject(4, "pig4");
        MyObject myObject5 = new MyObject(5, "pig5");
        MyObject myObject6 = new MyObject(6, "pig6");
        MyObject myObject7 = new MyObject(7, "pig7");

        List<MyObject> myList = Lists.newArrayList(myObject1, myObject2,
                myObject3, myObject4, myObject5, myObject6, myObject7);

        Map<Integer,MyObject> map = Maps.uniqueIndex(myList, new Function<MyObject, Integer>() {
            @Override
            public Integer apply(MyObject myObject) {
                return myObject.getId();
            }
        });

        System.out.println(map);
        //        {1=MyObject{id=1, name='pig1'}, 2=MyObject{id=2, name='pig2'}, 3=MyObject{id=3, name='pig3'},
        //            4=MyObject{id=4, name='pig4'}, 5=MyObject{id=5, name='pig5'},
        //            6=MyObject{id=6, name='pig6'}, 7=MyObject{id=7, name='pig7'}}


    }
}

class MyObject {
    private Integer id;
    private String name;

    public MyObject(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}