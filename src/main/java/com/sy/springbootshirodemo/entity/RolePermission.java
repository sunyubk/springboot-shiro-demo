package com.sy.springbootshirodemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@Getter
@Setter
@TableName("shiro_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private String id;
    @TableField("rid")
    private String rid;
    @TableField("per_id")
    private String perId;
}
