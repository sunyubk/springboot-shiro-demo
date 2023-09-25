package com.sy.springbootshirodemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sy.springbootshirodemo.entity.Permission;
import com.sy.springbootshirodemo.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Permission>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Permission> aPage = permissionService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Permission> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(permissionService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Permission params) {
        permissionService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        permissionService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Permission params) {
        permissionService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
