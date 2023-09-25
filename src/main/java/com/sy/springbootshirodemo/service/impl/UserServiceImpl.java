package com.ncpe.digitaldelivery.codegenerator.service.impl;

import com.ncpe.digitaldelivery.codegenerator.entity.User;
import com.ncpe.digitaldelivery.codegenerator.mapper.UserMapper;
import com.ncpe.digitaldelivery.codegenerator.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
