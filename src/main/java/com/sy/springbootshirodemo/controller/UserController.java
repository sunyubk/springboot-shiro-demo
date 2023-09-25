package com.sy.springbootshirodemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sy.springbootshirodemo.common.Result;
import com.sy.springbootshirodemo.entity.User;
import com.sy.springbootshirodemo.entity.param.LoginParam;
import com.sy.springbootshirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<User>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<User> aPage = userService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @RequiresPermissions(value = "user:getById")
    public ResponseEntity<User> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody User params) {
        userService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        userService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody User params) {
        userService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public Result login( @RequestBody LoginParam loginParam) {
        // 获取当前主体对象
        Subject subject = SecurityUtils.getSubject();

        // 创建用户名密码令牌
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPassword());

        try {
            // 调用主体的登录方法进行认证
            subject.login(token);

            // 登录成功，返回成功标识
            return new Result(200, "登录成功", null);
        } catch (Exception e) {
            e.printStackTrace();

            // 登录失败，重定向到登录页面
            return new Result(500, "登录失败", null);
        }
    }

    @PostMapping("/register")
    public Result register(String username, String password) {

        User user = userService.register(username, password);
        return new Result(200, "注册成功", user);
    }

}
