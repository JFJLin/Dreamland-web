package com.xzy.service.impl;

import com.xzy.entity.User;
import com.xzy.mapper.UserMapper;
import com.xzy.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int regist(User user) {
       int i = userMapper.regist(user);
        return i;
    }

    @Override
    public User login(String email, String password) {
        return userMapper.login(email,password);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public User findById(long id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByPhone(String phone) {
        return userMapper.findByPhone(phone);
    }

    @Override
    public void deleteByEmail(String email) {

        userMapper.deleteByEmail(email);
    }

    @Override
    public void update(User user) {

        userMapper.update(user);
    }


}
