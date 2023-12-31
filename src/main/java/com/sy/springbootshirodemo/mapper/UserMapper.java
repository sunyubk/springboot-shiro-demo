package com.sy.springbootshirodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.springbootshirodemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
