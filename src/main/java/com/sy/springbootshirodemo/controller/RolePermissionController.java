package com.sy.springbootshirodemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sy.springbootshirodemo.entity.RolePermission;
import com.sy.springbootshirodemo.service.RolePermissionService;
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
@RequestMapping("/role-permission")
public class RolePermissionController {


    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<RolePermission>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<RolePermission> aPage = rolePermissionService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RolePermission> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(rolePermissionService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody RolePermission params) {
        rolePermissionService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        rolePermissionService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody RolePermission params) {
        rolePermissionService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
