package com.sy.springbootshirodemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.springbootshirodemo.entity.Role;
import com.sy.springbootshirodemo.mapper.RoleMapper;
import com.sy.springbootshirodemo.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
