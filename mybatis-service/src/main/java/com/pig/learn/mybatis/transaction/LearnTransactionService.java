package com.pig.learn.mybatis.transaction;

import com.pig.learn.mybatis.domain.User;
import com.pig.learn.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * @Author: zhuerdong
 * @Date: Created in 下午9:58 17/6/27
 * @Description:mybatis 事务
 */
@Service
public class LearnTransactionService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 确定这么使用有回滚的效果，且异常会抛出
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void testUseTransaction() {
        User record1 = new User();
        record1.setName("12345");
        userMapper.insert(record1);


        User record2 = new User();
        record2.setName("23445");
        userMapper.insert(record2);
    }
}
