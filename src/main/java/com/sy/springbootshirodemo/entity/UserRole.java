package com.sy.springbootshirodemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("shito_user_role")
public class ShitoUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField("id")
    private String id;
    @TableField("uid")
    private String uid;
    @TableField("rid")
    private String rid;
}
