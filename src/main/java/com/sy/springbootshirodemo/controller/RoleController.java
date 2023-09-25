package com.ncpe.digitaldelivery.codegenerator.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ncpe.digitaldelivery.codegenerator.service.RoleService;
import com.ncpe.digitaldelivery.codegenerator.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2023-09-22
 */
@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Role>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Role> aPage = roleService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Role> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(roleService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Role params) {
        roleService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        roleService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Role params) {
        roleService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}