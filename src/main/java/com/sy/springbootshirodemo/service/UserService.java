package com.sy.springbootshirodemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sy.springbootshirodemo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
public interface UserService extends IService<User> {

    User findByUsername(String username);

    User register(String username, String password);
}
