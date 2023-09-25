package com.sy.springbootshirodemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.springbootshirodemo.entity.User;
import com.sy.springbootshirodemo.mapper.UserMapper;
import com.sy.springbootshirodemo.service.UserService;
import com.sy.springbootshirodemo.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public User findByUsername(String username) {
        // 创建一个查询条件包装器
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // 设置查询条件为"username = username"
        wrapper.eq("username", username);

        // 使用查询条件执行查询操作，返回单个用户对象
        User user = mapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User register(String username, String password) {
        // 用于 在使用单向哈希+盐的时候使用，如果使用BCrypt加密则可用于前端向服务端传递的加密密码中使用
        String salt = BCryptUtil.getSalt();
        String encodedPassword = BCryptUtil.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setSalt(salt);
        mapper.insert(user);
        // 后续可以补充逻辑，例如同名用户，密码是否过于简单之类的
        return user;
    }
}
