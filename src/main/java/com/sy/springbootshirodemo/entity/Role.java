package com.sy.springbootshirodemo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
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
@Data
@TableName("shiro_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("id")
    private String id;
    @TableField("name")
    private String name;
}
