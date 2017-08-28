package com.pig.learn.mybatis.BeanCopyTest;

import net.sf.cglib.beans.BeanCopier;

public class BeanCopyTest {
    public static void main(String[] args) throws Exception{

        SourceObject source = new SourceObject();
        source.setAge(1);
        source.setId(2);
        source.setName("pig");

        TargetObject target = new TargetObject();

        //第一种使用
        //org.springframework.beans.BeanUtils.copyProperties(source,target);


        //第二种
        //org.apache.commons.beanutils.BeanUtils.copyProperties(target,source);

        //第三种
        //org.apache.commons.beanutils.PropertyUtils.copyProperties(target,source);

        //第四种,这是最快的一种推荐使用
        //net.sf.cglib.beans.BeanCopier;
        BeanCopier copier = BeanCopier.create(source.getClass(),target.getClass(),false);
        copier.copy(source,target,null);

        System.out.println(target.toString());

    }
}
