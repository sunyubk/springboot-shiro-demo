package com.sy.springbootshirodemo.config.shiro;

import com.sy.springbootshirodemo.entity.User;
import com.sy.springbootshirodemo.service.PermissionService;
import com.sy.springbootshirodemo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName MyRealm
 * @Description
 * @Author sunyu
 * @Date 2023/9/22 12:33
 * @Version 1.0
 **/
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权--权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取主体中的用户对象
        User user = (User) principalCollection.getPrimaryPrincipal();

        // 根据用户ID查询用户权限列表
        List<String> list = permissionService.selectPermissionByUserid(user.getId().toString());
        list.add("user:getById");

        // 如果权限列表不为空
        if (list.size() != 0) {
            // 创建 SimpleAuthorizationInfo 对象
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            // 将权限列表添加到授权信息中
            info.addStringPermissions(list);

            // 返回授权信息
            return info;
        }
        // 返回空，表示无授权信息
        return null;

    }

    /**
     * 认证 -- 登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取登录账号
        String username = (String) authenticationToken.getPrincipal();

        //根据账号获取用户信息
        User user = userService.findByUsername(username);
        if (Objects.nonNull(user)) {
            // 使用用户的盐值创建 ByteSource 对象
            ByteSource salt = ByteSource.Util.bytes(user.getSalt());

            // 创建 SimpleAuthenticationInfo 对象，包含用户信息、密码、盐值和 Realm 名称
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), salt, this.getName());
            return info;
        }
        return null;
    }

}
