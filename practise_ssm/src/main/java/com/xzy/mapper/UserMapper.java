package com.xzy.mapper;

import com.xzy.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 王文涛
 * @since 2019-01-19
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户注册
     * @param user
     * @return
     */
    int regist(User user);

    /**
     *  用户登录
     * @param email
     * @param password
     * @return
     */

    User login(String email,String password);

    /**
     * 根据用户邮箱查询用户
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    User findById(long id);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * 根据邮箱账号删除用户
     * @param email
     */
    void deleteByEmail(String email);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);


}