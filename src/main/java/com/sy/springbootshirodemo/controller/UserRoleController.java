package com.sy.springbootshirodemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sy.springbootshirodemo.entity.ShitoUserRole;
import com.sy.springbootshirodemo.service.ShitoUserRoleService;
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
@RequestMapping("/shito-user-role")
public class ShitoUserRoleController {


    @Autowired
    private ShitoUserRoleService shitoUserRoleService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<ShitoUserRole>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<ShitoUserRole> aPage = shitoUserRoleService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ShitoUserRole> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(shitoUserRoleService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody ShitoUserRole params) {
        shitoUserRoleService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        shitoUserRoleService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody ShitoUserRole params) {
        shitoUserRoleService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
