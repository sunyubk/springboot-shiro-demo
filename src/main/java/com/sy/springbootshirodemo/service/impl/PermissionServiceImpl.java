package com.sy.springbootshirodemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.springbootshirodemo.entity.Permission;
import com.sy.springbootshirodemo.mapper.PermissionMapper;
import com.sy.springbootshirodemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper mapper;

    @Override
    public List<String> selectPermissionByUserid(String userid) {
        // 根据用户ID查询用户权限列表
        List<Permission> permissionList = mapper.findPermissionByUserid(userid);

        // 使用流操作将权限列表中的每个权限对象映射为权限编码，并收集为新的列表
        List<String> collect = permissionList.stream().map(Permission::getCode).collect(Collectors.toList());

        // 返回权限编码列表
        return collect;
    }

}
