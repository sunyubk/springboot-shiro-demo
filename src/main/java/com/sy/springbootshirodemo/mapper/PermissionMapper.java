package com.sy.springbootshirodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.springbootshirodemo.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findPermissionByUserid(String userid);
}
