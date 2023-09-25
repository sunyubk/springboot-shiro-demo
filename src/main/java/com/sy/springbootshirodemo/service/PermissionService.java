package com.sy.springbootshirodemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sy.springbootshirodemo.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
public interface PermissionService extends IService<Permission> {
    List<String> selectPermissionByUserid(String userid);

}
