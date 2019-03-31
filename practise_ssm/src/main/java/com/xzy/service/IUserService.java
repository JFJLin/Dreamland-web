package com.xzy.service;

import com.xzy.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public interface IUserService extends IService<User> {

    int regist(User user);
    User login(String email,String password);
    User findByEmail(String email);
    User findById(long id);
    User findByPhone(String phone);
    void deleteByEmail(String email);
    void update(User user);

}
